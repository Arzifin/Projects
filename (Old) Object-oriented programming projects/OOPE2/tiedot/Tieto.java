package harjoitustyo.tiedot;
import harjoitustyo.apulaiset.Tietoinen;

/**
 * Tieto-luokka
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public abstract class Tieto implements Tietoinen, Comparable<Tieto> {
    /* Vakiot */
    final String error = "Error!";
    /* Attribuutit */
    private StringBuilder nimi;
    /*  Rakentajat */
    /** Asetetaan oletusrakentajassa tehtävänannon mukainen muuttujan arvo. */
    public Tieto () {
        nimi = new StringBuilder("");
    }
    public Tieto (StringBuilder mj){
        if (mj != null ) {
            nimi(mj);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    /* Aksessorit */
    public StringBuilder nimi() {
        return nimi;
    }
    public void nimi(StringBuilder mj) {
        String tmj = "";
        if (mj != null ) {
            tmj = mj.toString();
        }
        else {
            throw new IllegalArgumentException();
        }
        if (tmj.length() > 0 && tmj.matches("^[a-zA-Z0-9._]*$") 
                && mj != null && pisteTesti(tmj)) {
            nimi = mj;
        }
        else {
            throw new IllegalArgumentException(error);
        }
    }
    /* Metodit */
    @Override
    public String toString() {
        return nimi.toString();
    }
    
    @Override
    public boolean equals(Object olio) {
        //System.out.println("objekti equalssi");
        boolean identtiset = false;
        if (olio != null && olio instanceof Tieto) {
            Tieto olioTieto = (Tieto)olio;
            //System.out.println("olion nimi: " + olioTieto.nimi().toString());
            //System.out.println("tiedon nimi: " + nimi());
            identtiset = nimi().toString().equals(olioTieto.nimi().toString());
            if (identtiset){
                return true;
            }
        }
        return identtiset;
    }
    
    @Override
    public boolean equals(String hakusana) {
        //System.out.println("equals hakusana: " + hakusana);
        if (hakusana != null) {
            boolean p = true;
            if (jokeriLasku(hakusana) == true) {
                return jokeriTesti(hakusana);
            }
            if (jokeriLasku(hakusana) == false) {
                return nimi().toString().equals(hakusana);
            }
        } else {
            return false;
        }
        return false;
    }
    
    @Override
    public int compareTo(Tieto n) {
        String tn = nimi.toString();
        int t = tn.compareTo(n.nimi().toString());
        if (t < 0) {
            t = -1;
        }
        else if (t > 0) {
            t = 1;
        }
        else {
            t = 0;
        }
        return t;
    }
    /**
    * jokeriLasku tarkistaa jokerimerkkien olemassaolon
    * @param n tarkasteltava merkkijono
    * @return false, jos ei ole jokerimerkkejä
    */
    public boolean jokeriLasku (String n) {
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '*') {
                return true;
            }
        }
        return false;
    }
    /**
    * tahtiTesti suorittaa jokerimerkki tarkastukset
    * @param n tarkasteltava merkkijono
    * @return paluu, joka on false, 
    * jos merkkejä löytyy välistä, tai alusta ja lopusta
    */
    public boolean tahtiTesti (String n) {
        int tahtia = 0;
        boolean paluu = false;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '*') {
                tahtia++;
            }
            /* Tarkastetaan, että jokerimerkkiä ei löydy välistä */
            if (i > 0 && i < n.length()-1) {
                if (n.charAt(i) == '*')
                    return false;
            }
            else {
                paluu = true;
            }
        }
        if (tahtia > 3) {
            return false;
        }
        /* Emme salli molempia: alkua ja loppua jokerimerkeillä,
            jos jokerimerkkien välissä ei ole mitään.*/
        else if (n.charAt(n.length()-1) == '*' && n.charAt(0) == '*' 
                && n.length() == 2) {
            return false;
        }
        return paluu;
    }
    /**
    * tahtiPoisto poistaa halutut tähdet
    * @param n muutettava merkkijono
    * @return palauttaa muunnellun merkkijonon
    */
    public String tahtiPoisto (String n) {
        boolean alk = false;
        boolean alklop = false;
        boolean lop = false;
        String mj = "";
        
        if (n.charAt(n.length()-1) == '*' && n.charAt(0) == '*') {
            alklop = true;
            for (int i = 1; i < n.length()-1; i++) {
                mj+=n.charAt(i);
            }
        }
        else if (n.charAt(0) == '*') {
            alk = true;
            for (int i = 1; i < n.length(); i++) {
                mj+=n.charAt(i);
            }
        }
        else if (n.charAt(n.length()-1) == '*') {
            lop = true;
            for (int i = 0; i < n.length()-1; i++) {
                mj+=n.charAt(i);
            }
        }      
        return mj;
    }
    
    /**
    * jokeriTesti tarkastaa, että merkkejä on käytetty oikein
    * @param n tarkasteltava merkkijono
    * @return paluu, joka on false, jos jokerimerkkejä on käytetty väärin
    */
    public boolean jokeriTesti (String n) {
        boolean keskiTesti = tahtiTesti(n);
        boolean paluu = false;
        int putki = 0;
        
        /* Yksi ja ainut merkki on jokeri */
        if (n.charAt(0) == '*' && n.length() == 1) {
            paluu = true;
        }
        
        /* Loppu ja alku ovat molemmat jokereita. */
        else if (n.charAt(n.length()-1) == '*' && n.charAt(0) == '*' 
                && n.length()>1) { 
            n = tahtiPoisto(n);
            paluu = kerroinTesti(n);
        }
        
        /* 1. merkki on jokeri */
        else if (n.charAt(0) == '*' && keskiTesti && n.length()>1) {
            for (int i = 0; i < n.length(); i++) {
                if (i+1 < n.length()) {    
                    if (n.charAt(i+1) == nimi.charAt(i+(nimi.length()-n.length()+1))) {
                    paluu = true;
                    }
                    else {
                        return false;
                    }
                }
            }    
        }

        /* Viimeinen merkki on jokeri */
        else if (n.charAt(n.length()-1) == '*' && keskiTesti && n.length()>1) {
            for (int i = 0; i < n.length(); i++) {
                if (i+1 < n.length()) {    
                    paluu = n.charAt(i) == nimi.charAt(i);
                }
            }
        }
        return paluu;
    }
    /**
    * kerroinTesti tarkastaa osuvuuden
    * @param n tarkasteltava merkkijono
    * @return paluu, joka on false, jos jokerimerkkejä on käytetty väärin
    */
    public boolean kerroinTesti (String n) {
        boolean paluu = false;
        boolean lukko = true;
        String putkikasa = "";
        int putki = 0;
        int k = 0;
        int l = 0;
        /* Tarkistamme yhden merkin pituiset merkkijonotkin */
        if (n != null) {    
            if (n.length() == 1) {
                for (int i = 0; i < nimi.length(); i++) {
                    if (n.charAt(0) == nimi.charAt(0) 
                            || nimi.charAt(nimi.length()-1) == n.charAt(0)) {
                        return false;
                    }
                    if (n.charAt(0) == nimi.charAt(i)) {
                        paluu = true;
                    }
                }
            }
            for (int i = 1; i < nimi.length(); i++) {
                for (int j = 0; j < n.length(); j++) {
                    if (j+i < nimi.length()) {
                        if (n.charAt(j) == nimi.charAt(i+j)) {
                            putki++;
                            putkikasa += n.charAt(j);
                            if (putki == n.length()) {
                                return true;
                            }
                        }
                        else {
                            putki=0;
                            putkikasa="";
                        }
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return paluu;
    }
    /**
    * pisteTesti tarkastaa pisteiden lkm
    * @param n tarkasteltava merkkijono
    * @return paluu, joka on false, jos pisteitä on käytetty väärin
    */
    public boolean pisteTesti (String n) {
        int pisteita = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '.') {
                pisteita++;
            }
        }
        return pisteita < 2;
    }
}
