import apulaiset.*;
/**Ui-luokka, joka vastaa tiedon esittämisestä käyttäjälle.
  *<p>
  *harjoitustyö Oope, kevät 2017
  *<p>
  *@author Valtteri Pohjola (pohjola.m.valtteri@student.uta.fi)
  */
public class Ui {
	/** vakiotulosteet */
	final String md = "md";
	final String mf = "mf";
	final String mv = "mv";
	final String cd = "cd";
	final String ls = "ls";
	final String cp = "cp";
	final String find = "find";
	final String rm = "rm";
	final String exit = "exit";
	final String error = "Error!";
	final String wel = "Welcome to SOS.";
	final String b = "Shell terminated.";

	private Tulkki tulkki;
	//Oletusrakentaja kayttoliittymalle, luo tulkin ja suorittaa kaynnista metodin
	/** Ui oletusrakentaja, joka suorittaa suoritus silmukan */
	public Ui(){
		tulkki = new Tulkki();
		suorita();
	}
	/** Suoritus metodi, jossa kysytään käyttäjän syötteet ja try-catchätään errorit */
	public void suorita(){
		String syote = "";
		System.out.println(wel);
		String[] komennot = new String[3];
		do {
			try{
				System.out.print(tulkki.polkuTulostus(tulkki.nykyinen()) + ">");
				syote = In.readString();
				komennot = jako(syote);
				if(komennot[0].equals(md)){
					tulkki.mdMethod(komennot[1]);
				} else if(komennot[0].equals(cd)){
					if(komennot[1] != null){
						tulkki.cdMethod(komennot[1]);
					} else {
						tulkki.cdMethod();
					}
				} else if(komennot[0].equals(mf)){
					int koko = Integer.parseInt(komennot[2]);
					tulkki.mfMethod(komennot[1], koko);
				} else if(komennot[0].equals(rm) && komennot[2] == null){
					tulkki.rmMethod(komennot[1]);
				}else if(komennot[0].equals(cp)){
					tulkki.cpMethod(komennot[1], komennot[2]);
				}else if(komennot[0].equals(mv)){
					tulkki.mvMethod(komennot[1], komennot[2]);
				}else if(komennot[0].equals(ls)){
					if(komennot[1] != null && komennot[2] == null){
						tulkki.lsMethod(komennot[1]);
					} else if(komennot[1] == null) {
						tulkki.lsMethod();
					} else {
						System.out.println(error);
					}
				}else if(komennot[0].equals(find) && komennot[1] == null){
					tulkki.find();
				} else if (!komennot[0].equals(exit)) {
					System.out.println(error);
				}
			} catch (Exception e) {
				System.out.println(error);
			}


		} while(!syote.equals(exit));
		System.out.println(b);
	}
	/**	*Jako-metodi, joka paloittelee alkuperäisen käyttäjän syötteen
		*@param käyttäjän syöte
		*@return String-tyyppinen taulukko, jossa on käyttäjän syötteet eri soluissa.
		*/
	public String[] jako(String t){
		String[] jakoTaulu = new String[3];
		String komento = "";
		String nimi = "";
		String koko = "";
		int k = 0;
		int maara = 0;
		for(int i = 0; i < t.length(); i++){
			//lisaa sanan katkaisun jälkeen.
			if(t.charAt(i) == ' '){
				komento = t.substring(k, i);
				jakoTaulu[maara] = komento;
				maara++;
				k = i+1;
			//jos valilyontia ei loydy otetaan komento suoraan
			} else if (i == t.length()-1){
				komento = t.substring(k, i + 1);
				jakoTaulu[maara] = komento;
			}
		}
		//testi
/*			for(int i = 0; i < jakoTaulu.length; i++){
			System.out.println(jakoTaulu[i] + ".");
		}  */

		return jakoTaulu;
	}

}