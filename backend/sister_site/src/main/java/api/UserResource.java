/**
 * @author Fouvez Dorian.
 */
package api;

/*
 * import java.util.regex.Matcher; import java.util.regex.Pattern;
 */
import org.glassfish.jersey.server.ContainerRequest;
import com.fasterxml.jackson.databind.JsonNode;
import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.DomaineFactory;
import domaine.user.UserDTO;
import domaine.user.UserUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/users")
public class UserResource {

  @Inject
  private UserUCC userUcc;

  @Inject
  private DomaineFactory domaineFactory;



  /**
   * Login the user if exists or send error message.
   * 
   * @param json object containing a username and a password.
   * @return a response with a token who contains the user if it exists in the database and matches the password.
   */
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response login(JsonNode json) {
    // Check credentials.
    if ((!json.hasNonNull("username") || json.get("username").asText().equals(""))
        && (!json.hasNonNull("password") || json.get("password").asText().equals(""))) {
      throw new PresentationException("Username and password needed.", Status.BAD_REQUEST);
    }
    if (!json.hasNonNull("username") || json.get("username").asText().equals("")) {
      throw new PresentationException("Username needed.", Status.BAD_REQUEST);
    }
    if (!json.hasNonNull("password") || json.get("password").asText().equals("")) {
      throw new PresentationException("Password needed.", Status.BAD_REQUEST);
    }

    UserDTO user = this.userUcc.login(json.get("username").asText(), json.get("password").asText());
    return ResponseMaker.createResponseWithToken(user);
  }

  /**
   * Get the user from an id in a token in header.
   * 
   * @param request header with the token.
   * @return a new token and the user.
   */
  @GET
  @Path("/me")
  @Authorize
  public Response getUser(@Context ContainerRequest request) {
    UserDTO currentUser = (UserDTO) request.getProperty("user");
    return ResponseMaker.createResponseWithToken(currentUser);
  }

  /**
   * Get the user with an ID if exists or send error message.
   * 
   * @param id id of the user.
   * @return a user if user exists in database and matches the id.
   */
  /*
   * @GET
   * 
   * @Path("/{id}")
   * 
   * @AuthorizeBoss public Response getUserById(@PathParam("id") int id) { // Check credentials. if (id < 1) { throw new
   * PresentationException("Id cannot be under 1", Status.BAD_REQUEST); }
   * 
   * UserDTO user = this.userUcc.findById(id);
   * 
   * return ResponseMaker.createResponseWithToken(user); }
   */

  /**
   * get all users.
   * 
   * @return list of all users.
   */
  /*
   * @GET
   * 
   * @AuthorizeBoss public Response allUsers() { List<UserDTO> listUsers = new ArrayList<UserDTO>(); listUsers = userUcc.getAll();
   * 
   * return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("list", listUsers); }
   */



  // ******************** Private's Methods ********************

  /*
   * private void checkTimestampPattern(String name, String toVerify) { toVerify = toVerify.replaceFirst("T", " "); String timestampPattern =
   * "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$"; Pattern pattern = Pattern.compile(timestampPattern, Pattern.CASE_INSENSITIVE); Matcher
   * matcher = pattern.matcher(toVerify); if (!matcher.find()) { throw new PresentationException(name + " is not matching a Timestamp pattern.",
   * Status.BAD_REQUEST); } }
   */
}
