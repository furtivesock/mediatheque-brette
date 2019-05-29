package documents.etats;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.DocumentAvecEtats;
import documents.EtatDocument;
import documents.exceptions.PasLibreException;
import documents.exceptions.PasLibreProprietaireException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * L'etat degrade d'un document
 */
public class Degrade implements EtatDocument {
	
	@Override
	public void reserver(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException {
		if (d.getProprietaire() == ab)
			throw new PasLibreProprietaireException(d);
		else
			throw new PasLibreException(d);
	}

	@Override
	public void emprunter(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException {
		throw new PasLibreException(d);
	}

	@Override
	public void retour(DocumentAvecEtats d) {
		d.setEtat(new Libre());
	}

	@Override
	public void degrader(DocumentAvecEtats d) {
		// ne rien faire
	}

}
