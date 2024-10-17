
public abstract class Shoferi {

	abstract String getEmri() ;

	abstract void setEmri(String emri) ;

	abstract String getMbiemri() ;

	abstract void setMbiemri(String mbiemri) ;
	
	abstract String getVendndodhja() ;

	abstract void setVendndodhja(String vendndodhja);

	abstract String getEkipi();

	abstract void setEkipi(String ekipi);

	abstract int getMosha() ;

	abstract void setMosha(int mosha);

	protected abstract void setSasia_vendiPare(int temp);
	
	protected abstract int getSasia_vendiPare();
	
	protected abstract void setSasia_vendiDyte(int temp);
	
	protected abstract int getSasia_vendiDyte();

	protected abstract void setSasia_vendiTrete(int temp);
	
	protected abstract int getSasia_vendiTrete();

	protected abstract void setNumri_pikeve(int piket);
	
	protected abstract int getNumri_pikeve();

	protected abstract void setNumri_garave(int temp);

	protected abstract int getNumri_garave();
	
}
