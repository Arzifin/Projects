/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package omalista;

import apulaiset.*;
import fi.uta.csjola.oope.lista.*;
/**
 *
 * @author Valtte
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva{

	@Override
	public Object hae(Object haettava){
		if(haettava != null){
		for(int i = 0; i < koko(); i++){
				if(alkio(i).equals(haettava) || alkio(i) == haettava){
					return alkio(i);
				}

			}

		}
		return null;
	}



	@Override
	public boolean lisaa(Object uusi){
		if(uusi != null){
			if(koko() == 0){
				lisaaAlkuun(uusi);
				return true;
			} else {
				int k = 0;
				for(int i = 0; i < koko(); i++){

					Object nykyinen = alkio(i);
					Comparable vertailtava = (Comparable)nykyinen;
					if(vertailtava.compareTo(uusi) > 0){
						lisaa(i, uusi);
						return true;
					} else if (vertailtava.compareTo(uusi) < 0){
						k++;
					} else if (vertailtava.compareTo(uusi) == 0){
						lisaa(i, uusi);
						return true;
					}

				}
				if(k == koko()){
					lisaaLoppuun(uusi);
					return true;
				}else if(k > 0){
					lisaa(k - 1, uusi);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Object poista(Object poistettava){

		if(poistettava != null){

			for(int i = 0; i < koko(); i++){
				if(alkio(i).equals(poistettava)){
					return poista(i);
				} 
			}

		}

		return null;
	}
}