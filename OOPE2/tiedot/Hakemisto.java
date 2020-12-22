package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.omalista.OmaLista;
import java.util.LinkedList;


/**
 * Hakemisto-luokka.
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public class Hakemisto extends Tieto implements Sailova<Tieto> {
    /* Attribuutit */
    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;
    
    /* Rakentajat */
    public Hakemisto () {
        sisalto = new OmaLista<>();
        ylihakemisto = null;
    }
    public Hakemisto (StringBuilder mjr, Hakemisto viite) {
        super(mjr);

        ylihakemisto = viite;
        sisalto = new OmaLista<>();
    }
    /* Aksessorit */
    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }
    public void ylihakemisto(Hakemisto ylla) {
        ylihakemisto = ylla;
    }

    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }
    public void sisalto(OmaLista<Tieto> sisalto) {
        this.sisalto = sisalto;
    }
    
    /* Metodit */
    @Override
    public String toString() {
        return super.toString() + "/ " +  sisalto.size();
    }
    
    /* Yliajaa sailovan hae metodin. */
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        LinkedList<Tieto> lista = new LinkedList<>();
        if (hakusana != null && sisalto != null) {
            
            for (int i = 0; i < sisalto.size(); i++) {
                
                if (sisalto.get(i).equals(hakusana) ) {
                    //System.out.println(sisalto.get(i));
                    lista.add(sisalto.get(i));
                }
            }
            return lista;
        }
        else {
            lista = null;
        }
        return lista;
    }
    
    /* Yliajaa sailovan lisaa metodin. */
    @Override
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }
    
    /* Yliajaa sailovan poista metodin. */
    @Override
    public boolean poista(Tieto poistettava) {
        if (poistettava == null) {
            return false;
        }
        return sisalto.poista(poistettava) > 0;
    }
}
