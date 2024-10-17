import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FormulaGui extends JFrame implements ActionListener{
	static boolean ka_Gara = false;
	static Formula1MenaxhimKampionati FMK = new Formula1MenaxhimKampionati();
	static int numer;
	static double probFituesit;
	JPanel panel = new JPanel();
	JScrollPane sp;
	
	static String column[] = {"Emri", "Ekipi", "Numri i garave", "Vend Pare", "Vend Dyte","vendi Trete", "Piket"};
	static String gara_Column[] = {"Nr.","VendiMbajtja", "Data","Vendi pare","Vendi dyte","Vendi trete"};
	static String kerko_column[] = {"Vendi i mbajtjes", "Data","Pozicioni"};
	static String generated_column[] = {"Pozicioni", "Shoferi"};
	String Gara_Data[][];
	String kerko_Garat[][];
	String data[][];
	String generated_Data[][] = new String[10][2];
	String Vendndodhja;
	String data_Gares;
	
	JLabel label = new JLabel("Kerkoni te dhenat e shoferit :");
	JLabel titulli = new JLabel("Tabela e Shofereve");
	
	JTable tabela_Shofereve ;
	JTable tabela_shfaqGarat;
	JTable tabela_Kerkimit;
	JTable tabela_gjenerimGare;
	
	JButton renditRrites = new JButton("Rendi rrites");
	JButton rendit_VendiPar = new JButton("Sipas vendeve");
	JButton gjenerim_gare = new JButton("Gjenero gare");
	JButton shfaq_Garat = new JButton("Shfaq Garat");
	JButton kerko = new JButton("Kerko");
	JButton rendiZbrites = new JButton("Rendi Zbrites");
	JButton gara_Probabilitet = new JButton("Gjenero Gare me Probabilitet");
	JTextArea txtBox = new JTextArea();
	 
	private List shoferet = (List) FMK.getShoferet();
	private List garat = (List) FMK.getGarat();
	
	
	//FormulaGui g = new FormulaGui();
	FormulaGui(int nr){
		numer=nr;
		data = new String[nr][7];
		
		int num = garat.size();
		Gara_Data = new String[num][6];
		kerko_Garat = new String[num][3];
		
		//kerko_Garat_Shoferit(txtBox.getText());
		Ruaj_teDhenat();
		ruaj_Garat();
		renditSipas_Pikeve();
		
		
		
		
		this.setSize(550,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Statistikat");
		

		tabela_Shofereve = new JTable(data, column);
		tabela_Shofereve.setEnabled(false);
		tabela_Shofereve.setCellSelectionEnabled(false);
		tabela_Shofereve.setBackground(new Color(255,248,220));
		sp=new JScrollPane(tabela_Shofereve);   
		sp.setBackground(new Color(255,248,220));
		sp.setBounds(30,50,480,150);
		
		tabela_shfaqGarat = new JTable(Gara_Data, gara_Column);
		tabela_shfaqGarat.setEnabled(false);
		tabela_shfaqGarat.setCellSelectionEnabled(false);
		
		
		tabela_Kerkimit = new JTable(kerko_Garat, kerko_column);
		tabela_Kerkimit.setEnabled(false);
		tabela_Kerkimit.setCellSelectionEnabled(false);
		tabela_Kerkimit.setBackground(new Color(255, 238, 88));
		
		panel.setSize(550,500);
		panel.setLayout(null);
		
		panel.setBackground(Color.red);
		
		
		renditRrites.setBounds(380,210,120,30);
		renditRrites.addActionListener(this);
		renditRrites.setFont(new Font("Sherif", Font.BOLD,11));
		
		rendiZbrites.setBounds(380,260,120,30);
		rendiZbrites.addActionListener(this);
		rendiZbrites.setFont(new Font("Sherif", Font.BOLD,11));
		
		rendit_VendiPar.setBounds(380,310,120,30);
		rendit_VendiPar.addActionListener(this);
		rendit_VendiPar.setFont(new Font("Sherif", Font.BOLD,11));
		
		gjenerim_gare.setBounds(380,360,120,30);
		gjenerim_gare.addActionListener(this);
		gjenerim_gare.setFont(new Font("Sherif", Font.BOLD,11));
		
		shfaq_Garat.setBounds(380,410,120,30);
		shfaq_Garat.addActionListener(this);
		shfaq_Garat.setFont(new Font("Sherif", Font.BOLD,11));
		
		label.setBounds(50,260,250,30);
		label.setFont(new Font("Sherif", Font.BOLD,14));
		
		titulli.setBounds(200,15,300,40);
		titulli.setFont(new Font("Sherif", Font.BOLD,17));
		titulli.setForeground(Color.darkGray);
		
		txtBox.setBounds(50,300,110,20);
		txtBox.setFont(new Font("Sherif", Font.ROMAN_BASELINE,13));
		
		kerko.setBounds(190,300,80,20);
		kerko.addActionListener(this);
		kerko.setFont(new Font("Sherif", Font.BOLD,11));
		
		gara_Probabilitet.setBounds(60,410,200,30);
		gara_Probabilitet.addActionListener(this);
		gara_Probabilitet.setFont(new Font("Sherif", Font.BOLD,11));
		
		
		panel.add(sp);
		panel.add(kerko);
		panel.add(txtBox);
		panel.add(label);
		panel.add(titulli);
		panel.add(renditRrites);
		panel.add(rendiZbrites);
		panel.add(rendit_VendiPar);
		panel.add(gjenerim_gare);
		panel.add(shfaq_Garat);
		panel.add(gara_Probabilitet);
		this.add(panel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(renditRrites)) {
		rendiPikeve_rrites();
		Ruaj_teDhenat();
		txtBox.setText("");
		tabela_Shofereve.repaint();
		}
		else if(e.getSource()==rendit_VendiPar) {
			renditSipas_vendi1();
			Ruaj_teDhenat();
			tabela_Shofereve.repaint();
		}
		else if(e.getSource()==gjenerim_gare) {
			FMK.shto_Gare(gjenero_gare());   //shton nje gare te krijuar rastesisht ne listen e garave dhe e ruan ate ne nje file
			Ruaj_teDhenat();
			txtBox.setText("");
			ruaj_Garat();
			tabela_Shofereve.repaint();
			JOptionPane.showMessageDialog(this, new JScrollPane(tabela_gjenerimGare),"VendMabjtja : "+Vendndodhja+"    "+"Data : "+data_Gares,JOptionPane.PLAIN_MESSAGE);
		}
		else if (e.getSource().equals(shfaq_Garat)) {
			Rendit_Garat();
			ruaj_Garat();
			JOptionPane.showMessageDialog(null, new JScrollPane(tabela_shfaqGarat),"Garat e Sezonit Aktual",JOptionPane.PLAIN_MESSAGE);
			txtBox.setText("");
			tabela_Kerkimit.removeAll();
		}
		else if(e.getSource().equals(kerko)) {
			
			 kerko_Garat_Shoferit(txtBox.getText());
			 if(ka_Gara==false) {
				 JOptionPane.showMessageDialog(null, "Shoferi \""+txtBox.getText()+"\" nuk ka marre pjes ne asnje gare ne sezon!");
				 txtBox.setText("");
				 panel.repaint();
			 }
			 else if(ka_Gara){
				 JOptionPane.showMessageDialog(null, new JScrollPane(tabela_Kerkimit), "Rezultatet e kerkimit per "+txtBox.getText(), JOptionPane.PLAIN_MESSAGE);
			 }
			 
		}
		else if(e.getSource().equals(rendiZbrites)) {
			renditSipas_Pikeve();
			Ruaj_teDhenat();
			tabela_Shofereve.repaint();
			txtBox.setText("");
		}
		else if(e.getSource().equals(gara_Probabilitet)) {
			FMK.shto_Gare(gjeneroGareTeRastesishme());   //shton nje gare te krijuar rastesisht ne listen e garave dhe e ruan ate ne nje file
			Ruaj_teDhenat();
			txtBox.setText("");
			ruaj_Garat();
			tabela_Shofereve.repaint();
			JOptionPane.showMessageDialog(this, new JScrollPane(tabela_gjenerimGare),"VendMabjtja : "+Vendndodhja+"    "+"Data : "+data_Gares
																+"      "+"Probabiliteti Fituesit: "+ probFituesit,JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void renditSipas_Pikeve() {
		for(int j = 0; j<shoferet.size()-1;j++) {
					
					for(int k=j+1 ;k<shoferet.size() ; k++ ) {
						ShoferiFormula1 obj1 = (ShoferiFormula1) shoferet.get(j);
						ShoferiFormula1 obj2 =  (ShoferiFormula1) shoferet.get(k);
						if (obj1.getNumri_pikeve()<obj2.getNumri_pikeve()) {
							ShoferiFormula1 temp = obj1;
							shoferet.set(j, obj2);
							shoferet.set(k, temp);
						}
						if(obj1.getNumri_pikeve()==obj2.getNumri_pikeve()) {
							if(obj1.getSasia_vendiPare()<obj2.getSasia_vendiPare()) {
								ShoferiFormula1 temp = obj1;
								shoferet.set(j, obj2);
								shoferet.set(k, temp);
							}
						}
					}
				}
			}
	public void rendiPikeve_rrites() {
		for(int j = 0; j<shoferet.size()-1;j++) {
					
					for(int k=j+1 ;k<shoferet.size() ; k++ ) {
						ShoferiFormula1 obj1 = (ShoferiFormula1) shoferet.get(j);
						ShoferiFormula1 obj2 =  (ShoferiFormula1) shoferet.get(k);
						if (obj1.getNumri_pikeve()>obj2.getNumri_pikeve()) {
							ShoferiFormula1 temp = obj1;
							shoferet.set(j, obj2);
							shoferet.set(k, temp);
						}
					}
				}
			}
	
	public void Ruaj_teDhenat() {
	//	int num = nr_Shofereve();
		int i=0;
		for (Iterator<ShoferiFormula1> iterator = ((List<ShoferiFormula1>) shoferet).iterator(); iterator.hasNext();) {
			ShoferiFormula1 obj= iterator.next();
			   data[i][0] = obj.getEmri();
			   data[i][1] = obj.getEkipi();
			   data[i][2] = String.valueOf(obj.getNumri_garave());
			   data[i][3] = String.valueOf(obj.getSasia_vendiPare());
			   data[i][4] = String.valueOf(obj.getSasia_vendiDyte());
			   data[i][5] = String.valueOf(obj.getSasia_vendiTrete());
			   data[i][6] = String.valueOf(obj.getNumri_pikeve());
			   i++;
			}
	}
	public void renditSipas_vendi1() {
		for(int j = 0; j<shoferet.size()-1;j++) {
			
			for(int k=j+1 ;k<shoferet.size() ; k++ ) {
				ShoferiFormula1 obj1 = (ShoferiFormula1) shoferet.get(j);
				ShoferiFormula1 obj2 =  (ShoferiFormula1) shoferet.get(k);
					if(obj1.getSasia_vendiPare()<obj2.getSasia_vendiPare()) {
						ShoferiFormula1 temp = obj1;
						shoferet.set(j, obj2);
						shoferet.set(k, temp);
					}
			}
		}
	}
	public Gara gjenero_gare() {
		Random rand = new Random();
		LocalDate dita = LocalDate.now();
		int dit_random = rand.nextInt(1000)-1000;
		dita = dita.plusDays(dit_random);
		String qyteti[]= {"Agjentine","Meksik","Kili","Portugali","France","Rusi", "Kine","Spanje","UK","USA","EMA","Katar",
				"Monako","Marok","Afrike","Moscov", "Rio de Zhanerio","Pekin","Hong Kong","Tokyo","Vatikan","Luksemburg","England",
				"Belgium","Zambabue", "Egypt","Kzakistan"};
		String Vendodhja = qyteti[rand.nextInt(qyteti.length)];
		
		String pozicionet[] = new String[10];
		String drivers[] = new String[numer];
		for(int j = 0; j<numer ; j++) {
			ShoferiFormula1 obj =  (ShoferiFormula1) shoferet.get(j);
			drivers[j] = obj.getEmri();
		}
		List<String> shufled = Arrays.asList(drivers);
		Collections.shuffle(shufled);
		
		Vendndodhja = Vendodhja;
		data_Gares = String.valueOf(dita);
		
		for(int i = 0 ; i<10 && i<numer ; i++) {
			
			pozicionet[i] = shufled.get(i);
			generated_Data[i][0]  =String.valueOf((i+1));
			generated_Data[i][1]  =	pozicionet[i];
			FMK.Modifiko_Automatikisht(pozicionet[i],(i+1));
		}
		
		Gara gara = new Gara();
		gara.setData(dita);
		gara.setVendi_Mbajtjes(Vendodhja);
		gara.setDrivers(pozicionet);
		tabela_gjenerimGare = new JTable(generated_Data, generated_column);
		return gara;
	}
	
	public int nr_Garave() {
		int i=0;
		//List lista = (List) FMK.g();
		for (Iterator<Gara> iterator = ((List<Gara>) garat).iterator(); iterator.hasNext();) {
			   i++;
			}
		return i;
	}
	public void Rendit_Garat() {
		for(int j = 0; j<garat.size()-1;j++) {
			for(int k=j+1 ;k<garat.size() ; k++ ) {
				Gara obj1 = (Gara) garat.get(j);
				Gara obj2 =  (Gara) garat.get(k);
					if(obj1.getData().isAfter(obj2.getData())) {
						Gara temp = obj1;
						garat.set(j, obj2);
						garat.set(k, temp);
					}
			}
		}
	}
	public void ruaj_Garat() {
		int i=0;
		String teDhenat[][]  =new String[garat.size()][7];
		for (Iterator<Gara> iterator = ((List<Gara>) garat).iterator(); iterator.hasNext();) {
			Gara obj= iterator.next();
			teDhenat[i][0] = String.valueOf(i+1);
			teDhenat[i][1] = obj.getVendi_Mbajtjes();
			teDhenat[i][2] = String.valueOf(obj.getData());		
			teDhenat[i][3] = obj.getDrivers()[0];
			teDhenat[i][4] = obj.getDrivers()[1];
			teDhenat[i][5] = obj.getDrivers()[2];
			i++;
			}
		tabela_shfaqGarat = new JTable(teDhenat,gara_Column);
	}
	public void kerko_Garat_Shoferit(String emri) {
		int kaGar=0;
		int k=0;
		 kerko_Garat = new String[garat.size()][3];
		for (Iterator<Gara> iterator = ((List<Gara>) garat).iterator(); iterator.hasNext();) {
			Gara obj= iterator.next();
			String drivers[] = obj.getDrivers();
			
			   for(int i = 0 ; i<10;i++) {
				   if(String.valueOf(drivers[i]).equals(emri)) {
					   kerko_Garat[k][0]= obj.getVendi_Mbajtjes();
					   kerko_Garat[k][1]= String.valueOf(obj.getData()) ;
					   kerko_Garat[k][2]= String.valueOf(i+1); 
					   k++;
					   kaGar++;
				   } 
			   }
			}
		if (kaGar==0) {
			ka_Gara = false;
		}
		else
			 ka_Gara = true;
		this.tabela_Kerkimit = new JTable(kerko_Garat, kerko_column);
	}
	
	
	public void Fshi() {
		for(int i = 0;i<numer ; i++) {
			for(int j = 0 ; j<3 ; j++)
				kerko_Garat[i][j] ="";
		}
	}
		//Kjo metpde krijon nje gare me probabilitete te rendesishme
	 public Gara gjeneroGareTeRastesishme() {
	        List<ShoferiFormula1> listTemp = new ArrayList<>();

	        Random rand = new Random();
	        
	        //Keto jane probabilitetet sipas pozicioneve te nisjes
	        List<Double> probArr = new ArrayList<>();
	        probArr.add(0.4);
	        probArr.add(0.3);
	        probArr.add(0.1);
	        probArr.add(0.1);
	        probArr.add(0.02);
	        probArr.add(0.02);
	        probArr.add(0.02);
	        probArr.add(0.02);
	        probArr.add(0.02);

	        double probabilitetiFituesit;

	        while(probArr.size()<this.shoferet.size()) {
	            probArr.add(0.0);
	        }

	        int i = 0;    // Perdorim per te marre vlerat ne listen e probabiliteteve me radhe
	        double randNum = Math.random();
	        HashMap<ShoferiFormula1, Double> tempMap = new HashMap<>();

	        // Futja e probabilitetit si celes dhe i objektit shoferiFormula1 ne nje Map
	        Collections.shuffle(shoferet);
	        for (i = 0 ; i<shoferet.size() ; i++) {
	        	tempMap.put((ShoferiFormula1) this.shoferet.get(i), probArr.get(i));
	        }

	       // System.out.println(tempMap.keySet());

	        ShoferiFormula1 key = null;  // Ruajme celesin e objektit qe rezulti nga objekti
	        double sum = 0;
	        for(Map.Entry<ShoferiFormula1, Double> entry : tempMap.entrySet()) {
	            if(sum >= randNum) {
	                break;
	            }
	            sum += entry.getValue();
	            key=entry.getKey();
	        }
	        listTemp.add(key);
	        probabilitetiFituesit = tempMap.get(key);
	        tempMap.remove(key);

	        //System.out.println(tempMap);

	        List<ShoferiFormula1> keys= new ArrayList<>(tempMap.keySet());
	        Collections.shuffle(keys);
	        for(ShoferiFormula1 shoferiFormula1:keys) {
	            listTemp.add(shoferiFormula1);
	        }
	        
	        this.probFituesit = probabilitetiFituesit;
	        
	        
			LocalDate dita = LocalDate.now();
			int dit_random = rand.nextInt(1000)-1000;
			dita = dita.plusDays(dit_random);
			String qyteti[]= {"Agjentine","Meksik","Kili","Portugali","France","Rusi", "Kine","Spanje","UK","USA","EMA","Katar",
					"Monako","Marok","Afrike","Moscov", "Rio de Zhanerio","Pekin","Hong Kong","Tokyo","Vatikan","Luksemburg","England",
					"Belgium","Zambabue", "Egypt","Kzakistan"};
			String Vendodhja = qyteti[rand.nextInt(qyteti.length)];
			
			Vendndodhja = Vendodhja;
			data_Gares = String.valueOf(dita);
			String pozicionet[] = new String[10];
			for(int k = 0 ; k<10 && k<numer ; k++) {
				
				pozicionet[k] = listTemp.get(k).getEmri();
				generated_Data[k][0]  =String.valueOf((k+1));
				generated_Data[k][1]  =	pozicionet[k];
				FMK.Modifiko_Automatikisht(pozicionet[k],(k+1));
			}
			
			Gara gara = new Gara();
			gara.setData(dita);
			gara.setVendi_Mbajtjes(Vendodhja);
			gara.setDrivers(pozicionet);
			tabela_gjenerimGare = new JTable(generated_Data, generated_column);
			return gara;
	        
	    }
}
