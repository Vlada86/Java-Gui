package domen;

import java.util.Date;

public class Zaposleni {

	private int idZaposleni;
	private String imePrezime;
	private String userName;
	private String passWord;
	private String datumRodjenja;
	private String telefon;
	private int prioritet;

	public int getIdZaposleni() {
		return idZaposleni;
	}

	public void setIdZaposleni(int idZaposleni) {
		this.idZaposleni = idZaposleni;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getPrioritet() {
		return prioritet;
	}

	public void setPrioritet(int prioritet) {
		this.prioritet = prioritet;
	}

}
