package bibliotheque;

import documents.exceptions.PasLibreException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Interface de base pour tous les documents de la bibliothèque
 */
public interface Document {
	/** @return le numero du document */
	int numero();
	
	/**
	 * Réserve un document
	 * @param ab le numéro de l'abonne qui souhaite reserver
	 * @throws PasLibreException si le document n'est pas libre
	 * @throws AbonneInterditException si l'abonné ne peut pas réserver le document
	 */
	void reserver(Abonne ab) throws PasLibreException, AbonneInterditException;
	
	/**
	 * Emprunte un document
	 * @param ab le numero de l'abonne qui souhaite emprunter
	 * @throws PasLibreException si le document n'est pas libre
	 * @throws AbonneInterditException si l'abonne ne peut pas emprunter le document
	 */
	void emprunter(Abonne ab) throws PasLibreException, AbonneInterditException;
	
	/**
	 * Retourne le document
	 */
	void retour();
	
	/**
	 * Dégrade le document
	 */
	void degrader();
}
