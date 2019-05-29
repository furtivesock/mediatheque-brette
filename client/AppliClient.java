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
		/*System.out.print("Bonjour et bienvenue sur notre interface de la m�diath�que VDM."
				+ "\nPour acc�der aux diff�rentes services :"
				+ "\n2500 : Besoin d'un document mais quelqu'un d'autre que vous l'a pris avant vous ? R�servez-le pour �tre s�r de l'avoir !"
				+ "\n2600 : Vous voulez savourer un document pos�ment chez vous ? Empruntez-le !"
				+ "\n2700 : Eh oui, il faut un jour dire au revoir � votre cher document. Rendez-le nous ici.\n");
		 */
		int port = Integer.parseInt(args[1]);
		Socket client = new Socket(args[0], port);
		
		switch(port) {
		case 2500:
			System.out.print("Bonjour et bienvenue tr�s cher abonn�. Vous �tes au service R�servation.\n");
			break;
		case 2600:
			System.out.print("Bonjour et bienvenue tr�s cher abonn�. Vous �tes au service Emprunt.\n");
			break;
		case 2700:
			System.out.print("Bonjour et bienvenue tr�s cher abonn�. Vous �tes au service Retour.\n");
			break;
		default:
			throw new IllegalArgumentException("Ce port est invalide.");
		}
		
		System.out.println("Le client est bien connect� au serveur");
		
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
		
		while (true) {
			String serverLine = socketIn.readLine();
			if (serverLine == null) break;
			System.out.println(serverLine);
			String r�ponse = sc.nextLine();
			if (r�ponse == "") break;
			socketOut.println(r�ponse);
			System.out.println("Traitement de votre demande...");
		}
		
	}

}
