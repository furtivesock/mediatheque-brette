package documents;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import bibliotheque.Document;
import documents.exceptions.PasLibreException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Implémente l'interface document pour creer un document avec des etats,
 * un numero et un proprietaire
 */
public abstract class DocumentAvecEtats implements Document {

	/** son numero */
	private int num;
	
	/** son etat */
	private EtatDocument etat;
	
	/** son proprietaire (emprunteur) */
	private Abonne proprietaire;
	
	public DocumentAvecEtats(int n, EtatDocument e) {
		num = n;
		etat = e;
	}
	
	
	@Override
	public int numero() {
		return num;
	}

	@Override
	public void reserver(Abonne ab) throws AbonneInterditException, PasLibreException {
		synchronized (this) { this.etat.reserver(ab, this); }
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException, AbonneInterditException {
		synchronized (this) { this.etat.emprunter(ab,this); }
	}

	@Override
	public void retour() {
		synchronized (this) { this.etat.retour(this); }
	}
	
	@Override
	public void degrader() {
		synchronized (this) { this.etat.degrader(this); }
	}
	
	/**
	 * Change son etat
	 * @param e son nouvel etat
	 */
	public void setEtat(EtatDocument e) {
		this.etat = e;
	}
	
	/**
	 * @return son proprietaire
	 */
	public Abonne getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * Change son proprietaire
	 * @param ab son nouveau proprietaire
	 */
	public void setProprietaire(Abonne ab) {
		this.proprietaire = ab;
	}
	
	/**
	 * @return son etat
	 */
	public EtatDocument getEtatDocument() {
		return etat;
	}
	
	/**
	 * @return son type
	 */
	public abstract String getType();
	
}
