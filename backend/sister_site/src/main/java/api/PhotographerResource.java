/**
 * @author Fouvez Dorian.
 */
package api;

import java.util.List;
import org.glassfish.jersey.server.ContainerRequest;
import api.filters.Authorize;
import api.utils.ResponseMaker;
import domaine.photographer.PhotographerDTO;
import domaine.photographer.PhotographerUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Singleton
@Path("/photographers")
public class PhotographerResource {

  @Inject
  private PhotographerUCC photographerUCC;



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

}
