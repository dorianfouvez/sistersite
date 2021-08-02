/**
 * @author Fouvez Dorian.
 */
package api;

import java.util.List;
import org.glassfish.jersey.server.ContainerRequest;
import api.filters.AnonymousOrAuthorize;
import api.utils.ResponseMaker;
import domaine.tag.TagDTO;
import domaine.tag.TagUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Singleton
@Path("/tags")
public class TagsResource {

  @Inject
  private TagUCC tagUCC;



  /**
   * Get all the tags.
   * 
   * @param request header with the token.
   * @return a list of all the tags.
   */
  @GET
  @Path("/all")
  @AnonymousOrAuthorize
  public Response getAll(@Context ContainerRequest request) {
    List<TagDTO> tags = this.tagUCC.getAll();
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("tags", tags);
  }

}
