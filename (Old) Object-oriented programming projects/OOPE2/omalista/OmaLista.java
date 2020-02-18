package harjoitustyo.omalista;
import harjoitustyo.apulaiset.Ooperoiva;
import java.util.LinkedList;
        
/**
 * OmaLista-luokka, joka perii javan LinkedListin 
 * ja toteuttaa Ooperoiva rajapintaa.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    
    /* Lisää uuden alkion listaan eri tavoin riippuen listan koosta */
    @SuppressWarnings({"unchecked"})
    @Override
    public boolean lisaa(E uusi) {
        int kerroin = 0;
        if (uusi != null && uusi instanceof Comparable) {
            if (size() == 0) {
                addFirst(uusi);
                return true;
            } 
            else {
                for (int i = 0; i < size(); i++) {
                    Object olio = get(i);
                    Comparable olionArvo = (Comparable)olio;
                    if (olionArvo.compareTo(uusi) < 0) {
                        kerroin++;
                    }
                    else if (olionArvo.compareTo(uusi) > 0) {
                        add(i, uusi);
                        return true;
                    }
                    else if (olionArvo.compareTo(uusi) == 0) {
                        add(i, uusi);
                        return true;
                    }
                }
                if (kerroin == size()) {
                    addLast(uusi);
                    return true;
                } 
                else if (kerroin > 0) {
                    add(kerroin - 1, uusi);
                    return true;
                }
            }
        }
        return false;
    }    
    
    /* Poistaa listalta viitteet, jotka liittyvät parametrin alkioon. */
    @Override
    public int poista(E poistettava) {
        int poistetut = 0;
        if (poistettava != null && poistettava instanceof Comparable) {
            for (int i = 0; i < size(); i++) {
                if (get(i) == poistettava) {
                    remove(i);
                    poistetut++;
                } 
            }
            /* Toinen tarkistus, jos on useampi poistettava.
                Laskukerroin tykkää hypätä lähellä olevia arvoja, 
                * kun on poistanut viitteen. */
            for (int i = 0; i < size(); i++) {
                if (get(i) == poistettava) {
                    remove(i);
                    poistetut++;
                } 
            }
        }
        return poistetut;
    }
}

