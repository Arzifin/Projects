package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Syvakopioituva;

/**
 * Tiedosto-luokka
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public class Tiedosto extends Tieto implements Syvakopioituva<Tieto> {
    /* Vakiot */
    final String error = "Error!";
    /* Attribuutit */
    private int koko = 0;
    /* Rakentajat */
    /* Asetetaan oletusrakentajassa pyydetty muuttujan arvo. */
    public Tiedosto() {
        koko = 0;
    }
    public Tiedosto(StringBuilder mjr, int n){
        super(mjr);
        if(n >= 0) {
            koko(n);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /* Aksessorit */
    public int koko () {
        return koko;
    }
    public void koko(int koko) {
        if (koko >= 0) {
            this.koko = koko;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    /* Metodit */
    @Override
    public String toString() {
        return super.toString() + " " + koko;
    }
    
    @Override
    public Tieto kopioi() {
        return null;
    }
}
