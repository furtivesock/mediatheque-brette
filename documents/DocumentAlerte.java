package documents;

import java.util.HashSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bibliotheque.Abonne;
import bibliotheque.AbonneInterditException;
import bibliotheque.Bibliotheque;
import documents.exceptions.PasLibreException;

/**
 * @author Aleryc Serrania et Sophie Nguyen
 * Brettesoft n°2 : Document qui peut alerter les utilisateurs intÃ©ressÃ©s par mail
 */
public class DocumentAlerte extends DocumentDecorateur {

	/** liste des abonnes en attente de retour du livre */
	private HashSet<Abonne> abonnesEnAttente; 
	
	public DocumentAlerte(DocumentAvecEtats d) {
		super(d);
		abonnesEnAttente = new HashSet<Abonne>();
	}
	
	/**
	 * Réserve un document, ajoute un abonné dans la liste d'attente si le document est indisponible
	 * @param ab l'abonné qui souhaite réserver
	 */
	@Override
	public void reserver(Abonne ab) throws PasLibreException, AbonneInterditException {
		try {
			this.getDocumentDecore().reserver(ab);
		} catch (PasLibreException e) {
			if (ab.getId() != this.getDocumentDecore().getProprietaire().getId())
				abonnesEnAttente.add(ab);
			throw e;
		} 
	}
	
	/**
	 * Retourne un document, envoie un mail aux abonnés en attente
	 */
	@Override
	public void retour() {
		this.getDocumentDecore().retour();
		if (abonnesEnAttente!=null) {
			for (Abonne abo : abonnesEnAttente) {
				sendMessage(abo,this);
			}
			abonnesEnAttente.clear();
		}
	}
	
	/**
	 * Envoie un mail
	 * @param ab l'abonné à qui envoyer le mail d'alerte, d le document libre qu'il voulait réserver
	 * L'option "autoriser les applications moins autorisées" sur Gmail doit être activée
	 * Il se peut que cette méthode ne fonctionne pas correctement à cause du port utilisé et des anti-virus
	 */
	public static void sendMessage(Abonne ab, DocumentAvecEtats d) {
		String to = ab.getMail();
		String from = Bibliotheque.getInstance().getMail();
		String host = "localhost";
		//Creation de session
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

		properties.setProperty("mail.stmp.host", host);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Bibliotheque.getInstance().getMail(), Bibliotheque.getInstance().getPassword());
			}
        });
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("Votre document nï¿½" + d.numero() + " est disponible dans votre bibliothï¿½que");
			message.setContent("Bonjour,<br> votre document nï¿½" + d.numero() 
					+ " de type " + d.getType()
					+ " est disponible dans votre bibliothï¿½que."
					+ "<br><br>Attrapez-le avant les autres ! <br> - Votre bibliothï¿½que","text/html");
			
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
}
