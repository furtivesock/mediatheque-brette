package documents.exceptions;

import bibliotheque.Document;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Pour un document qui est r�serv�/emprunt� par un propri�taire qui le r�serve encore une fois
 */
@SuppressWarnings("serial")
public class PasLibreProprietaireException extends PasLibreException {
	
	public PasLibreProprietaireException(Document doc) {
		super("Vous �tes l'actuel propri�taire du document n�" + doc.numero() + " de type " + doc.getClass().getSimpleName() + ".");
	}
	
}
