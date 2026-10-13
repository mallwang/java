package datenhaltung;

import java.sql.Date;

public class Kunde {
	private String knr = null;
	private String adressID = null;
	private String vname = null;
	private String nname = null;
	private Date geb = null;
	private String tel = null;
	private String str = null;
	private String hnr = null;
	private String plz = null;
	private String ort = null;

	private String pnr;

	public String getAdressID() {
		return adressID;
	}

	public Date getGeb() {
		return geb;
	}

	public String getHnr() {
		return hnr;
	}

	public String getKnr() {
		return knr;
	}

	public String getNname() {
		return nname;
	}

	public String getOrt() {
		return ort;
	}

	public String getPlz() {
		return plz;
	}

	public String getPnr() {
		return pnr;
	}

	public String getStr() {
		return str;
	}

	public String getTel() {
		return tel;
	}

	public String getVname() {
		return vname;
	}

	public void setAdressID(String adressID) {
		this.adressID = adressID;
	}

	public void setGeb(Date geb) {
		this.geb = geb;
	}

	public void setHnr(String hnr) {
		this.hnr = hnr;
	}

	public void setKnr(String knr) {
		this.knr = knr;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

}
