package documents.etats;

import java.util.Timer;
import java.util.TimerTask;

import documents.DocumentAvecEtats;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Tache pour la mise en retard d'un document
 */
public class TacheRetard extends TimerTask {

	/** le document a mettre en retard */
	private DocumentAvecEtats doc;
	
	/** le timer */
	private Timer delai;

	/**
	 * Pour mettre en retard un document
	 * @param doc le document a mettre en retard
	 * @param t le timer
	 */
	public TacheRetard(DocumentAvecEtats doc, Timer t) {
		this.doc = doc;
		this.delai = t;
	}
	
	@Override
	public void run() {
		doc.setEtat(new Retard());
		delai.cancel();
	}

}
