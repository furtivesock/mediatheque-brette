package bibliotheque;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Une tâche pour autoriser un abonné à emprunter et réserver
 */
public class TacheAutoriser extends TimerTask {

	private Abonne ab;
	private Timer t;
	
	public TacheAutoriser(Abonne ab, Timer t) {
		this.ab = ab;
		this.t = t;
	}
	
	@Override
	public void run() {
		ab.autoriser();
		t.cancel();
	}

}
