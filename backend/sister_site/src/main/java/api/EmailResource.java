/**
 * @author Fouvez Dorian.
 */
package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import api.utils.Escaper;
import api.utils.PatternChecker;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.user.UserDTO;
import domaine.user.UserUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import utils.Config;

@Singleton
@Path("/emails")
public class EmailResource {

  @Inject
  private UserUCC userUCC;

  private static final int ADMIN_ID = 1;



  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response send(JsonNode json) {
    checkEmailCredentials(json);

    Email email = createEmail(json);
    email.send();

    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("email", "send");
  }



  // ******************** Private's Methods ********************

  private void checkEmailCredentials(JsonNode json) {
    // All (5) credentials needed.
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Everything is needed.", Status.BAD_REQUEST);
    }

    // All 4 credentials combinations needed.
    if (json.hasNonNull("firstname") && !json.get("firstname").asText().equals("")
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Everything except the firstname is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && json.hasNonNull("lastname") && !json.get("lastname").asText().equals("")
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Everything except the lastname is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && json.hasNonNull("from") && !json.get("from").asText().equals("")
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Everything except the email is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && json.hasNonNull("subject") && !json.get("subject").asText().equals("")
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Everything except the subject is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && json.hasNonNull("body") && !json.get("body").asText().equals("")) {
      throw new PresentationException("Everything except the message is needed.",
          Status.BAD_REQUEST);
    }

    // All 3 credentials combinations needed.
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))) {
      throw new PresentationException("Your firstname, lastname and email is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your firstname, lastname and subject is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your firstname, lastname and message is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your firstname, email and subject is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your firstname, email and message is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your firstname, subject and message is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your lastname, email and subject is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your lastname, email and message is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your lastname, subject and message is needed.",
          Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your email, subject and message is needed.",
          Status.BAD_REQUEST);
    }

    // TODO All 2 credentials combinations needed.
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))) {
      throw new PresentationException("Your firstname and lastname is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))) {
      throw new PresentationException("Your firstname and email is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your firstname and subject is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("firstname") || json.get("firstname").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your firstname and message is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("from") || json.get("from").asText().equals(""))) {
      throw new PresentationException("Your lastname and email is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your lastname and subject is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("lastname") || json.get("lastname").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your lastname and message is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("subject") || json.get("subject").asText().equals(""))) {
      throw new PresentationException("Your email and subject is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("from") || json.get("from").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Your email and message is needed.", Status.BAD_REQUEST);
    }
    if ((!json.hasNonNull("subject") || json.get("subject").asText().equals(""))
        && (!json.hasNonNull("body") || json.get("body").asText().equals(""))) {
      throw new PresentationException("Subject and message is needed.", Status.BAD_REQUEST);
    }

    // All 1 credential needed.
    if (!json.hasNonNull("firstname") || json.get("firstname").asText().equals("")) {
      throw new PresentationException("Your firstname is needed.", Status.BAD_REQUEST);
    }
    if (!json.hasNonNull("lastname") || json.get("lastname").asText().equals("")) {
      throw new PresentationException("Your lastname is needed.", Status.BAD_REQUEST);
    }
    if (!json.hasNonNull("from") || json.get("from").asText().equals("")) {
      throw new PresentationException("Your email is needed.", Status.BAD_REQUEST);
    }
    PatternChecker.checkEmailPattern(json.get("from").asText());
    if (!json.hasNonNull("subject") || json.get("subject").asText().equals("")) {
      throw new PresentationException("Subject needed.", Status.BAD_REQUEST);
    }
    if (!json.hasNonNull("body") || json.get("body").asText().equals("")) {
      throw new PresentationException("Message needed.", Status.BAD_REQUEST);
    }
  }

  private Email createEmail(JsonNode json) {
    UserDTO ambre = this.userUCC.findById(ADMIN_ID);
    if (ambre.getUserName() == null || ambre.getEmail() == null) {
      throw new PresentationException("Error find admin email.", Status.BAD_REQUEST);
    }

    String firstname = json.get("firstname").asText();
    String lastname = json.get("lastname").asText();
    String to = ambre.getEmail();
    String from = json.get("from").asText();
    String subject = json.get("subject").asText();
    String body = json.get("body").asText();
    return new Email(firstname, lastname, to, from, subject, body);
  }



  // ******************** Private's Class ********************

  private class Email {
    private static final String GMAIL_NAME = "site.ambre.fouvez";
    // private static final String GMAIL_USERNAME = "site.ambre.fouvez@gmail.com";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    private OAuth oauth = new OAuth();

    private String firstname;
    private String lastname;
    private String to;
    private String from;
    private String subject;
    private String body;

    public Email(String firstname, String lastname, String to, String from, String subject,
        String body) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.to = to;
      this.from = from;
      this.subject = subject;
      this.body = body;
    }

    public void send() {
      Properties properties = this.createProperties();
      Session session = Session.getInstance(properties, null);
      Message message = this.createMessage(session);

      try (Transport transport = session.getTransport("smtp");) {
        String accessToken = this.oauth.getAccessToken();
        transport.connect("smtp.gmail.com", GMAIL_NAME, accessToken);
        transport.sendMessage(message, message.getAllRecipients());

      } catch (NoSuchProviderException e) {
        e.printStackTrace();
        throw new PresentationException("Error when send email.", e, Status.BAD_REQUEST);
      } catch (MessagingException e) {
        e.printStackTrace();
        throw new PresentationException("Error when send email.", e, Status.BAD_REQUEST);
      }
    }

    private Message createMessage(Session session) {
      try {
        InternetAddress from = new InternetAddress(this.from);
        Message message = new MimeMessage(session);
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO,
            InternetAddress.parse("clabi-tarsal@hotmail.com")); // Can use multiples emails Ex: "to_username_a@gmail.com, to_username_b@yahoo.com".
        message.setReplyTo(InternetAddress.parse(this.from));
        message.setSubject(this.subject);
        message.setContent(this.getEmailHeader() + Escaper.deleteTagsHtml(this.body),
            "text/html; charset=utf-8");

        return message;
      } catch (AddressException e) {
        e.printStackTrace();
        throw new PresentationException("Error when search email address.", e, Status.BAD_REQUEST);
      } catch (MessagingException e) {
        e.printStackTrace();
        throw new PresentationException("Error when write the message.", e, Status.BAD_REQUEST);
      }
    }

    private Properties createProperties() {
      Properties properties = System.getProperties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.auth.mechanisms", "XOAUTH2");
      properties.put("mail.smtp.host", HOST);
      properties.put("mail.smtp.password", Config.getProperty("gmail.password"));
      properties.put("mail.smtp.port", PORT);
      properties.put("mail.smtp.ssl.trust", HOST);
      properties.put("mail.smtp.starttls.enable", true);
      properties.put("mail.smtp.user", GMAIL_NAME);

      return properties;
    }

    private String getEmailHeader() {
      return "<b>From: </b>" + Escaper.deleteTagsHtml(this.from) + "<br><b>Firstname: </b>"
          + Escaper.deleteTagsHtml(this.firstname) + "<br><b>Lastname: </b>"
          + Escaper.deleteTagsHtml(this.lastname)
          + "<br><small><i>If you answer, it will be for the "
          + "<b>From</b> and not for site.ambre.fouvez@gmail.com</i></small>"
          + "<br><hr tabindex='-1' style='display:inline-block;width:98%'><br><br>";
    }

    @Override
    public String toString() {
      return "To: " + this.to + "\nFrom: " + this.from + "\nSubject: " + this.subject + "\nBody:\n"
          + this.getEmailHeader() + "\n\n" + this.body;
    }

    private class OAuth {

      private long expireTime = -1;
      private String accessToken = Config.getProperty("oauth.accessToken");
      // private static final String SCOPE = "https://mail.google.com/";
      // private String tokenType = "Bearer";

      public String getAccessToken() {
        this.renewTokenIfExpire();
        return this.accessToken;
      }

      private void renewTokenIfExpire() {
        if (this.expireTime == -1 || System.currentTimeMillis() > this.expireTime) {
          try {
            String request = "client_id="
                + URLEncoder.encode(Config.getProperty("oauth.clientId"), "UTF-8")
                + "&client_secret=" + URLEncoder.encode(Config.getProperty("oauth.secret"), "UTF-8")
                + "&refresh_token="
                + URLEncoder.encode(Config.getProperty("oauth.refreshToken"), "UTF-8")
                + "&grant_type=refresh_token";
            HttpURLConnection conn =
                (HttpURLConnection) new URL("https://www.googleapis.com/oauth2/v4/token")
                    .openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            try (PrintWriter out = new PrintWriter(conn.getOutputStream());) {
              out.print(request); // note: println causes error
              out.flush();
            }
            conn.connect();
            try {
              HashMap<String, Object> result;
              result = new ObjectMapper().readValue(conn.getInputStream(),
                  new TypeReference<HashMap<String, Object>>() {});
              this.accessToken = (String) result.get("access_token");
              this.expireTime = System.currentTimeMillis()
                  + (((Number) result.get("expires_in")).intValue() * 1000);
            } catch (IOException e) {
              String line;
              BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
              while ((line = in.readLine()) != null) {
                System.out.println(line);
              }
              System.out.flush();
              throw new PresentationException(
                  "Error when try to read value of the token to access to email.", e,
                  Status.BAD_REQUEST);
            }
          } catch (Exception e) {
            e.printStackTrace();
            throw new PresentationException("Error when try to renew the token to access to email.",
                e, Status.BAD_REQUEST);
          }
        }
      }

    }
  }

}
