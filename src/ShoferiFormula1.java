import java.io.Serializable;

//ShoferiFormula1 duhet të përfshijë statistika të tilla si sasinë e
//herëve që shoferi ka zënë vend të parë, të dytë dhe të tretë në sezon. Numri actual i
//pikëve që ka një shofer, dhe numri i garave që ka qënë pjesëmarrës në sezon deri në atë
//moment duhet të përfshihen gjithashtu.



public class ShoferiFormula1 extends Shoferi implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String emri;
	private String mbiemri;
	private String vendndodhja;
	private String ekipi;
	private int mosha;
	
	private int sasia_vendiPare;
	private int sasia_vendiDyte;
	private int sasia_vendiTrete;
	private int numri_pikeve;
	private int numri_garave;
	
	
	
	public ShoferiFormula1() {
		super();
	}
	
	public ShoferiFormula1(String emri, String mbiemri, String vendndodhja, String ekipi, int mosha,
			int sasia_vendiPare, int sasia_vendiDyte, int sasia_vendiTrete, int numri_garave) {
		super();
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.vendndodhja = vendndodhja;
		this.ekipi = ekipi;
		this.mosha = mosha;
		this.sasia_vendiPare = sasia_vendiPare;
		this.sasia_vendiDyte = sasia_vendiDyte;
		this.sasia_vendiTrete = sasia_vendiTrete;
		this.numri_garave = numri_garave;
	}


	public int getSasia_vendiPare() {
		return sasia_vendiPare;
	}

	public void setSasia_vendiPare(int sasia_vendiPare) {
		this.sasia_vendiPare = sasia_vendiPare;
	}
	public int getSasia_vendiDyte() {
		return sasia_vendiDyte;
	}
	public void setSasia_vendiDyte(int sasia_vendiDyte) {
		this.sasia_vendiDyte = sasia_vendiDyte;
	}
	public int getSasia_vendiTrete() {
		return sasia_vendiTrete;
	}
	public void setSasia_vendiTrete(int sasia_vendiTrete) {
		this.sasia_vendiTrete = sasia_vendiTrete;
	}
	public int getNumri_pikeve() {
		return numri_pikeve;
	}
	public void setNumri_pikeve(int numri_pikeve) {
		this.numri_pikeve = numri_pikeve;
	}
	public int getNumri_garave() {
		return numri_garave;
	}
	public void setNumri_garave(int numri_garave) {
		this.numri_garave = numri_garave;
	}
	
	public String getEmri() {
		return emri;
	}
	
	public void setEmri(String emri) {
		this.emri = emri;
	}
	
	public String getMbiemri() {
		return mbiemri;
	}
	
	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}
	
	public String getVendndodhja() {
		return vendndodhja;
	}
	
	public void setVendndodhja(String vendndodhja) {
		this.vendndodhja = vendndodhja;
	}
	
	public String getEkipi() {
		return ekipi;
	}
	
	public void setEkipi(String ekipi) {
		this.ekipi = ekipi;
	}
	
	public int getMosha() {
		return mosha;
	}
	
	public void setMosha(int mosha) {
		this.mosha = mosha;
	}

	@Override
	public String toString() {
		return "emri=" + emri + ", mbiemri=" + mbiemri + ", vendndodhja=" + vendndodhja + ", ekipi="
				+ ekipi + ", mosha=" + mosha + ", sasia_vendiPare=" + sasia_vendiPare + ", sasia_vendiDyte="
				+ sasia_vendiDyte + ", sasia_vendiTrete=" + sasia_vendiTrete + ", numri_pikeve=" + numri_pikeve
				+ ", numri_garave=" + numri_garave ;
	}

	
	

}
