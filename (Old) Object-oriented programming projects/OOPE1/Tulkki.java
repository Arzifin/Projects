import tiedot.*;
/**Tulkki-luokka, joka vastaa ohjelman toiminnallisuudesta.
  *<p>
  *harjoitustyö Oope, kevät 2017
  *<p>
  *@author Valtteri Pohjola (pohjola.m.valtteri@student.uta.fi)
  */
public class Tulkki {
	/**	otetaan talteen juuri hakemisto ja nykyinen hakemisto */
	private Hakemisto juuri;
	private Hakemisto nykyinen;

	final String ylos = "..";
	final String error = "Error!";
	//oletusrakentaja tulkille, luo juuren ja tekee siitä nykyisen.
	/** Tulkin oletusrakentaja, joka luo juuren */
	public Tulkki(){
		juuri = new Hakemisto(new StringBuilder("j"), null);
		nykyinen(juuri);
	}
	/**	*nykyisen hakemiston aksessori 
		*@param Hakemisto, joka asetetaan nykyiseksi.
		*@throws IllegalArgumentException
	*/
	public void nykyinen(Hakemisto n){
		if(n != null){
			nykyinen = n;
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	/** nykyisen hakemiston aksessori */
	public Hakemisto nykyinen(){
		return nykyinen;
	}
	/**	
    * Metodi, joka luo hakemiston
	* @param Nimi hakemistolle.
	* @throws IllegalArgumentException jos parametrissa oli virhe.
	*/
	public void mdMethod(String n){
		Hakemisto alahakemisto = new Hakemisto(new StringBuilder(n), nykyinen);
		if(nykyinen.lisaa(alahakemisto)){
			nykyinen.lisaa(alahakemisto);
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	//liikkuminen järjestelmässä
	//parametrillinen cd-metodi
	/**	*Hakemiston vaihtometodi
		*@param Hakemiston nimi johon siirrytään.
		*@throws IllegalArgumentException
	*/
	public void cdMethod(String s){
		if(s.equals(ylos) && nykyinen.ylihakemisto() != null){
			nykyinen = nykyinen.ylihakemisto();
		} else if (nykyinen.hae(s) != null && nykyinen.hae(s) instanceof Hakemisto){
			nykyinen = (Hakemisto)nykyinen.hae(s);
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	/**	*Parametriton Hakemiston vaihtometodi
		*@throws IllegalArgumentException
	*/
	//cd:n kuormittava metodi ilman parametria
	public void cdMethod(){
		nykyinen = juuri;
	}
	//luo tiedoston
	/**	*Tiedoston luontimetodi
		*@param uuden tiedoston nimi
		*@param uuden tiedoston koko
		*@throws IllegalArgumentException
	*/
	public void mfMethod(String s, int k){
		if(s != null && k > 0) {
			Tiedosto tiedosto = new Tiedosto(new StringBuilder(s), k);
			if(nykyinen.lisaa(tiedosto)){
				nykyinen.lisaa(tiedosto);
			} else {
				throw new IllegalArgumentException(error);
			}
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	//poistaminen
	/**	*Poistometodi tiedostojen ja hakemistojen poistamiseen.
		*@param Tiedon nimi joka poistetaan
		*@throws IllegalArgumentException
	*/
	public void rmMethod(String s){
		if(nykyinen.hae(s) != null){
			nykyinen.poista(s);
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	//kopiointi
	/**	*Kopiointimetodi
		*@param Kopioitavan tiedon nimi
		*@param nimi kopiotiedostolle
		*@throws IllegalArgumentException
	*/
	public void cpMethod(String s, String kopioNimi){
		if(nykyinen.hae(s) != null && nykyinen.hae(kopioNimi) == null){
			Tiedosto kopio = new Tiedosto((Tiedosto) nykyinen.hae(s));
			kopio.nimi(new StringBuilder(kopioNimi));
			nykyinen.lisaa(kopio);
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	//uudelleen nimeäminen
	/**	*Uudelleen nimeämismetodi
		*@param nykyinen tiedoston nimi
		*@param uusi tiedoston nimi
		*@throws IllegalArgumentException
	*/
	public void mvMethod(String nimi, String uusiNimi){
		if(nykyinen.hae(nimi) != null && nykyinen.hae(uusiNimi) == null){
			Object o = (Object)nykyinen.hae(nimi);
			if(o instanceof Hakemisto){
				Hakemisto temp = (Hakemisto)nykyinen.hae(nimi);
				temp.nimi(new StringBuilder(uusiNimi));
				nykyinen.lisaa(temp);
				nykyinen.poista(nimi);
			} else {
				cpMethod(nimi, uusiNimi);
				rmMethod(nimi);
			}
		} else {
			throw new IllegalArgumentException(error);
		}
	}
	//tulostus
	/**	*Listausmetodi, joka tulostaa koko hakemiston sisällön
		*@throws IllegalArgumentException
	*/
	public void lsMethod(){
		//käydään nykyisen hakemiston sisältö läpi ja tulostetaan sen sisältö
		for(int i = 0; i < nykyinen.sisalto().koko(); i++){
			if(nykyinen.sisalto().alkio(i) instanceof Hakemisto){
				Hakemisto listattava = (Hakemisto)nykyinen.sisalto().alkio(i);
				System.out.println(listattava + " " + listattava.sisalto().koko());
			} else {
				System.out.println(nykyinen.sisalto().alkio(i));
			}
		}
	}
	//Parametrillinen ls-metodi joka kuormittaa
	/**	*parametrillinen listausmetodi
		*@param listattavan tiedon nimi
		*@throws IllegalArgumentException
	*/
	public void lsMethod(String n){
		if(nykyinen.hae(n) != null){
			if(nykyinen.hae(n) instanceof Hakemisto){
				Hakemisto haettava = (Hakemisto)nykyinen.hae(n);
				System.out.println(haettava + " " + haettava.sisalto().koko());
			} else {
				System.out.println(nykyinen.hae(n));
			}
		} else {
			throw new IllegalArgumentException(error);
		}
	}

	public void find(){
		findMethod(nykyinen);
	}
	//Parametrillinen ls-metodi joka kuormittaa
	/**	*rekursiivinen listaus
		*@param hakemisto jossa käyttäjä on.
	*/
	public void findMethod(Hakemisto nyk){
		for(int i = 0; i < nyk.sisalto().koko(); i++){
			if(nyk.sisalto().alkio(i) instanceof Hakemisto){
				Hakemisto tulostettava = (Hakemisto)nyk.sisalto().alkio(i);

				if(tulostettava.sisalto().koko() > 0 ){
					System.out.println(polkuTulostus(tulostettava) + " " + tulostettava.sisalto().koko());
					findMethod(tulostettava);
				} else {
					System.out.println(polkuTulostus(tulostettava) + " " + tulostettava.sisalto().koko());
				}
			} else {
				System.out.println(polkuTulostus(nyk) + nyk.sisalto().alkio(i));
			}
		}
	}


	//Metodi polun tulostamiseen
	/**	*Metodi polun tulostamiseen
		*@param Kansio jossa käyttäjä on sillä hetkellä.
		*@return merkkijono esitys polusta.
	*/
	public String polkuTulostus(Hakemisto nyt){
		String polku = "";
		String apuPolku = "";
		while(nyt != null){
			apuPolku = nyt.toString();

			polku = apuPolku + polku;

			nyt = nyt.ylihakemisto();

		}

		return polku;
	}


}