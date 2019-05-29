package documents;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Un livre super cool pour la bibliothèque
 */
public class Livre extends DocumentAvecEtats {

	public Livre(int n, EtatDocument e) {
		super(n, e);
	}
	
	@Override 
	public String getType() {
		return "Livre";
	}

}
