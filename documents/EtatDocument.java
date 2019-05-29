package documents;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.DocumentAvecEtats;
import documents.exceptions.PasLibreException;

public interface EtatDocument {
	void reserver(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException;
	void emprunter(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException;
	void retour(DocumentAvecEtats d);
	void degrader(DocumentAvecEtats d);
}
