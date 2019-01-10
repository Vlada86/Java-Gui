package Kontroler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import broker.DBKomunikacija;
import domen.Artikal;
import domen.ArtikalTableModel;
import domen.Korpa;
import domen.Zaposleni;

public class Kontroler {

	private static Kontroler instanca;
	private List<Zaposleni> alZaposleni = new ArrayList<>();
	private List<Artikal> alArtikal = new ArrayList<Artikal>();
	private ArtikalTableModel atm = new ArtikalTableModel(alArtikal);
	private ArrayList<Korpa> alKorpa = new ArrayList<>();
	
	public static Kontroler getInstanca() {
		if (instanca == null) {
			instanca = new Kontroler();
		}
		return instanca;

	}

	public boolean proveraZaposlenog(String user, String pass, int prioritet) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		boolean prom = DBKomunikacija.getBroker().proveraZaposlenog(user, pass, prioritet);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return prom;

	}

	public List<Zaposleni> vratiZaposlene() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alZaposleni = DBKomunikacija.getBroker().vratiZaposlene();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alZaposleni;
	}

	public List<Artikal> prikaziPodatke() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alArtikal = DBKomunikacija.getBroker().vratiArtikle();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alArtikal;
	}

	public List<Artikal> getAlArtikal() {
		return alArtikal;
	}

	public void setAlArtikal(List<Artikal> alArtikal) {
		this.alArtikal = alArtikal;
	}

	public List<Korpa> prikaziPodatkeBelo() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alKorpa = DBKomunikacija.getBroker().prikaziPodatkeBelo();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alKorpa;
	}

	public List<Korpa> prikaziPodatkeCrno() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alKorpa = DBKomunikacija.getBroker().prikaziPodatkeCrno();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alKorpa;
	}

	public void upisiArtikal(String sifra, String naziv, String cena) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiArtikal(sifra, naziv, cena);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public List<Korpa> filtriraj(String d1, String d2,String opcija) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alKorpa = DBKomunikacija.getBroker().filtriraj(d1, d2,opcija);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alKorpa;

	}

	public void upisiZaposlenog(String imePrezime, String userName, String password, String date, String telefon,
			int prioritet) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().upisiZaposlenog(imePrezime, userName, password, date, telefon, prioritet);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public List<Artikal> filtrirajPretragu(String query) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alArtikal = DBKomunikacija.getBroker().filtrirajPretragu(query);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alArtikal;
	}

	public void ubaciUKorpu(int idZaposleni, int idArtikal, String opis, String opcija, int cena, java.sql.Date datum) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().ubaciUKorpu(idZaposleni, idArtikal, opis, opcija, cena, datum);
		DBKomunikacija.getBroker().zatvoriKonekciju();

	}

	public void izbrisiArtikal(int idArtikal) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiArtikal(idArtikal);
		DBKomunikacija.getBroker().zatvoriKonekciju();
	}

	public List<Zaposleni> prikaziZaposlene() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alZaposleni = DBKomunikacija.getBroker().prikaziZaposlene();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alZaposleni;
	}

	public List<Korpa> prikaziPodatkeZaposlenom() {
		DBKomunikacija.getBroker().otvoriKonekciju();
		alKorpa = DBKomunikacija.getBroker().prikaziPodatkeZaposlenom();
		DBKomunikacija.getBroker().zatvoriKonekciju();
		return alKorpa;
	}

	public void izbrisiZaposlenog(int idZaposleni) {
		DBKomunikacija.getBroker().otvoriKonekciju();
		DBKomunikacija.getBroker().izbrisiZaposlenog(idZaposleni);
		DBKomunikacija.getBroker().zatvoriKonekciju();
		
	}

}

// public void ubaciUKorpu(int idZaposleni, int idArtikal, int kolicina, String
// model, int legal, int black) {
// DBKomunikacija.getBroker().otvoriKonekciju();
// DBKomunikacija.getBroker().upisiRezervaciju(idZaposleni,idArtikal,
// kolicina,model,legal,black);
// DBKomunikacija.getBroker().zatvoriKonekciju();
//
// }

// public void ubaciUKorpu(int parseInt) {
// DBKomunikacija.getBroker().otvoriKonekciju();
// DBKomunikacija.getBroker().upisiRezervaciju(, predstava, ukupnoMesta,
// kolicina);
// DBKomunikacija.getBroker().zatvoriKonekciju();
//
// }
