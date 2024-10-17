import java.io.Serializable;
import java.time.*;
import java.util.Arrays;
public class Gara implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vendi_Mbajtjes;
	private LocalDate data;
	private String[] drivers = new String[10];
	Gara() {
		
	}
	public Gara(String vendi_Mbajtjes, LocalDate data, String[] drivers) {
		super();
		this.vendi_Mbajtjes = vendi_Mbajtjes;
		this.data = data;
		this.drivers = drivers;
	}
	
	public String getVendi_Mbajtjes() {
		return vendi_Mbajtjes;
	}
	public void setVendi_Mbajtjes(String vendi_Mbajtjes) {
		this.vendi_Mbajtjes = vendi_Mbajtjes;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String[] getDrivers() {
		return drivers;
	}
	public void setDrivers(String[] drivers) {
		this.drivers = drivers;
	}

	@Override
	public String toString() {
		return "Gara [vendi_Mbajtjes=" + vendi_Mbajtjes + ", data=" + data + ", drivers=" + Arrays.toString(drivers)
				+ "]";
	}
	
	
	
}
