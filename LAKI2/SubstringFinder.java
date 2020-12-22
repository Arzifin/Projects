/*
* LAKI2, Harjoitustyö 1
* Arttu Lehtola, Lehtola.Arttu.M@student.uta.fi
* Ohjelma etsii merkkijonon päämerkkijonosta. 
* Tähdet pakottavat haun joko merkkijonon alkuun tai päätyyn.
*/

public class SubstringFinder {
   public static void main (String[]args) {
      // Esittelyt ja alustukset.
      System.out.println("Hello! I find substrings.");
      boolean tulosta = true;
      String tulostus = "";
      boolean jatka = true;
      char EI = 'n';
      char KYLLA = 'y';
      String VIRHE = "Error!";
      int tahtia = 0;
      boolean kysy = true;
      boolean alkutahti;
      boolean lopputahti;
      String sijainen = "";
      while (jatka) {
         // Resetoidaan / alustetaan booleanit.
         kysy = true;
         alkutahti = false;
         lopputahti = false;
         // Pyydetään käyttäjältä merkkijonot, joista
         // kyseinen ohjelma on riippuvainen.
         System.out.println("Please, enter a string:");
         String syote = In.readString();
         int syotePituus = syote.length();
         System.out.println("Please, enter a substring:");
         String aliSyote = In.readString();
         int aliSyotePituus = aliSyote.length();
         // Lasketaan alemman merkkijonon tähtien lukumäärä,
         // niin voimme käsitellä merkkijonoa sopivasti.
         // On tärkeää nollata tähdet, sillä muuten tähdet
         // akkumuloituvat vanhoilta kierroksilta.
         tahtia = 0;
         for (int i = 0; i < aliSyotePituus; i++) {
            if (aliSyote.charAt(i) == '*') {
               tahtia+=1;
            }
         }
         // Jos merkkijono on pienempi kuin alempi merkkijono tai
         // sisältää liian paljon tähtiä, niin while pyytää uutta
         // alempaa merkkijonoa, jossa ei ole liikaa tähtiä ja
         // alempi merkkijono ei ole pituudeltaan suurempi kuin
         // päämerkkijono.
         while (aliSyotePituus>syotePituus || tahtia>1) {
            System.out.println(VIRHE);
            System.out.println("Please, enter a substring:");
            aliSyote = In.readString();
            aliSyotePituus = aliSyote.length(); 
            tahtia = 0;
            // Ohjelma laskee kaikki tähdet lopuksi ja
            // tarkistaa, että tähtimäärä on sopiva.
            // Jos ei, niin while-lause alkaa alusta!
            for (int i = 0; i < aliSyotePituus; i++) {
               if (aliSyote.charAt(i) == '*') {
                  tahtia+=1;
               }
            }
         }
         // Poistetaan tähdet, niin vertailu on helpompaa.
         aliSyotePituus = aliSyote.length();
         if (aliSyote.charAt(aliSyotePituus-1) == '*') {
            sijainen = "";
            lopputahti = true;
            for (int i = 0; i < aliSyotePituus; i++) {
               if (aliSyote.charAt(i) == '*') {  
               }
               else {
                  sijainen += aliSyote.charAt(i);  
               }
            }
            aliSyote = sijainen;
         }
         else if (aliSyote.charAt(0) == '*') {
            sijainen = "";
            alkutahti = true;
            for (int i = 0; i < aliSyotePituus; i++) {
               if (aliSyote.charAt(i) == '*') {  
               }
               else {
                  sijainen += aliSyote.charAt(i);  
               }
            }
            aliSyote = sijainen;
         }
         aliSyotePituus = aliSyote.length();      
         for (int i = 0; i < syotePituus; i++) {
            int tulostusP = tulostus.length();
            int pErotus = syotePituus - tulostusP;
            if (alkutahti && tulosta) {
               if (i == syotePituus-aliSyotePituus) {
                  System.out.println(tulostus);
               }
            }
            else if (lopputahti && tulosta) {
               if (i == 1) {
                  for (int k = 0; k < pErotus; k++) {
                     tulostus += "-";
                  }
                  System.out.println(tulostus);
                  tulostus = "";
               }
            }
            else if (tulosta && i > 0) {
               for (int k = 0; k < pErotus; k++) {
                  tulostus += "-";
               }
               System.out.println(tulostus);
               tulostus = "";
            }   
            else {
               tulosta = false;
            }
            tulosta = true;
            tulostus = "";
            if (i > 0 && tahtia == 0) {
               for (int h = 0; h < i; h++) {
                  tulostus += "-";
               }
            }
            else if (alkutahti) {
               for (int p = 0; p < syotePituus-aliSyotePituus; p++) {
                  tulostus += "-";
               }
            }
            for (int j = 0; j < aliSyotePituus; j++) {
              if (alkutahti && i+j < syotePituus) {
                 if (aliSyote.charAt(j) == syote.charAt(syotePituus-aliSyotePituus + j)) {
                    tulostus += syote.charAt(syotePituus-aliSyotePituus + j);
                 }
                 else {
                    tulosta = false;
                 }
              }
              else if (lopputahti && i+j < syotePituus) {
                 if (aliSyote.charAt(j) == syote.charAt(j)) {
                    tulostus += syote.charAt(j);
                 }
                 else {
                    tulosta = false;
                 }
              }
              else if (i+j < syotePituus && tahtia == 0) {
                 if (aliSyote.charAt(j) == syote.charAt(i+j)) {
                    tulostus += syote.charAt(i+j);
                 }
                 else {
                    tulosta = false;
                 }
              }
              else {
                 tulosta = false;
              }
            }
         }
         // Kyseinen if-lause printtaa viimeiseltä kierrokselta jääneen printin.
         // Se ei kuitenkaan printtaannu, jos alisyote sisälsi tähtiä.
         if (tulosta && tahtia == 0) {
            System.out.println(tulostus);
         }
         // While-lause kysyy niin kauan, kunnes saamme y/n vastauksen.
         // Jos vastaus on 'n', niin muutamme booleanin "jatka" epätodeksi,
         // jolloin ohjelma ei aloita enää alusta.
         // Myös saatuaan haluaman vastauksensa y/n se muuttaa oman booleaninsa (kysy)
         // epätodeksi, jolloin se pystyy etenemään. Tämä boolean muuttuu todeksi
         // jatka while-lauseen alussa.
         while (kysy) {
            System.out.println("Continue (y/n)?");
            char vastaus = In.readChar();
            if (vastaus == EI) {
               System.out.println("See you soon.");
               kysy = false;
               jatka = false;
            }
            else if (vastaus == KYLLA) {
               jatka = true;
               kysy = false;
            }
            else {
               System.out.println(VIRHE);
            }
         }
      }
   }
}