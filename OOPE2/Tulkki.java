package harjoitustyo;
import harjoitustyo.tiedot.*;
import java.util.LinkedList;

/**
 * Tulkki-luokka
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public class Tulkki {
    /* Vakiot */
    final String error = "Error!";
    final String kp = "..";

    /* Attribuutit */
    private Hakemisto juuri;
    private Hakemisto kaytossa;
    
    /* Rakentajat */
    /* Oletusrakentaja, jossa luomme Hakemisto-olion */
    public Tulkki () {
        juuri = new Hakemisto();
        kaytossa(juuri);
    }
    /* Käytössä oleva hakemisto */
    public Hakemisto kaytossa () {
        return kaytossa;
    }
    /* Parametrillinen rakentaja */
    public void kaytossa (Hakemisto o) throws IllegalArgumentException {
        if (o != null){
            kaytossa = o;
        } 
        else {
            throw new IllegalArgumentException(error);
        }
    }
    /**
    * Metodi, joka luo hakemiston.
    * @param mj hakemistolle. 
    * @throws IllegalArgumentException jos parametrissa oli virhe.
    */
    public void mdM (String mj) throws IllegalArgumentException {
        int osuma = kaytossa.hae(mj).size();
        String[] osumat = new String[osuma];
        if (osumat.length == 0) {
            Hakemisto lisattava = new Hakemisto(new StringBuilder(mj), kaytossa);
            kaytossa.lisaa(lisattava);
        }
        else {
            throw new IllegalArgumentException(error);
        }
    }
   
    /**
    * cdM on metodi, joka vaihtaa hakemistoa
    * @param mj 
    * @throws IllegalArgumentException, 
    * jos komennon parametri on tyhjä/olematon.
    */
    public void cdM (String mj) throws IllegalArgumentException{
        LinkedList<Tieto> osumat = kaytossa.hae(mj);
        if (kaytossa.ylihakemisto() != null && mj.equals(kp)) {
            kaytossa = kaytossa.ylihakemisto();
        }
        else if (osumat.size() == 1
                && osumat.get(0) instanceof Hakemisto) {
            kaytossa = (Hakemisto)osumat.get(0);
        }
        else {
            throw new IllegalArgumentException(error);
        }
    }
    /**
    * cdM (ei kuormittava) on metodi, joka vaihtaa hakemistoa suoraan juureen
    */
    public void cdM () {
        kaytossa = juuri;
    }
    /**
    * Metodi, joka luo tiedoston.
    * @param mj annettu nimi
    * @param n annettu koko
    * @throws IllegalArgumentException, 
    * jos parametrissa oli virhe
    */
    public void mfM (String mj, int n) throws IllegalArgumentException {
        if (mj != null && n > 0) {
            int osuma  = kaytossa.hae(mj).size();
            String[] osumat = new String[osuma];
            if (osumat.length == 0) {
                Tiedosto t = new Tiedosto(new StringBuilder(mj), n);
                kaytossa.lisaa(t);
            }
            else {
                throw new IllegalArgumentException(error);
            }
        }
        else {
            throw new IllegalArgumentException(error);
       }
    }
    /**
    * mvM nimeää halutun tiedoston uudelleen
    * @param mj haluttu tiedosto
    * @param mjU haluttu uusi nimi
    * @throws IllegalArgumentException, 
    * jos parametrissa oli virhe
    */
    public void mvM (String mj, String mjU) throws IllegalArgumentException {
        boolean uusiTyhja = false;
        LinkedList<Tieto> uusiLista = kaytossa.hae(mjU);
        String testi = uusiLista.toString();
        if (testi.equals("[]")) {
            uusiTyhja = true;
        }
        if (kaytossa.hae(mj) != null && uusiTyhja) {
            if (kaytossa.hae(mj).get(0) instanceof Hakemisto) {
                Hakemisto v = (Hakemisto)kaytossa.hae(mj).get(0);
                Hakemisto u = (Hakemisto)kaytossa.hae(mj).get(0);
                u.nimi(new StringBuilder(mjU));
                kaytossa.poista(v);
                kaytossa.lisaa(u);
            }
            else {
                Tiedosto v = (Tiedosto)kaytossa.hae(mj).get(0);
                Tiedosto u = (Tiedosto)kaytossa.hae(mj).get(0);
                u.nimi(new StringBuilder(mjU));
                kaytossa.poista(v);
                kaytossa.lisaa(u);
            }
        }
        else {
            throw new IllegalArgumentException(error);
        }
    }
    /**
    * lsM tulostaa hakemiston sisällön
    * @throws IllegalArgumentException, 
    * jos parametrissa oli virhe
    */
    public void lsM () {
        int sisKoko = kaytossa.sisalto().size();
        for (int i = 0; i < sisKoko; i++) {
            if (kaytossa.sisalto().get(i) instanceof Hakemisto) {
                System.out.println(kaytossa.sisalto().get(i));
            }
            else {
                System.out.println(kaytossa.sisalto().get(i));
            }
        }
    }
    /**
    * (kuormittava) lsM tulostaa hakemiston sisällön
    * @param mj listattavan nimi
    * @throws IllegalArgumentException, 
    * jos parametrissa oli virhe.
    */
    public void lsM (String mj) throws IllegalArgumentException {
        int kayKoko = kaytossa.hae(mj).size();
        LinkedList<Tieto> satsumat = kaytossa.hae(mj);
        if ("*".equals(mj)) {
            lsM();
        }
        else if (kaytossa.hae(mj) != null) {
            int osuma = kayKoko;
            String[] osumat = new String[osuma];
            /* Placeholder, kunnes keksin parempaa */
            if (osumat.length == 0 && "*".equals(mj)) {
                lsM();
            }
            /* Jos saimme hausta osumia, niin printtaamme ne */
            else if (osumat.length > 0) {
                for (int i = 0; i < osuma;  i++) {
                    osumat[i] = kaytossa.hae(mj).get(i).toString();
                    System.out.println(osumat[i]);
                }
                
            }
            else {
                throw new IllegalArgumentException(error);
            }
        }
        else {
            throw new IllegalArgumentException(error);
        }
        
    }
    
    public void cpM () {
        
    }
    public void findM () {
        
    }
    
    /**
    * rmM on Metodi, joka poistaa tiedoston/hakemiston
    * @param mj hakemistolle
    * @throws IllegalArgumentException, 
    * jos parametrissa on virhe.
    */
    public void rmM (String mj) throws IllegalArgumentException {
        LinkedList<Tieto> osumat = kaytossa.hae(mj);
        if (osumat.size() > 0) {
            
            for (int i = 0; i < osumat.size(); i++) {
                Tieto kohta = (Tieto)osumat.get(i);
                kaytossa.poista(kohta);
                
                //System.out.println(kohta);
            }
            
        }
        else {
            throw new IllegalArgumentException(error);
        }
    }
    
    /**
    * kuorija on metodi, joka kuorii turhat merkit pois.
    * @param p, jota 'kuoritaan'. 
    * @return mj, joka on siistitty merkkijono
    * @throws IllegalArgumentException,
    * jos parametrissa on virhe.
    */
    public String kuorija (Tieto p) throws IllegalArgumentException {
        String vaultti = "";
        String sailio = p.toString();
        boolean kv = true;
        for (int i = 0; i < sailio.length(); i++) {
            if (sailio.charAt(i) == '/' && sailio.charAt(i+1) == ' ') {
                kv = false;
            }
            if (kv) {
                vaultti += sailio.charAt(i);
            }
        }
        return vaultti;
    }
    
    /**
    * polku yleistää printettavan hakemiston.
    * @param h, josta muovataan printattava merkkijono.
    * @return paluu, joka on siistitty hakemistopolku
    * @throws IllegalArgumentException, 
    * jos parametrissa on virhe.
    */
    public String polku (Hakemisto h) throws IllegalArgumentException {
        String sailio = "";
        String paluu = "";
        String vaultti = "";
        boolean kv = true;
        do {
            sailio = h.toString();
            for (int i = 0; i < sailio.length(); i++) {
                if (sailio.charAt(i) == '/' && sailio.charAt(i+1) == ' ') {
                    kv = false;
                }
                if (kv) {
                    vaultti += sailio.charAt(i);
                }
            }
            kv = true;
            vaultti += '/';
            h = h.ylihakemisto();
        }
        while (h != null);

        paluu = vaultti;
        return paluu;
    }
    
    /**
    * tauluttaja hyllyttää hakemiston
    * @param mj, joka hyllytetään tauluun
    * @return syote, joka on tauluun pilkottu merkkijono
    * @throws IllegalArgumentException, 
    * jos parametrissa on virhe.
    */
    public String[] tauluttaja(String mj) throws IllegalArgumentException {
        String[] syote = new String[1000];
        String jono = "";
        int kerroin = 0;
        if (mj != null) {   
            for (int i = 0; i < mj.length(); i++) {
                if (mj.charAt(i) != '/' && mj.charAt(i) != ' ') {
                    jono += mj.charAt(i);
                }
                else {
                    syote[kerroin] = jono;
                    kerroin++;
                    jono = "";
                }
                syote[kerroin] = jono;
            }
        }
        return syote;
    }
    
    /**
    * printti tulostaa polun käyttäjälle
    * @param taulu, josta rakentuu käyttäjän näkemä polku
    * @return jemma on mj, johon on kasattuna (kaikki) taulun arvot
    * @throws IllegalArgumentException jos parametrissa oli virhe.
    */
    public String printti (String[] taulu) throws IllegalArgumentException {
        String jemma = "";
        if (taulu != null) {
            for (int i = taulu.length-1; i >= 0; i--) {
                if (taulu[i] != null) {    
                    if (taulu[i].length()>=2) {    
                        jemma += "/";
                    }
                    jemma += taulu[i];
                }
            }
        }
        return jemma + "/>";
    }
    
}
