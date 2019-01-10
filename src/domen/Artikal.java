package domen;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Artikal {

	private int idArtikal;
	private int sifra;
	private String naziv;
	private String opis;
	private int cena;

	List<Artikal> al = new ArrayList<Artikal>();
	ArtikalTableModel atm = new ArtikalTableModel(al);

	public void dodaj1(Artikal a) {
		atm.add(a);
	}

	public void dodaj(Artikal a) {
		al.add(a);
	}

	public List<Artikal> getAl() {
		return al;
	}

	public void setAl(List<Artikal> al) {
		this.al = al;
	}

	public int getIdArtikal() {
		return idArtikal;
	}

	public void setIdArtikal(int idArtikal) {
		this.idArtikal = idArtikal;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

}
