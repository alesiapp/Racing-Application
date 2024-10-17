import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Formula1MenaxhimKampionati implements MenaxhimKampionati{
	
	ShoferiFormula1 shofer = new ShoferiFormula1();

	
	private static List<ShoferiFormula1> shoferet = new ArrayList<ShoferiFormula1>();
	private static List<Gara> garat = new ArrayList<Gara>();
	private int nr_Shofereve;
	
	
	public static void main(String[] args) {
		
		Formula1MenaxhimKampionati FMK = new Formula1MenaxhimKampionati();
		 
		 int zgjedhja = 0;
		Scanner input = new Scanner(System.in);
		
		// Jane shtuar per te bere testimet me lehte 
		
		
		
		int i = FMK.Lexo_Shoferet();    // ben leximin nga file i shofereve dhe kthen numrin e shofereve ne file
		FMK.lexo_Garat();				//lexon garat e ruajtura ne file dhe i hedh ne liste
		FMK.setNumriShofereve(i);
		//System.out.println("Shoferet: "+FMK.nr_Shofereve);

			String emri;
			String ekipi;
			String mbiemri;
		
			//Cikli i menuse
			do {
			//shfaq menune
			FMK.Menu();
		
			zgjedhja = input.nextInt();
		//Zgjedhja e opsioneve te menuse nga perdoruesi
			switch(zgjedhja) {
			
			case 1:
				FMK.Shto_Shofer();	
				break;
			case 2: 	
				
				System.out.print("\nJepni emrin e shoferit qe doni te fshini: ");
				emri = input.next();
				i=FMK.fshi_Shofer(emri,i);				//fshin shoferin me emrin e dhene nga perdoruesi nese ekziston dhe ull me nje nr e shofereve
				break;
				
			case 3:
				boolean isEkip = false;
				System.out.print("\nJepni emrin e skuadren qe doni t'i ndryshoni shoferin: ");
				ekipi = input.next();
				for(int k = 0 ; k<shoferet.size() ; k++) {
					ShoferiFormula1 driver = shoferet.get(k);
					if(driver.getEkipi().equalsIgnoreCase(ekipi)) {
						isEkip = true;
						break;
					}
				}
				if(isEkip) {
					System.out.print("\nJepni emrin e shoferit te ri: ");
					emri = input.next();
					System.out.print("\nJepni mbiemrin e shoferit te ri: ");
					mbiemri = input.next();
					
					FMK.ndrysho_Shofer(ekipi, emri, mbiemri); // ndryshon emrin dhe mbiemrin e shoferit te nje ekipi
					FMK.ruaj_Shoferet();
				}// ruan te dhenat e modifikuara ne file
				else {
					System.err.println("\n\tEkipi "+ekipi+" nuk ekziston!!!\n");
				}
				break;
			case 4:
				System.out.print("\nZgjidh nje shofer: ");
				 emri = input.next();						//shfaq statistikat per nje shofer
				 boolean ndodhet = false;
				for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
					ShoferiFormula1 obj= iterator.next();
					    if (obj.getEmri().equals(emri)) {
					    	System.out.print("\nEmri:\t\t\t"+obj.getEmri());
					    	System.out.print("\nMbiemri:\t\t"+obj.getMbiemri());
					    	System.out.print("\nVenddodhja:\t\t"+obj.getVendndodhja());
					    	System.out.print("\nMosha:\t\t\t"+obj.getMosha());
					    	System.out.print("\nEkipi:\t\t\t"+obj.getEkipi());
					    	System.out.print("\nVendi Pare:\t\t"+obj.getSasia_vendiPare());
					    	System.out.print("\nVendi Dyte:\t\t"+obj.getSasia_vendiDyte());
					    	System.out.print("\nVendi Trete:\t\t"+obj.getSasia_vendiTrete());
					    	System.out.print("\nNumri Garave:\t\t"+obj.getNumri_garave());
					    	System.out.print("\nPiket:\t\t\t"+obj.getNumri_pikeve());
					    	ndodhet = true;
					    }
					}
				if(!ndodhet) {
					System.err.println("\\n\tShoferi "+emri+" nuk ndodhet ne Liste!\n");
				}
				break;
			case 5:						//regjistron nje gare
				Scanner scan = new Scanner(System.in);
				String vendi_mbajtjes;
				LocalDate data; 
				String[] drivers = new String[10];
				
				System.out.print("\nJepni Vendin ku u mbajt Gara: ");
				vendi_mbajtjes = scan.nextLine();
				
				System.out.print("\nJepni daten e mbajtjes se gares: ");
				String temp = scan.nextLine();
				data = LocalDate.parse(temp);
				
				System.out.print("\nPlotesoni rezultatet e gares: ");
				for(int k = 0; k<10;k++) {
					System.out.println("Vendi "+(k+1)+" : ");
					drivers[k] = scan.nextLine();
					FMK.Modifiko_Automatikisht(drivers[k],(k+1));
				}
				
				Gara gare = new Gara(vendi_mbajtjes,data,drivers);
				FMK.shto_Gare(gare);							//shton nje gare ne liste
				System.out.println("\n\tGara u shtua me Sukses!\n\n");
				break;
			case 6:				//ruan shoferet ne nje file
				FMK.ruaj_Shoferet();
				break;
			case 7: 
				//therret guin duke shfaqur tabelen e statistikave
				FormulaGui gui = new FormulaGui(FMK.getNumriShofereve());
				break;
			case 8:
				for (Iterator<Gara> iterator = garat.iterator(); iterator.hasNext();) {
					Gara obj= iterator.next();
					    System.out.println(obj.toString());
					}
				break;
			default:
				System.out.println("\nInput i gabuar. Provo perseri !\n\n");
				break;	
			}
		}while(zgjedhja!=0);
	//	System.out.println(shoferet);
		//input.close();
	}
	
	@SuppressWarnings("resource")
	@Override						//Kjo metode merr te gjithe te dhenat per nje shofer nga perdoruesi dhe e shton ne list
	public void Shto_Shofer() {
		Scanner scan = new Scanner(System.in);
		 String emri;
		 String mbiemri;
		 String vendndodhja;
		 String ekipi;
		 int mosha;
		 int temp;
		 int piket=0;
		boolean ekziston=false;
		ShoferiFormula1 shoferi = new ShoferiFormula1();
		 		System.out.print("\nJepni emrin e shoferit qe doni te shtoni: ");
				emri = scan.nextLine();
				System.out.print("\nJepni mbiemrin e shoferit qe doni te shtoni: ");
				mbiemri = scan.nextLine();
				System.out.print("\nJepni vendodhjen e shoferit qe doni te shtoni: ");
				vendndodhja = scan.nextLine();
				System.out.print("\nJepni ekipin e shoferit qe doni te shtoni: ");
				ekipi = scan.nextLine();
				
				//System.out.println(shoferet);
				
				try { 				//kontrrollojme nese ekziston nje shofer ose ekip me emrat e dhene
					for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
						ShoferiFormula1 obj= iterator.next();
						    if (obj.getEmri().equals(emri) || obj.getEkipi().equals(ekipi)) {
						    	ekziston = true;
						        throw new Exception("\n\tEkziston nje shofer(ekip) me kete emer!\n");
						    }
						}
				}catch(Exception e) {
					System.err.println(e.getMessage());
				}
				if(!ekziston) {
				
					shoferi.setEmri(emri);
					shoferi.setMbiemri(mbiemri);
					shoferi.setVendndodhja(vendndodhja);
					shoferi.setEkipi(ekipi);
					
					System.out.print("\nJepni moshen e shoferit qe doni te shtoni: ");
					mosha = scan.nextInt();
					shoferi.setMosha(mosha);
					System.out.print("\nJepni numrin e vendeve te para te fituara : ");
					temp = scan.nextInt();
					shoferi.setSasia_vendiPare(temp);
					piket+=temp*25;
					
					System.out.print("\nJepni numrin e vendeve te dyta te fituara : ");
					temp = scan.nextInt();
					shoferi.setSasia_vendiDyte(temp);
					piket+=temp*18;
					
					System.out.print("\nJepni numrin e vendeve te trete te fituara : ");
					temp = scan.nextInt();
					shoferi.setSasia_vendiTrete(temp);
					piket+=temp*15;
					
					shoferi.setNumri_pikeve(piket);
					
					System.out.print("\nJepni numrin e garave pjesmarrese : ");
					
						mosha = scan.nextInt();
					shoferi.setNumri_garave(mosha);
					
					shoferet.add(shoferi);
					this.nr_Shofereve+=1;
					ruaj_Shoferet();
				}
	}
	@Override
	public void Menu() {
		System.out.print("\n\n--------------------*****Menuja*****--------------------\n\n"
					+ "1 --> Krijo Shofer\n"
					+ "2 --> Fshi Shofer/skuader\n"
					+ "3 --> Ndrysho shofer\n"
					+ "4 --> Shfaq Satistika shoferi\n"
					+ "5 --> Shto Gare\n"
					+ "6 --> Ruaj\n"
					+ "7 --> Shfaq tabelen e Statistikave\n"
					+ "0 --> Dil\n\n"
					+ "Zgjedhja: ");
	}
	@Override
	public int fshi_Shofer(String emri, int NrShofereve) {
		boolean isShofer = false;
				
	
			for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
				ShoferiFormula1 obj= iterator.next();
				    if (obj.getEmri().equals(emri)) {
				    	isShofer =  true;
				    }
		}	
		
		if(isShofer) {
			for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
				ShoferiFormula1 obj= iterator.next();
				    if (obj.getEmri().equals(emri)) {
				       iterator.remove();
				    }
				}
			System.out.println("\n\tShofer "+emri+" u fshi nga lista!!\n\n");
			NrShofereve--;
			ruaj_Shoferet();
		}
		else if(!isShofer)
			System.err.println("\n\tNuk ekziston asnje shofer me emrin "+emri+"!\n\n");
		return NrShofereve;
	}
	@Override
	public void ndrysho_Shofer(String ekipi, String emri, String mbiemri) {
		boolean eshte = false;
		for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
					ShoferiFormula1 obj= iterator.next();
					    if (obj.getEmri().equals(emri) && obj.getMbiemri().equals(mbiemri)) {
					    	System.err.println("\n\tShoferi ndoshet tashme ne nje shkuader!!\n\n");
					    	eshte = true;
					    	break;
					    }
					}
		if(!eshte) {
			for (Iterator<ShoferiFormula1> iterator2 = shoferet.iterator(); iterator2.hasNext();) {
				ShoferiFormula1 obj= iterator2.next();
				    if (obj.getEkipi().equals(ekipi)) {
				    	obj.setEmri(emri);
				    	obj.setMbiemri(mbiemri);
				    }
				}
			System.out.println("\nShoferi i ekipit "+ekipi+" u ndryshua!!\n");
		}
		ruaj_Shoferet();
	}
	@Override
	public void shto_Gare(Gara gare) {
		
		garat.add(gare);
		ruaj_Garat();
	}
	public void Modifiko_Automatikisht(String shoferi, int pozicioni) {
		for (Iterator<ShoferiFormula1> iterator = shoferet.iterator(); iterator.hasNext();) {
			ShoferiFormula1 obj= iterator.next();
		    if (obj.getEmri().equals(shoferi)) {
		    	
		    	switch(pozicioni) {
		    	case 1:
		    		obj.setSasia_vendiPare(obj.getSasia_vendiPare()+1);
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+25);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 2:
		    		obj.setSasia_vendiDyte(obj.getSasia_vendiDyte()+1);
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+18);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 3:
		    		obj.setSasia_vendiTrete(obj.getSasia_vendiTrete()+1);
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+15);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 4:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+12);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 5:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+10);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 6:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+8);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 7:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+6);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 8:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+4);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 9:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+2);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	case 10:
		    		obj.setNumri_pikeve(obj.getNumri_pikeve()+1);
		    		obj.setNumri_garave(obj.getNumri_garave()+1);
		    		break;
		    	}
		    	
		    }
		}
		ruaj_Shoferet();
	}
	@Override
	public void ruaj_Shoferet() {
		empty();
		 try {
	            FileOutputStream ruaj = new FileOutputStream("Shoferet.txt", true);
	            try {
	                ObjectOutputStream ruajShofer = new ObjectOutputStream(ruaj);
	                for (Object obj : shoferet){
	                    try {
	                        ruajShofer.writeObject(obj);
	                       // System.out.println("saved");
	                    } catch (NotSerializableException e) {
	                        System.out.println("An object was not serializable, it has not been saved.");
	                        e.printStackTrace();
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
		
	}
	
	@Override
	public void ruaj_Garat() {
		emptyGarat();
		 try {
	            FileOutputStream ruaj = new FileOutputStream("Garat.txt", true);
	            try {
	                ObjectOutputStream ruajGare = new ObjectOutputStream(ruaj);
	                for (Object o : garat){
	                    try {
	                        ruajGare.writeObject(o);
	                       
	                    } catch (NotSerializableException e) {
	                        System.out.println("An object was not serializable, it has not been saved.");
	                        e.printStackTrace();
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public int Lexo_Shoferet() {
		  
		boolean cont = true;
		int i=0;
        try {
            ObjectInputStream lexo = new ObjectInputStream(new FileInputStream("Shoferet.txt"));
            while(cont){
                  Object obj=null;
                try {
                    obj = lexo.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            //	e.printStackTrace();
	            	break;
	            }
                
                  if(obj != null) {
                     shoferet.add((ShoferiFormula1) obj);
                     i++;
                  }
                  else
                     cont = false;
               }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       //System.out.println("Numri i shofereve: "+i);
        return i;
	}
	
	@Override
	public void lexo_Garat() {
		boolean cont = true;
        try {
            ObjectInputStream lexo = new ObjectInputStream(new FileInputStream("Garat.txt"));
            while(cont){
                  Object obj=null;
                try {
                    obj = lexo.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
	            }catch (EOFException e) {
	            	e.getMessage();
	            	break;
	            }
                
                  if(obj != null)
                     garat.add((Gara) obj);
                  else
                     cont = false;
               }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	
	@Override
	public void empty() {
		File file = new File("Shoferet.txt");
		try {
	        PrintWriter writer = new PrintWriter(file);
	        writer.print("");
	        writer.flush();
	        writer.close();

	    }catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	public void emptyGarat() {
		File file = new File("Garat.txt");
		try {
	        PrintWriter writer = new PrintWriter(file);
	        writer.print("");
	        writer.flush();
	        writer.close();

	    }catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	public int getNumriShofereve() {
	//	System.out.println("nr shofereve "+nr_Shofereve);
		return nr_Shofereve;
	}
	public List<ShoferiFormula1> getShoferet(){
		return this.shoferet;
	}
	public List<Gara> getGarat(){
		return this.garat;
	}
	public void setNumriShofereve(int nr) {
		this.nr_Shofereve=nr;
	}
}
