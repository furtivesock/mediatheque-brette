package documents.exceptions;

import bibliotheque.Document;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Pour un document qui est indisponible pour une raison quelconque
 */
@SuppressWarnings("serial")
public class PasLibreException extends Exception {
	
	public PasLibreException(String s) {
		super(s);
	}
	
	public PasLibreException(Document doc) {
		this("Le document nÂ°" + doc.numero() + " de type " + doc.getClass().getSimpleName() + " n'est pas disponible. Vous serez notifié par son retour.");
	}
}
