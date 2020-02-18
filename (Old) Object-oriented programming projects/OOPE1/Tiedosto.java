package tiedot;

/**
 *
 * @author Valtte
 */
public class Tiedosto extends Tieto {
	private int koko;

	final String error = "Error!";

	public Tiedosto(StringBuilder n, int k){
		super(n);
		if(k >= 0){
			koko(k);
		} else {
			throw new IllegalArgumentException(error);
		}
	}

	//kopiorakentaja
	public Tiedosto(Tiedosto t){
			super(t);
			koko(t.koko());
	}

	public void koko(int k){
		if(k >= 0){
			koko = k;
		} else {
			throw new IllegalArgumentException(error);
		}
	}

	public int koko(){
		return koko;
	}

	@Override
	public String toString(){
		return super.toString() + " " + koko;
	}

}