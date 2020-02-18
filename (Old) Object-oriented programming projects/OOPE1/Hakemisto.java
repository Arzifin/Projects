package tiedot;
import omalista.*;
import apulaiset.*;
import fi.uta.csjola.oope.lista.*;
/**Hakemisto-luokka, joka vastaa hakemistojen luomisesta ja toiminnasta.
  *<p>
  *harjoitustyö Oope, kevät 2017
  *<p>
  *@author Valtteri Pohjola (pohjola.m.valtteri@student.uta.fi)
  */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {

	private OmaLista sisalto;
	private Hakemisto ylihakemisto;

	public Hakemisto(StringBuilder n, Hakemisto viite){
		super(n);

		ylihakemisto = viite;
		sisalto = new OmaLista();

	}

	@Override
	public LinkitettyLista sisalto(){
		return sisalto;
	}
	//ylihakemiston aksessori
	public Hakemisto ylihakemisto(){
		return ylihakemisto;
	}

	@Override
	public Tieto hae(String nimi){
		if(nimi != null){
			//kaytetaan muuntaja metodia valiaikais tiedoston tekemiseen
			Tiedosto temp = valiaikaisTiedosto(nimi);
			//Castataan paluuarvo tiedoksi ja palautetaan se.
			for(int i = 0; i < sisalto.koko(); i++){
				if(sisalto.alkio(i).equals(temp)){
					Tieto retVal = (Tieto)sisalto.alkio(i);
					return retVal;
				}
			}
		}

		return null;
	}

	@Override
	public boolean lisaa(Tieto lisattava){
		for(int i = 0; i < sisalto.koko(); i++){
			if(sisalto.alkio(i).equals(lisattava)){
				return false;
			}
		}
		boolean onnistuuko = sisalto.lisaa(lisattava);
		return onnistuuko;
	}

	@Override
	public Tieto poista(String nimi){
		if(nimi != null){
			Tiedosto temp = valiaikaisTiedosto(nimi);
			for(int i = 0; i < sisalto.koko(); i++){
				if(sisalto.alkio(i).equals(temp)){
					Tieto retVal = (Tieto)sisalto.poista(i);
					return retVal;
				}
			}

		}
		return null;
	}

	@Override
	public String toString(){
		if(this.ylihakemisto != null){
			return super.toString() + "/";
		}
		return "/";
	}



	//muunnos ja väliaikaistiedoston luontimetodi
	public Tiedosto valiaikaisTiedosto(String nimi){
		StringBuilder n = new StringBuilder(nimi);
		//luodaan temp tiedosto hakua varten
		Tiedosto temp = new Tiedosto(n, 1);
		return temp;
	}
}