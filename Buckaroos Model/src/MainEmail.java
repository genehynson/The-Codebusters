import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is used to send emails to users of the Buckaroos application
 * 
 * @author Jordan
 * @version 1.0
 */
public class MainEmail {

    private static final String USER_NAME = "thecodebusters.buckaroos"; // GMail
    private static final String PASSWORD = "buckaroos2014"; // GMail password

    /**
     * Sends a new temporary password to the user.
     * 
     * @param recipientEmail The email to send the temporary password to
     * @param password The temporary password to send
     * @param username The username with which the password is associated
     */
    public void sendResetPassword(String recipientEmail, String password,
            String username) {
        String[] recipients = { recipientEmail };
        String subject = "Password Reset";
        String body = "Hello, you recently requested to reset your password"
                + "for the account '" + username
                + "' that you have registered "
                + "with Buckaroos. Your new temporary password is '" + password
                + " (without the quotes). Please login using this temporary "
                + "password.  You can then change your password or keep the "
                + "temporary password if you wish to do so.";
        sendFromGMail(USER_NAME, PASSWORD, recipients, subject, body);
    }

    /**
     * Sends an email to a user to welcome them to Buckaroos.
     * 
     * @param recipientEmail The email of the user
     * @param username The username of the registered account
     */
    public void sendWelcomeEmail(String recipientEmail, String username) {
        String[] recipients = { recipientEmail };
        String subject = "Welcome";
        String body = "Thank you for registering with Buckaroos.  Your "
                + "account '" + username + "' was registered "
                + "successfully with Buckaroos.";
        sendFromGMail(USER_NAME, PASSWORD, recipients, subject, body);
    }

    /**
     * Sends a transaction history of all the accounts associated with the user
     * 
     * @param recipientEmail The email of the user
     * @param username The user's username
     * @param transactions The list of transactions associated with the user
     */
    public void sendTransactionHistoryOfAllUserAccounts(String recipientEmail,
            String username, List<AccountTransaction> transactions) {
        // Could take in a date if we want to give transaction history for every
        // month or week
        StringBuffer sb = new StringBuffer();
        String[] recipients = { recipientEmail };
        String subject = "Transaction History";
        sb.append("The transaction history for '" + username
                + "' is as follows:\n");
        for (AccountTransaction accTrans : transactions) {
            sb.append(accTrans.toString() + "\n");
        }
        String body = sb.toString();
        sendFromGMail(USER_NAME, PASSWORD, recipients, subject, body);
    }

    /*
     * Sends an email using a GMail address
     * 
     * @param from The email address to send the email from
     * 
     * @param pass The password of the from email
     * 
     * @param to The email address to send the email to
     * 
     * @param subject The subject of the email to send
     * 
     * @param body The body of the email to send
     */
    private void sendFromGMail(String from, String pass, String[] to,
            String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}