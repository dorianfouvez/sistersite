/**
 * @author Fouvez Dorian.
 */
package api;

import java.util.List;
import org.glassfish.jersey.server.ContainerRequest;
import com.fasterxml.jackson.databind.JsonNode;
import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.photographer.PhotographerDTO;
import domaine.photographer.PhotographerUCC;
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
@Path("/photographers")
public class PhotographerResource {

  @Inject
  private PhotographerUCC photographerUCC;



  /**
   * Add the photographer.
   * 
   * @param photographer who contains all the information needed.
   * @return a response with a token who contains the photographer.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Authorize
  public Response add(PhotographerDTO photographer) {
    if (this.photographerUCC.nameAlreadyExist(photographer.getName())) {
      throw new PresentationException("The Name \"" + photographer.getName() + "\" is already use.",
          Status.BAD_REQUEST);
    }
    PhotographerDTO newPhotographer = this.photographerUCC.add(photographer);
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("photographer", newPhotographer);
  }

  /**
   * Get all the photographers. Must be Authorize.
   * 
   * @param request header with the token.
   * @return a list of all the photographers.
   */
  @GET
  @Path("/all")
  @Authorize
  public Response getAll(@Context ContainerRequest request) {
    List<PhotographerDTO> photographers = this.photographerUCC.getAll();
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("photographers", photographers);
  }



  // ******************** Private's Methods ********************

  private void checkPhotographerCredentials(JsonNode json) {
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
