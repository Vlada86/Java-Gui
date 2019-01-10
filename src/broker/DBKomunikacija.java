package broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import domen.Artikal;
import domen.Korpa;
import domen.Zaposleni;

public class DBKomunikacija {

	public static DBKomunikacija broker;
	private Connection con;

	private DBKomunikacija() {

		ucitajDriver();
	}

	private void ucitajDriver() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	public void otvoriKonekciju() {

		try {
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/AlfaMobil?" + "user=root&password=vv24041986");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void zatvoriKonekciju() {

		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static DBKomunikacija getBroker() {

		if (broker == null) {

			broker = new DBKomunikacija();
		}

		return broker;
	}

	public boolean proveraZaposlenog(String user, String pass, int prioritet) {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL database connection
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/AlfaMobil?" + "user=root&password=vv24041986");
			PreparedStatement pst = conn
					.prepareStatement("Select * from zaposleni where BINARY userName=? and  BINARY passWord=? and prioritet=?");
			pst.setString(1, user);
			pst.setString(2, pass);
			pst.setInt(3, prioritet);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Zaposleni> vratiZaposlene() {
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Zaposleni> zap = new ArrayList<>();
		String sql = "select * from zaposleni";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Zaposleni z = new Zaposleni();
				z.setIdZaposleni(rs.getInt("idZaposleni"));
				z.setImePrezime(rs.getString("imePrezime"));
				z.setUserName(rs.getString("userName"));
				z.setPassWord(rs.getString("passWord"));
				z.setDatumRodjenja(rs.getString("datumRodjenja"));
				z.setTelefon(rs.getString("telefon"));
				z.setPrioritet(rs.getInt("prioritet"));

				zap.add(z);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return zap;
	}

	public List<Artikal> vratiArtikle() {
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * from artikal  ";
		ArrayList<Artikal> art = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Artikal a = new Artikal();

				a.setIdArtikal(rs.getInt("idArtikal"));
				a.setSifra(rs.getInt("Sifra"));
				a.setNaziv(rs.getString("Naziv"));
				a.setCena(rs.getInt("nabavnaCena"));
				art.add(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return art;
	}

	public ArrayList<Korpa> prikaziPodatkeBelo() {
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT zaposleni.imePrezime ,artikal.sifra, artikal.naziv,korpa.datum,korpa.opcija,artikal.nabavnaCena,korpa.prodajnaCena from korpa inner join artikal on artikal.idArtikal = korpa.idArtikal inner join zaposleni on  zaposleni.idZaposleni = korpa.idZaposleni WHERE month(datum) = MONTH( NOW()) ";
		ArrayList<Korpa> belo = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Korpa kor = new Korpa();
				kor.setDate(rs.getDate("datum"));
				kor.setImePrezime(rs.getString("imePrezime"));
				kor.setSifra(rs.getInt("sifra"));
				kor.setNaziv(rs.getString("naziv"));
				kor.setOpcija(rs.getString("opcija"));
				kor.setNabavnaCena(rs.getInt("nabavnaCena"));
				kor.setProdajnaCena(rs.getInt("prodajnaCena"));

				belo.add(kor);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return belo;
	}

	public ArrayList<Korpa> prikaziPodatkeCrno() {
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT zaposleni.imePrezime , artikal.sifra,artikal.naziv,korpa.datum,artikal.nabavnaCena,korpa.prodajnaCena from korpa inner join artikal on artikal.idArtikal = korpa.idArtikal inner join zaposleni on  zaposleni.idZaposleni = korpa.idZaposleni where opcija = 'crno'";
		ArrayList<Korpa> crno = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Korpa kor = new Korpa();
				kor.setDate(rs.getDate("datum"));
				kor.setImePrezime(rs.getString("imePrezime"));
				kor.setSifra(rs.getInt("sifra"));
				kor.setNaziv(rs.getString("naziv"));
				kor.setNabavnaCena(rs.getInt("nabavnaCena"));
				kor.setProdajnaCena(rs.getInt("prodajnaCena"));

				crno.add(kor);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return crno;
	}

	public void upisiArtikal(String sifra, String naziv, String cena) {
		String sql = "INSERT INTO Artikal (Sifra,Naziv,nabavnaCena) Values (?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, sifra);
			ps.setString(2, naziv);
			ps.setString(3, cena);

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<Korpa> filtriraj(String d1, String d2,String opcija) {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Korpa> filter = new ArrayList<>();
		String sql = "SELECT zaposleni.imePrezime ,artikal.sifra, artikal.naziv,korpa.opis,korpa.datum,korpa.opcija,artikal.nabavnaCena,korpa.prodajnaCena from korpa inner join artikal on artikal.idArtikal = korpa.idArtikal inner join zaposleni on  zaposleni.idZaposleni = korpa.idZaposleni where opcija = '"+opcija+"' and datum between '"
				+ d1 + "' and '" + d2 + "'  ";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Korpa kor = new Korpa();
				kor.setDate(rs.getDate("datum"));
				kor.setImePrezime(rs.getString("imePrezime"));
				kor.setSifra(rs.getInt("sifra"));
				kor.setNaziv(rs.getString("naziv"));
				kor.setModel(rs.getString("opis"));
				kor.setOpcija(rs.getString("opcija"));
				
				kor.setNabavnaCena(rs.getInt("nabavnaCena"));
				kor.setProdajnaCena(rs.getInt("prodajnaCena"));

				filter.add(kor);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return filter;

	}

	public void upisiZaposlenog(String imePrezime, String userName, String password, String date,
			String telefon, int prioritet) {

		String sql = "INSERT INTO zaposleni (imePrezime,userName,passWord,datumRodjenja,telefon,prioritet) Values (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, imePrezime);
			ps.setString(2, userName);
			ps.setString(3, password);
			ps.setString(4,  date);
			ps.setString(5, telefon);
			ps.setInt(6, prioritet);

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<Artikal> filtrirajPretragu(String query) {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Artikal> artikal = new ArrayList<>();
		String sql = "Select * from artikal where sifra like '" + query + "%" + "'  ";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Artikal a = new Artikal();

				a.setIdArtikal(rs.getInt("idArtikal"));
				a.setSifra(rs.getInt("Sifra"));
				a.setNaziv(rs.getString("Naziv"));
				a.setCena(rs.getInt("nabavnaCena"));

				artikal.add(a);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return artikal;

	}

	public void ubaciUKorpu(int idZaposleni, int idArtikal, String opis, String opcija, int cena, java.sql.Date datum) {

		String sql = "INSERT INTO korpa (idArtikal,idZaposleni,opis,opcija,prodajnaCena,datum) Values (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, idArtikal);
			ps.setInt(2, idZaposleni);
			ps.setString(3, opis);
			ps.setString(4, opcija);
			ps.setInt(5, cena);
			ps.setDate(6, datum);

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void izbrisiArtikal(int idArtikal) {
		String sql = "Delete from artikal where idArtikal ='" + idArtikal + "'";
		

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Zaposleni> prikaziZaposlene() {
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Zaposleni> zap = new ArrayList<>();
		String sql = "select * from zaposleni";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Zaposleni z = new Zaposleni();
				z.setIdZaposleni(rs.getInt("idZaposleni"));
				z.setImePrezime(rs.getString("imePrezime"));
				z.setUserName(rs.getString("userName"));
				z.setPassWord(rs.getString("passWord"));
				z.setDatumRodjenja(rs.getString("datumRodjenja"));
				z.setTelefon(rs.getString("telefon"));
				z.setPrioritet(rs.getInt("prioritet"));

				zap.add(z);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return zap;
	}

	public ArrayList<Korpa> prikaziPodatkeZaposlenom() {
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT zaposleni.imePrezime ,artikal.sifra, artikal.idArtikal,artikal.naziv,korpa.datum,korpa.opcija,korpa.opis,korpa.prodajnaCena from korpa inner join artikal on artikal.idArtikal = korpa.idArtikal inner join zaposleni on  zaposleni.idZaposleni = korpa.idZaposleni WHERE month(datum) = MONTH( NOW())";
		ArrayList<Korpa> prodavac = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {

				Korpa kor = new Korpa();
				kor.setDate(rs.getDate("datum"));
				kor.setImePrezime(rs.getString("imePrezime"));
				kor.setSifra(rs.getInt("sifra"));
				kor.setNaziv(rs.getString("naziv"));
				kor.setModel(rs.getString("opis"));
				kor.setOpcija(rs.getString("opcija"));
				kor.setProdajnaCena(rs.getInt("prodajnaCena"));

				prodavac.add(kor);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodavac;
	}

	public void izbrisiZaposlenog(int idZaposleni) {
		String sql = "Delete from zaposleni where idZaposleni ='" + idZaposleni + "'";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		
	}
}
