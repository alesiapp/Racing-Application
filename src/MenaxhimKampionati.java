
public interface MenaxhimKampionati {
		
	void Menu();	
	void Shto_Shofer();
	int fshi_Shofer(String emri, int NrShofereve);
	void ndrysho_Shofer(String ekipi, String emri, String mbiemri);
	void shto_Gare(Gara gare);
	void ruaj_Shoferet();
	void ruaj_Garat();
	int Lexo_Shoferet();
	void lexo_Garat();
	void empty();
	void Modifiko_Automatikisht(String shoferi, int pozicioni);
}
