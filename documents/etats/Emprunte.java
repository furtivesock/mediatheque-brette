package documents.etats;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import bibliotheque.Abonne;
import documents.DocumentAvecEtats;
import documents.EtatDocument;
import documents.exceptions.PasLibreException;
import documents.exceptions.PasLibreProprietaireException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * L'etat emprunte d'un document
 */
public class Emprunte implements EtatDocument {
	
	/** délai avant le retard du livre */
	private Timer delai;

	public Emprunte(DocumentAvecEtats d) {
		this.delai = new Timer();
		//delai.schedule(new TacheRetard(d, delai), 2000);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 14);
		delai.schedule(new TacheRetard(d, delai), calendar.getTime());
	}
	
	@Override
	public void reserver(Abonne ab, DocumentAvecEtats d) throws PasLibreException {
		if (d.getProprietaire() == ab)
			throw new PasLibreProprietaireException(d);
		else
			throw new PasLibreException(d);
	}

	@Override
	public void emprunter(Abonne ab, DocumentAvecEtats d) throws PasLibreException {
		throw new PasLibreException(d);
	}

	@Override
	public void retour(DocumentAvecEtats d) {
		d.setProprietaire(null);
		d.setEtat(new Libre());
		delai.cancel();
	}
	
	@Override
	public void degrader(DocumentAvecEtats d) {
		d.getProprietaire().sanctionner();
		d.setEtat(new Degrade());
		delai.cancel();
	}
	
}
