package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppliClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		/*System.out.print("Bonjour et bienvenue sur notre interface de la médiathèque VDM."
				+ "\nPour accéder aux différentes services :"
				+ "\n2500 : Besoin d'un document mais quelqu'un d'autre que vous l'a pris avant vous ? Réservez-le pour être sûr de l'avoir !"
				+ "\n2600 : Vous voulez savourer un document posément chez vous ? Empruntez-le !"
				+ "\n2700 : Eh oui, il faut un jour dire au revoir à votre cher document. Rendez-le nous ici.\n");
		 */
		int port = Integer.parseInt(args[1]);
		Socket client = new Socket(args[0], port);
		
		switch(port) {
		case 2500:
			System.out.print("Bonjour et bienvenue très cher abonné. Vous êtes au service Réservation.\n");
			break;
		case 2600:
			System.out.print("Bonjour et bienvenue très cher abonné. Vous êtes au service Emprunt.\n");
			break;
		case 2700:
			System.out.print("Bonjour et bienvenue très cher abonné. Vous êtes au service Retour.\n");
			break;
		default:
			throw new IllegalArgumentException("Ce port est invalide.");
		}
		
		System.out.println("Le client est bien connecté au serveur");
		
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
		
		while (true) {
			String serverLine = socketIn.readLine();
			if (serverLine == null) break;
			System.out.println(serverLine);
			String réponse = sc.nextLine();
			if (réponse == "") break;
			socketOut.println(réponse);
			System.out.println("Traitement de votre demande...");
		}
		
	}

}
