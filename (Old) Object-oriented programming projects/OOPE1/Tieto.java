package tiedot;

import java.util.*;

/**abstrakti Tieto-luokka, joka vastaa nimen tallentamisesta
  *<p>
  *harjoitustyö Oope, kevät 2017
  *<p>
  *@author Valtteri Pohjola (pohjola.m.valtteri@student.uta.fi)
  */
public abstract class Tieto implements Comparable<Tieto> {
	/** StringBuilder johon hakemisto/tiedoston nimi tallennetaa */
	private StringBuilder nimi;

	final String error = "Error!";
	/** rakentaja tiedolle
	  *@param tiedon nimi
	  *@throws IllegalArgumentException jos nimi on väärin.
	  */
	public Tieto(StringBuilder n){
		String s = n.toString();
		if(n != null && s.matches("^[-a-zA-Z0-9._]*[a-zA-Z0-9_]{1}") && testaaja(s)){
			nimi = n;
		} else {
			throw new IllegalArgumentException(error);
		}
	}


	public Tieto(Tieto n){
		if(n instanceof Tieto){
			StringBuilder kopioNimi = new StringBuilder(n.nimi());
			nimi(kopioNimi);
		}
	}

	public void nimi(StringBuilder n){
		if(n != null){
			nimi = n;
		} else {
			throw new IllegalArgumentException(error);
		}
	}

	public StringBuilder nimi(){
		return nimi;
	}

	@Override
	public int compareTo(Tieto n) {
		String tiedostoNimi = nimi.toString();
		return tiedostoNimi.compareTo(n.nimi().toString());
	}

	@Override
	public String toString(){
		String n = nimi.toString();
		return n;
	}

	@Override
	public boolean equals(Object o){
		if(o != null && o instanceof Tieto){
			Tieto t = (Tieto)o;
			boolean samat = nimi().toString().equals(t.nimi().toString());
			if(samat){
				return true;
			}
		}
		return false;
	}
	//testaa löytyyko pistetä liikaa
	public boolean testaaja(String s){
		int pisteMaara = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '.'){
				pisteMaara++;
			}
		}
		if(pisteMaara <= 1){
			return true;
		}
		return false;
	}
}