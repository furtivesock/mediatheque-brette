package documents.etats;

import java.util.Timer;
import java.util.TimerTask;

import documents.DocumentAvecEtats;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Tache pour retourner un document
 */
public class TacheRetourner extends TimerTask {

	/** le document a retourner */
	private DocumentAvecEtats docReserve;
	
	/** le timer */
	private Timer delai;
	
	/**
	 * Pour retourner un document
	 * @param d le document à retourner
	 * @param t le timer
	 */
	public TacheRetourner(DocumentAvecEtats d, Timer t) {
		this.docReserve = d;
		this.delai = t;
	}
	
	@Override
	public void run() {
		docReserve.retour();
		delai.cancel();
	}

}
