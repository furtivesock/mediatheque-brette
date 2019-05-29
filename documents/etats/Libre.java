package documents.etats;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.EtatDocument;
import documents.DocumentAvecEtats;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * L'etat libre d'un document
 */
public class Libre implements EtatDocument {
	
	@Override
	public void reserver(Abonne ab, DocumentAvecEtats d) throws AbonneInterditException {
		if (ab.estInterdit()) throw new AbonneInterditException(ab);
		d.setProprietaire(ab);
		d.setEtat(new Reserve(d));
	}

	@Override
	public void emprunter(Abonne ab, DocumentAvecEtats d) throws AbonneInterditException {
		if (ab.estInterdit()) throw new AbonneInterditException(ab);
		d.setProprietaire(ab);
		d.setEtat(new Emprunte(d));
	}

	@Override
	public void retour(DocumentAvecEtats d) {
		//Ne rien faire
	}
	
	@Override
	public void degrader(DocumentAvecEtats d) {
		d.setEtat(new Degrade());
	}
}
