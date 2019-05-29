package bibliotheque;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Exception pour un abonné interdit d'emprunt ou de réservation
 * dans la bibliothèque
 */
@SuppressWarnings("serial")
public class AbonneInterditException extends Exception {
	public AbonneInterditException(Abonne a) {
		super("L'abonne n°" + a.getId() + " interdit d'emprunt...");
	}
}
