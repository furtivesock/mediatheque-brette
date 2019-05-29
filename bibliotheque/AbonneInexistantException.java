package bibliotheque;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Exception pour un identifiant inconnu dans la bibliothèque
 */
@SuppressWarnings("serial")
public class AbonneInexistantException extends Exception {
	public AbonneInexistantException(int idAbonne) {
		super("L'abonne n°" + idAbonne + " n'existe point dans notre base de donnees...");
	}
}
