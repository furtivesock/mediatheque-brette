package documents.etats;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.DocumentAvecEtats;
import documents.EtatDocument;
import documents.exceptions.PasLibreException;

public class Retard implements EtatDocument {

	@Override
	public void reserver(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException {
		throw new PasLibreException(d);
	}

	@Override
	public void emprunter(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException {
		throw new PasLibreException(d);
	}

	@Override
	public void retour(DocumentAvecEtats d) {
		d.setProprietaire(null);
		d.setEtat(new Libre());
	}

	@Override
	public void degrader(DocumentAvecEtats d) {
		d.getProprietaire().sanctionner();
		d.setEtat(new Degrade());
	}

}
