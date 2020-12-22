import java.io.*; 
import java.util.*;

/*
* Harjoitustyö 2
* Arttu Lehtola, Lehtola.Arttu.M@student.uta.fi
* Luodaan ohjelma, joka pystyy olemaan syvässä vuorovaikutuksessa tekstitiedostojen kanssa.
*/

public class LakiHT2 {
   // Tallentaa tekstitiedoston arvot omaan tauluun.
   public static char[][] tallentaja (File tiedosto) {
      int rivit = 0;
      int sarakkeet = 0;
      try {
         Scanner laskija = new Scanner(tiedosto);
         while (laskija.hasNextLine()) {
            String rivillinen = laskija.nextLine();
            rivit += 1;
            sarakkeet = rivillinen.length();
         }
      }
      catch (FileNotFoundException e) {
      }    
      catch (Exception e) {
      }
      char[][] taulu = new char[rivit][sarakkeet];
      // Luetaan ja tallennetaan tiedoston merkit rivi kerrallaan.
      try {
         Scanner lukija = new Scanner(tiedosto);
         for (int i = 0; i < taulu.length; i++) {
            String lause = lukija.nextLine();
            for (int j = 0; j < taulu[0].length; j++) {
               taulu[i][j] = lause.charAt(j);
            }
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("Invalid command-line argument!");
         return null;
      }    
      catch (Exception e) {
      }
      return taulu;
   }
   
   // Kääntää char taulun inttiin.
   public static int[][] numKaantaja (char[][] taulu) {
      int[][] merk = new int[1][1];
      char hashM = '#';
      char atM = '@';
      char jaM = '&';
      char dolM = '$';
      char proM = '%';
      char axM = 'x';
      char tahtiM = '*';
      char ooM = 'o';
      char viiM = '|';
      char huutM = '!';
      char ppisM = ';';
      char kpisM = ':';
      char hpisM = '\'';
      char pilkM = ',';
      char pisM = '.';
      char valiM = ' ';
      // Tarkistaa kaikki merkit ja korvaa sen mukaisesti.
      // Ei kuitenkaan jatka, jos annettu parametri on null.
      if (taulu != null) {
         int[][] numeroTaulu = new int[taulu.length][taulu[0].length];
         for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu[0].length; j++) {
               if (taulu[i][j] == hashM) {
                  numeroTaulu[i][j] =  0;
               }
               else if (taulu[i][j] == atM) {
                  numeroTaulu[i][j] = 1;
               }
               else if (taulu[i][j] == jaM) {
                  numeroTaulu[i][j] = 2;
               }
               else if (taulu[i][j] == dolM) {
                  numeroTaulu[i][j] = 3;
               }
               else if (taulu[i][j] == proM) {
                  numeroTaulu[i][j] = 4;
               }
               else if (taulu[i][j] == axM) {
                  numeroTaulu[i][j] = 5;
               }
               else if (taulu[i][j] == tahtiM) {
                  numeroTaulu[i][j] = 6;
               }
               else if (taulu[i][j] == ooM) {
                  numeroTaulu[i][j] = 7;
               }
               else if (taulu[i][j] == viiM) {
                  numeroTaulu[i][j] = 8;
               }
               else if (taulu[i][j] == huutM) {
                  numeroTaulu[i][j] = 9;
               }
               else if (taulu[i][j] == ppisM) {
                  numeroTaulu[i][j] = 10;
               }
               else if (taulu[i][j] == kpisM) {
                  numeroTaulu[i][j] = 11;
               }
               else if (taulu[i][j] == hpisM) {
                  numeroTaulu[i][j] = 12;
               }
               else if (taulu[i][j] == pilkM) {
                  numeroTaulu[i][j] = 13;
               }
               else if (taulu[i][j] == pisM) {
                  numeroTaulu[i][j] = 14;
               }
               else if (taulu[i][j] == valiM) {
                  numeroTaulu[i][j] = 15;
               }
               else {
                  System.out.println("Ei osumaa!");
               }
            }
         }
         merk = numeroTaulu;
      }
      return merk;     
   }
   // Kääntää int taulut chariin.
   public static char[][] merKaantaja (int[][] taulu) {
      char[][] nem = new char[1][1];
      char hashM = '#';
      char atM = '@';
      char jaM = '&';
      char dolM = '$';
      char proM = '%';
      char axM = 'x';
      char tahtiM = '*';
      char ooM = 'o';
      char viiM = '|';
      char huutM = '!';
      char ppisM = ';';
      char kpisM = ':';
      char hpisM = '\'';
      char pilkM = ',';
      char pisM = '.';
      char valiM = ' ';
      // Tarkistaa kaikki merkit ja korvaa sen mukaisesti.
      // Ei kuitenkaan jatka, jos annettu parametri on null.
      if (taulu != null) {
         char[][] merkkiTaulu = new char[taulu.length][taulu[0].length];
         for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu[0].length; j++) {
               if (taulu[i][j] == 0) {
                  merkkiTaulu[i][j] = hashM;
               }
               else if (taulu[i][j] == 1) {
                  merkkiTaulu[i][j] = atM;
               }
               else if (taulu[i][j] == 2) {
                  merkkiTaulu[i][j] = jaM;
               }
               else if (taulu[i][j] == 3) {
                  merkkiTaulu[i][j] = dolM;
               }
               else if (taulu[i][j] == 4) {
                  merkkiTaulu[i][j] = proM;
               }
               else if (taulu[i][j] == 5) {
                  merkkiTaulu[i][j] = axM;
               }
               else if (taulu[i][j] == 6) {
                  merkkiTaulu[i][j] = tahtiM;
               }
               else if (taulu[i][j] == 7) {
                  merkkiTaulu[i][j] = ooM;
               }
               else if (taulu[i][j] == 8) {
                  merkkiTaulu[i][j] = viiM;
               }
               else if (taulu[i][j] == 9) {
                  merkkiTaulu[i][j] = huutM;
               }
               else if (taulu[i][j] == 10) {
                  merkkiTaulu[i][j] = ppisM;
               }
               else if (taulu[i][j] == 11) {
                  merkkiTaulu[i][j] = kpisM;
               }
               else if (taulu[i][j] == 12) {
                  merkkiTaulu[i][j] = hpisM;
               }
               else if (taulu[i][j] == 13) {
                  merkkiTaulu[i][j] = pilkM;
               }
               else if (taulu[i][j] == 14) {
                  merkkiTaulu[i][j] = pisM;
               }
               else if (taulu[i][j] == 15) {
                  merkkiTaulu[i][j] = valiM;
               }
            }
         }
         nem = merkkiTaulu;
      }         
      return nem; 
   }
   
   // Hoitaa tehtävän filtteroinnin.
   public static int[][] suodatin (int[][] taulu, String syote) {
      String FILTER = "filter";
      int[][] uusiTaulu = kopioiTaulukko(taulu);
      int numero = 0;
      int syotePituus = syote.length();
      if (syotePituus == 9) {
         char ekaNro = syote.charAt(7);
         char tokaNro = syote.charAt(8);
         String nroJono = "";
         nroJono += ekaNro;
         nroJono += tokaNro;
         numero = Integer.parseInt(nroJono);
      }
      else if (syotePituus == 8) {
         char nro = syote.charAt(7);
         int iNro = nro - '0';
         numero = iNro;
      }
      else if (syotePituus > 9) {
         System.out.println("Liian suuri!");
         return null;
      }
      else if (syotePituus == 6) {
         numero = 3;
      }
      else {
         System.out.println("Laita filtterin koko!");
      }
      int pariton = numero % 2;
      if (numero > 15 || numero < 3 || pariton == 0 && taulu == null) {
         return null;
      }
      else {
         int vali = numero / 2;
         int[] tulokset = new int[(taulu.length-(2*vali))*(taulu[0].length-(2*vali))];
         double laskualusta = 0;
         double tulos = 0;
         int laskenta = 0;
         int pTulos = 0;
         boolean saa = true;
         int o = 0;
         // Uloimmat for-lauseet liikuttelevat laatikkoa rivi kerrallaan taululla.
         // Sisäpari laskee ja tallentaa yksiuloitteiseen tauluun halutut arvot.
         for (int i = vali; i + vali < taulu.length; i++) {
            for (int j = vali; j + vali < taulu[0].length; j++) {
               saa = true;
               for (int k = 0; k < numero; k++) {
                  for (int l = 0; l < numero; l++) {
                     if ((i+k)-vali < taulu.length && (j+l)-vali < taulu[0].length) {
                        laskualusta += taulu[(i+k)-vali][(j+l)-vali];
                     }
                     if (k + 1 == numero && l + 1 == numero && 
                     laskenta <= taulu.length * taulu[0].length) {
                        tulos = laskualusta / (numero * numero);
                        pTulos = (int) (tulos + 0.5);
                        tulokset[laskenta] = pTulos;
                        laskenta += 1;
                        pTulos = 0;
                        laskualusta = 0;
                     }
                  } 
               }
            }
         }
         // Sijoitetaan taulun arvot kopoituun tauluun.
         for (int i = vali; i + vali < uusiTaulu.length; i++) {
            for (int j = vali; j + vali < uusiTaulu[0].length; j++) {
               if (o < tulokset.length) {
                  uusiTaulu[i][j] = tulokset[o];
                  o += 1;
               }
            }
         }
      }
      return uusiTaulu;
   }
   // Laskee ja tulostaa haluttua tietoa käyttäjälle.
   public static void laskija (char[][] taulu) {
      int hash = 0;
      int at = 0;
      int ja = 0;
      int dol = 0;
      int pro = 0;
      int ax = 0;
      int tahti = 0;
      int oo = 0;
      int vii = 0;
      int huut = 0;
      int ppis = 0;
      int kpis = 0;
      int hpis = 0;
      int pilk = 0;
      int pis = 0;
      int vali = 0;
      char hashM = '#';
      char atM = '@';
      char jaM = '&';
      char dolM = '$';
      char proM = '%';
      char axM = 'x';
      char tahtiM = '*';
      char ooM = 'o';
      char viiM = '|';
      char huutM = '!';
      char ppisM = ';';
      char kpisM = ':';
      char hpisM = '\'';
      char pilkM = ',';
      char pisM = '.';
      char valiM = ' ';
      // Tarkistaa kaikki merkit ja lisää kertoimen löytäessään.
      if (taulu != null) {
         for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu[0].length; j++) {            
               if (taulu[i][j] == hashM) {
                  hash+=1;
               }
               else if (taulu[i][j] == atM) {
                  at+=1;
               }
               else if (taulu[i][j] == jaM) {
                  ja+=1;
               }
               else if (taulu[i][j] == dolM) {
                  dol+=1;
               }
               else if (taulu[i][j] == proM) {
                  pro+=1;
               }
               else if (taulu[i][j] == axM) {
                  ax+=1;
               }
               else if (taulu[i][j] == tahtiM) {
                  tahti+=1;
               }
               else if (taulu[i][j] == ooM) {
                  oo+=1;
               }
               else if (taulu[i][j] == viiM) {
                  vii+=1;
               }
               else if (taulu[i][j] == huutM) {
                  huut+=1;
               }
               else if (taulu[i][j] == ppisM) {
                  ppis+=1;
               }
               else if (taulu[i][j] == kpisM) {
                  kpis+=1;
               }
               else if (taulu[i][j] == hpisM) {
                  hpis+=1;
               }
               else if (taulu[i][j] == pilkM) {
                  pilk+=1;
               }
               else if (taulu[i][j] == pisM) {
                  pis+=1;
               }
               else if (taulu[i][j] == valiM) {
                  vali+=1;
               }
               else {
                  System.out.println("Ei osumaa! Onko taulusi tyhja?");
               }
            }
         }
         System.out.println(taulu.length + " x " + taulu[0].length);
         System.out.println("# "+hash);
         System.out.println("@ "+at);
         System.out.println("& "+ja);
         System.out.println("$ "+dol);
         System.out.println("% "+pro);
         System.out.println("x "+ax);
         System.out.println("* "+tahti);
         System.out.println("o "+oo);
         System.out.println("| "+vii);
         System.out.println("! "+huut);
         System.out.println("; "+ppis);
         System.out.println(": "+kpis);
         System.out.println("' "+hpis);
         System.out.println(", "+pilk);
         System.out.println(". "+pis);
         System.out.println("  " +vali);  
      }
   }
   
   // Kopioi annetun taulukon.
   public static int[][] kopioiTaulukko (int[][] taulu) {
      return taulu;
   }
   
   // Printtaa char muodossa olevia tauluja.
   public static void merTulostaja (char[][] taulu) {
      if (taulu != null) {
         for (int i = 0; i < taulu.length; i++) {
            if (i > 0) {   
               System.out.println();
            }
            for (int j = 0; j < taulu[0].length; j++) {
               System.out.print(taulu[i][j]);
            }
         }
         System.out.println();
      }
      else {
         System.out.println("Ei ole varattu tilaa. Hupsista!");
      }
   }
   
   // Printtaa int muodossa olevia tauluja.
   public static void numTulostaja (int[][] taulu) {
      if (taulu != null) {
         for (int i = 0; i < taulu.length; i++) {
            if (i > 0) {   
               System.out.println();
            }
            for (int j = 0; j < taulu[0].length; j++) {
               if (taulu[i][j] < 10) {
                  System.out.print(" ");
               }
               System.out.print(taulu[i][j]);
               if (j+1 != taulu[0].length) {
                  System.out.print(" ");
               }
            }
         }
         System.out.println();
      }
      else {
         System.out.println("Ei ole varattu tilaa. Hupsista!");
      }
   }

   // Pääoperaatio, jossa toteutetaan esittelyt ja kyselyt.
   public static void main (String[] args) {
      boolean jatka = true;
      String PRNTA = "printa";
      String PRNTI = "printi";
      String INF = "info";
      String FILT = "filter";
      String RES = "reset";
      String QIT = "quit";
      System.out.println("-------------------");
      System.out.println("| A S C I I A r t |");
      System.out.println("-------------------");   
      String testi = "";
      try {
         testi = args[0];
      }    
      catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid command-line argument!");
      }
      File tiedosto = new File(args[0]);
      String tiedostoNimi = args[0];
      char[][] taulu = tallentaja(tiedosto); 
      if (taulu == null) {
         jatka = false;
      }          
      while (jatka) {   
         if (tiedostoNimi.contains(args[0])) {
            System.out.println("printa/printi/info/filter [n]/reset/quit?");
            String syote = In.readString();
            if (syote.contains(PRNTA)) {
               merTulostaja(taulu);
            }
            else if (syote.contains(PRNTI)) {
               int[][] kaannettyTaulu = numKaantaja(taulu);
               numTulostaja(kaannettyTaulu);
            }
            else if (syote.contains(INF)) {
               laskija(taulu);    
            }
            else if (syote.contains(FILT)) {
               int[][] numTaulu = numKaantaja(taulu);
               int[][] suoTaulu = suodatin(numTaulu, syote);
               taulu = merKaantaja(suoTaulu);
            }
            else if (syote.contains(RES)) {
               taulu = tallentaja(tiedosto);
            }
            else if (syote.contains(QIT)) {
               jatka = false;
            }
         }
         else {
            jatka = false;
         }
      }
      System.out.println("Bye, see you soon.");
   }
}