/**
 * @author Fouvez Dorian.
 */
package api;

import org.glassfish.jersey.server.ContainerRequest;
import com.fasterxml.jackson.databind.JsonNode;
import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.user.ComplexUserDTO;
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
  private UserUCC userUCC;



  @GET
  @Path("/complex")
  @Authorize
  public Response getComplexUser(@Context ContainerRequest request) {
    UserDTO currentUser = (UserDTO) request.getProperty("user");

    ComplexUserDTO complexUser = this.userUCC.findComplexUserById(currentUser.getID());
    PhotoResource.transformTheURLOfThePhotoIntoBase64Image(complexUser.getProfilePicture());
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("user", complexUser);
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
   * Login the user if exists or send error message.
   * 
   * @param json object containing a username and a password.
   * @return a response with a token who contains the user if it exists in the database and matches the password.
   */
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response login(JsonNode json) {
    checkLoginCredentials(json);

    UserDTO user = this.userUCC.login(json.get("username").asText(), json.get("password").asText());
    return ResponseMaker.createResponseWithToken(user);
  }



  // ******************** Private's Methods ********************

  private void checkLoginCredentials(JsonNode json) {
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
  }

}
