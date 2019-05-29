package bibliotheque;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Représente un abonné de la bibliothèque
 */
public class Abonne {
	
	/** son identifiant */
	private int id;

	/** son mail */
	private String mail;
	
	/** s'il est interdit d'emprunt */
	private boolean interdit;
	
	/**Timer qui s'active lorsqu'il est sanctionn�*/
	private Timer tempsSanction; 
	
	
	public Abonne(int id, String mail) {
		this.id = id;
		this.interdit = false;
		this.mail = mail;
		this.tempsSanction = new Timer();
	}
	
	/**
	 * @return son identifiant
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return son mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Sanctionne l'abonné en lui enlevant son droit d'emprunter
	 */
	public void sanctionner() {
		interdit = true;
		synchronized(this) { // pour �viter un cancel avant le schedule
			tempsSanction.cancel();
			tempsSanction = new Timer();
			//tempsSanction.schedule(new TacheAutoriser(this, tempsSanction), 1000);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 30);
			System.out.println(calendar);
			tempsSanction.schedule(new TacheAutoriser(this, tempsSanction), calendar.getTime());
		}
		
	}
	
	/**
	 * Lui redonne le droit d'emprunter
	 */
	public void autoriser() {
		interdit = false;
	}
	
	/**
	 * @return true s'il est interdit, false sinon
	 */
	public boolean estInterdit() {
		return interdit;
	}
}
