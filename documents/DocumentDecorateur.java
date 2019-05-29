package documents;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import documents.exceptions.PasLibreException;

/**
 * @author Serrania Aleryc et Sophie Nguyen
 * Decorateur pour ajouter des fonctionnalités au document comme l'envoi de mail
 * au retour du document
 */
public abstract class DocumentDecorateur extends DocumentAvecEtats {
	
	//DocumentAbst pour le getEtatDocument
	private DocumentAvecEtats doc;
	
	public DocumentDecorateur(DocumentAvecEtats d) {
		super(d.numero(), d.getEtatDocument());
		this.doc = d;
	}
	
	public DocumentAvecEtats getDocumentDecore() {
		return doc;
	}

	@Override
	public int numero() {
		return doc.numero();
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException, AbonneInterditException {
		doc.reserver(ab);
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException, AbonneInterditException {
		doc.emprunter(ab);
	}

	@Override
	public void retour() {
		doc.retour();
	}

	@Override
	public void degrader() {
		doc.degrader();
	}
	
	public EtatDocument getEtatDocument() {
		return doc.getEtatDocument();
	}
	
	@Override
	public String getType() {
		return doc.getType();
	}
	
}
