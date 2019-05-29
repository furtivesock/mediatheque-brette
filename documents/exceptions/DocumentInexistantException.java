package documents.exceptions;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Pour un numéro de document qui ne correspond à aucun document dans la 
 * bibliothèque
 */
@SuppressWarnings("serial")
public class DocumentInexistantException extends Exception {

	public DocumentInexistantException(int numDoc) {
		super("Le document n�" + numDoc + " n'existe point dans notre base de donn�es...");
	}
}
