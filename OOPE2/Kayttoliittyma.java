package harjoitustyo;
import harjoitustyo.apulaiset.In;
import harjoitustyo.tiedot.Tieto;

/**
 * Käyttöliittymä-luokka
 * <p>
 * Harjoitustyö, Olio-ohjelmoinnin perusteet II, kevät 2019.
 * <p>
 * @author Arttu Lehtola (arttu.lehtola@tuni.fi), Informaatioteknologian
 * ja viestinnän tiedekunta,
 * Tampereen yliopisto.
 */

public class Kayttoliittyma {
    /* Vakiot */
    final String wts = "Welcome to SOS.";
    final String error = "Error!";
    final String st = "Shell terminated.";
    final String md = "md";
    final String cd = "cd";
    final String mf = "mf";
    final String mv = "mv";
    final String ls = "ls";
    final String cp = "cp";
    final String rm = "rm";
    final String find = "find";
    final String exit = "exit";
    
    /* Attribuutit */
    private Tulkki tulkki;
    /* Rakentajat */
    public Kayttoliittyma () {
        tulkki = new Tulkki();
        kysely();
    }
    /* Aksessorit */
    
    /* Metodit */
    public void kysely () {
        String s = "";
        System.out.println(wts);
        String[] syote = new String[3];
        while (!s.equals(exit)) {
            try {
                String polutus = tulkki.polku(tulkki.kaytossa());
                String[] taulutus = tulkki.tauluttaja(polutus);
                String printtaus = tulkki.printti(taulutus);
                System.out.print(printtaus);
                s = In.readString();
                syote = pilko(s);
                if (syote[0].equals(md)) {
                    tulkki.mdM(syote[1]);
                }
                else if (syote[0].equals(cd)) {
                    if (syote[1] != null) {
                        tulkki.cdM(syote[1]);
                    }
                    else {
                        tulkki.cdM();
                    }
                }
                else if (syote[0].equals(mf)) {
                    tulkki.mfM(syote[1], Integer.parseInt(syote[2]));
                }
                else if (syote[0].equals(mv)) {
                    tulkki.mvM(syote[1], syote[2]);
                }
                else if (syote[0].equals(ls)) {
                    if (syote[1] == null) {
                        tulkki.lsM();
                    }
                    else if (syote[2] == null && syote[1] != null) {
                        tulkki.lsM(syote[1]);
                    }
                }
                else if (syote[0].equals(cp)) {
                    
                }
                else if (syote[0].equals(rm)) {
                    if (syote[2] != null) {
                        throw new IllegalArgumentException(error);
                    }
                    else {
                        tulkki.rmM(syote[1]);
                    }
                }
                else if (syote[0].equals(find)) {
                    
                }
                else if (!syote[0].equals(exit)) {
                    System.out.println(error);
                }
            }
            catch (Exception e) {
                System.out.println(error);
            }
        }   
        System.out.println(st);
    }
    /**
     * Pilko-metodi,
     * @param mj pilkottava merkkijono
     * @return merkkijono taulukko, jossa on pilkottu käyttäjän komento
     */
    public String[] pilko(String mj){
        String[] syote = new String[3];
        String jono = "";
        int kerroin = 0;
        if (mj != null) {   
            for (int i = 0; i < mj.length(); i++) {
                if (mj.charAt(i) != ' ') {
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
}
