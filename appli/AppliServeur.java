package appli;

import java.io.IOException;

import serveur.Serveur;
import serveur.services.FactoryService;

public class AppliServeur {
	public static void main(String[] args) throws IOException {
		
		Serveur Alerick = new Serveur(2500, new FactoryService());
		new Thread(Alerick).start();
		
		Serveur Sofi = new Serveur(2600, new FactoryService());
		new Thread(Sofi).start();
		
		Serveur Garygolade = new Serveur(2700, new FactoryService());
		new Thread(Garygolade).start();
		
		System.out.println("Les serveurs sont prets a recevoir les demandes innombrables et au combien sadiques des clients");
	}
}