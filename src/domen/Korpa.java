package domen;

import java.security.Timestamp;
import java.util.Date;

public class Korpa {

	private int idKorpa;
	private int idZaposleni;
	private int sifra;
	private String imePrezime;
	private Date date;
	private String naziv;
	private String model;
	private String opcija;
	private int kolicina;
	private int nabavnaCena;
	private int prodajnaCena;

	public int getIdKorpa() {
		return idKorpa;
	}

	public void setIdKorpa(int idKorpa) {
		this.idKorpa = idKorpa;
	}

	public int getIdZaposleni() {
		return idZaposleni;
	}

	public void setIdZaposleni(int idZaposleni) {
		this.idZaposleni = idZaposleni;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOpcija() {
		return opcija;
	}

	public void setOpcija(String opcija) {
		this.opcija = opcija;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getNabavnaCena() {
		return nabavnaCena;
	}

	public void setNabavnaCena(int nabavnaCena) {
		this.nabavnaCena = nabavnaCena;
	}

	public int getProdajnaCena() {
		return prodajnaCena;
	}

	public void setProdajnaCena(int prodajnaCena) {
		this.prodajnaCena = prodajnaCena;
	}

}
