package bibliotheque.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bibliotheque.AbonneInexistantException;
import bibliotheque.AbonneInterditException;
import bibliotheque.Bibliotheque;
import documents.DocumentAvecEtats;
import documents.etats.Degrade;
import documents.etats.Emprunte;
import documents.etats.Libre;
import documents.etats.Reserve;
import documents.exceptions.DocumentInexistantException;
import documents.exceptions.PasLibreException;
import documents.exceptions.PasLibreProprietaireException;

public class TestBibliotheque {

	@Before 
	public void start() throws DocumentInexistantException, AbonneInexistantException {
		Bibliotheque.getInstance().retourner(1410);
		Bibliotheque.getInstance().searchAbonneById(2110).autoriser();
	}
	
	@Test
	public void testCycleNormal() throws DocumentInexistantException, AbonneInexistantException, PasLibreException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		
		// WHEN 
		biblio.emprunter(1410, 2110);
		assertEquals(((DocumentAvecEtats) biblio.searchDocumentById(1410)).getEtatDocument().getClass(), Emprunte.class);
		biblio.retourner(1410);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Libre.class);
		biblio.reserver(1410, 2110);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Reserve.class);
		biblio.retourner(1410);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Libre.class);
		biblio.reserver(1410, 2110);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Reserve.class);
		biblio.emprunter(1410, 2110);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Emprunte.class);
		biblio.degrader(1410);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Degrade.class);
		biblio.retourner(1410);		
		assertTrue(biblio.searchAbonneById(2110).estInterdit());
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Libre.class);

	}
	
	@Test (expected = PasLibreException.class)
	public void testEmprunteDejaEmprunteParAutre() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
				
		// WHEN 
		biblio.emprunter(1410, 2110);
		biblio.emprunter(1410, 1102);
	}
	
	@Test (expected = PasLibreException.class)
	public void testEmprunteMemeAbonne() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
				
		// WHEN 
		biblio.emprunter(1410, 2110);
		biblio.emprunter(1410, 2110);
	}
	
	@Test (expected = PasLibreException.class)
	public void testReserveDejaEmprunteParAutre() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
				
		// WHEN 
		biblio.emprunter(1410, 2110);
		biblio.reserver(1410, 1102);
	}
	
	@Test (expected = PasLibreProprietaireException.class)
	public void testReserveMemeAbonne() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
				
		// WHEN 
		biblio.emprunter(1410, 2110);
		biblio.reserver(1410, 2110);
	}
	
	@Test 
	public void testRetourLibre() throws PasLibreException, DocumentInexistantException, AbonneInexistantException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		// WHEN 
		biblio.retourner(1410);
		assertEquals(((DocumentAvecEtats) biblio.searchDocumentById(1410)).getEtatDocument().getClass(), Libre.class);
	}
	//Test pour attendre les délais de réservation, retards, sanctions en secondes car sinon ce sera beaucoup trop long pour notre vie
	/*@Test
	public void testDelaiReservation() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, InterruptedException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		// WHEN 
		biblio.reserver(1410,2110);
		Thread.sleep(2000);
		assertEquals(((DocumentAvecEtats)biblio.searchDocumentById(1410)).getEtatDocument().getClass() , Libre.class);
	}*/
	
	/*@Test
	public void testEmprunteRetard() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, InterruptedException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		// WHEN 
		biblio.emprunter(1410, 2110);
		Thread.sleep(3000);
		assertEquals(((DocumentAvecEtats) Bibliotheque.getInstance().searchDocumentById(1410)).getEtatDocument().getClass(), Retard.class);
	}*/
	
	/*@Test
	public void testAutoriseApresSanction() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, InterruptedException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		// WHEN 
		biblio.emprunter(1410, 2110);
		Thread.sleep(3000);
		biblio.retourner(1410);
		Thread.sleep(2000);
		assertFalse(biblio.searchAbonneById(2110).estInterdit());
	}*/
	
	@Test (expected = PasLibreException.class)
	public void testReserveReserveParUnAutreDispo() throws PasLibreException, DocumentInexistantException, AbonneInexistantException, AbonneInterditException {
		// GIVEN
		Bibliotheque biblio = Bibliotheque.getInstance();
		// WHEN 
		biblio.emprunter(1410, 2110);
		biblio.reserver(1410, 1102);
		biblio.retourner(1410);
	}
}
