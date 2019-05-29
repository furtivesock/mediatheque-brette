package bibliotheque;

import java.util.LinkedList;

import documents.DocumentAlerte;
import documents.Livre;
import documents.etats.Degrade;
import documents.etats.Libre;
import documents.exceptions.DocumentInexistantException;
import documents.exceptions.PasLibreException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Classe qui propose une interface pour toutes les actions possibles
 * que l'on peut faire dans une bibliothèque (emprunt, réservation, retour, ajout
 * de livres, ...)
 */
public class Bibliotheque {
	/** ses abonnés */
	private LinkedList<Abonne> abonnes;
	
	/** ses documents */
	private LinkedList<Document> documents;
	
	/** son instance (singleton) */
	private static Bibliotheque instance;
	
	/** mail de la bibliothèque */
	private String mail;
	
	/** mot de passe du mail de la bibliothèque */
	private String password;
	
	private Bibliotheque() {
		mail = "bernard.bibliobrette@gmail.com";
		password = "brettes0ft";
		
		abonnes = new LinkedList<>();
		documents = new LinkedList<>();
		abonnes.add(new Abonne(2110,"alerycserrania@gmail.com"));
		abonnes.add(new Abonne(1102,"chaussettefurtive@outlook.fr"));
		abonnes.add(new Abonne(2712,"lyx@outlook.com"));
		
		documents.add(new DocumentAlerte(new Livre(1410,new Libre())));
		documents.add(new Livre(2003,new Libre()));
		documents.add(new Livre(1111,new Degrade()));
	}
	
	/**
	 * @return la bibliothèque
	 */
	public static synchronized Bibliotheque getInstance() {
		if (instance == null)
			instance = new Bibliotheque();
		return instance;
	}
	
	
	/**
	 * Renvoie le document correspondant à l'id
	 * @param id l'identifiant du document
	 * @return le document
	 * @throws DocumentInexistantException si l'id ne correspond à aucun
	 * document dans la bibliothèque
	 */
	public Document searchDocumentById(int id) throws DocumentInexistantException{
		for (Document d : documents) {
			if (d.numero()==id)
				return d;
		}
		throw new DocumentInexistantException(id);
	}

	/**
	 * Renvoie le document correspondant à l'id
	 * @param idAbonne
	 * @return l'abonne
	 * @throws AbonneInexistantExceptionsi l'id ne correspond à aucun
	 * abonne dans la bibliothèque
	 */
	public Abonne searchAbonneById(int idAbonne) throws AbonneInexistantException{
		for (Abonne a : abonnes) {
			if (a.getId()==idAbonne)
				return a;
		}
		throw new AbonneInexistantException(idAbonne);
	}
	
	/**
	 * Emprunte un document
	 * @param numDoc le numero du document à emprunter
	 * @param idAbonne l'id de l'emprunteur
	 * @throws PasLibreException si le document n'est pas libre
	 * @throws DocumentInexistantException si le document est inexistant
	 * @throws AbonneInexistantException si l'abonne est inexistant
	 * @throws AbonneInterditException si l'abonne ne peut pas emprunter
	 */
	public void emprunter(int numDoc, int idAbonne) throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		searchDocumentById(numDoc).emprunter(searchAbonneById(idAbonne));
	}
	
	/**
	 * Reserve un document
	 * @param numDoc le numero du document à reserver
	 * @param idAbonne l'id de l'abonne qui souhaite réserver
	 * @throws PasLibreException si le document n'est pas libre
	 * @throws DocumentInexistantException si le document est inexistant
	 * @throws AbonneInexistantException si l'abonne est inexistant
	 * @throws AbonneInterditException si l'abonne ne peut pas reserver 
	 */
	public void reserver(int numDoc, int idAbonne) throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		searchDocumentById(numDoc).reserver(searchAbonneById(idAbonne));
	}
	
	/**
	 * Retourne un document
	 * @param numDoc le document à retourner
	 * @throws DocumentInexistantException si le document n'existe pas
	 */
	public void retourner(int numDoc) throws DocumentInexistantException {
		searchDocumentById(numDoc).retour();
	}
	
	/**
	 * Dégrade un document
	 * @param numDoc le document à dégrader
	 * @throws DocumentInexistantException si le document n'existe pas
	 * @throws PasEmprunteException si le document n'est pas emprunté
	 */
	public void degrader(int numDoc) throws DocumentInexistantException {
		searchDocumentById(numDoc).degrader();
	}

	/**
	 * @return le mail de la bibliothèque
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @return le mot de passe du mail de la bibliothèque
	 */
	public String getPassword() {
		return password;
	}
}
