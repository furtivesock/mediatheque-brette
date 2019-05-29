package documents.exceptions;

import bibliotheque.Document;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Pour un document qui est réservé/emprunté par un propriétaire qui le réserve encore une fois
 */
@SuppressWarnings("serial")
public class PasLibreProprietaireException extends PasLibreException {
	
	public PasLibreProprietaireException(Document doc) {
		super("Vous êtes l'actuel propriétaire du document n°" + doc.numero() + " de type " + doc.getClass().getSimpleName() + ".");
	}
	
}
