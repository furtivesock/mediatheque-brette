package documents.etats;

import java.util.Timer;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.EtatDocument;
import documents.exceptions.PasLibreException;
import documents.exceptions.PasLibreProprietaireException;
import documents.DocumentAvecEtats;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * L'etat emprunte d'un document
 */
public class Reserve implements EtatDocument {

	/** délai avant l'annulation de la réservation */
	private Timer delai;
	
	public Reserve(DocumentAvecEtats d) {
		this.delai = new Timer();
		delai.schedule(new TacheRetourner(d,delai), 2*60*60*1000);
	}
	
	@Override
	public void reserver(Abonne ab, DocumentAvecEtats d) throws PasLibreException {
		if (d.getProprietaire() == ab)
			throw new PasLibreProprietaireException(d);
		else
			throw new PasLibreException(d);
	}

	@Override
	public void emprunter(Abonne ab, DocumentAvecEtats d) throws PasLibreException, AbonneInterditException {
		if (ab.estInterdit()) {
			throw new AbonneInterditException(ab);
		} else if (ab.getId() == d.getProprietaire().getId()) {
			delai.cancel();
			d.setEtat(new Emprunte(d));
		}
		else
			throw new PasLibreException(d);
	}

	@Override
	public void retour(DocumentAvecEtats d) {
		delai.cancel();
		d.setEtat(new Libre());
		d.setProprietaire(null);
	}
	
	@Override
	public void degrader(DocumentAvecEtats d) {
		delai.cancel();
		d.setEtat(new Degrade());
		d.setProprietaire(null);
	}

}
