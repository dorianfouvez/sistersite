/**
 * @author Fouvez Dorian.
 */
package api;

import java.util.List;
import org.glassfish.jersey.server.ContainerRequest;
import api.filters.AnonymousOrAuthorize;
import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.tag.TagDTO;
import domaine.tag.TagUCC;
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
@Path("/tags")
public class TagsResource {

  @Inject
  private TagUCC tagUCC;



  /**
   * Add the tag.
   * 
   * @param tag who contains all the information needed.
   * @return a response with a token who contains the tag.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Authorize
  public Response add(TagDTO tag) {
    if (this.tagUCC.labelAlreadyExist(tag.getLabel())) {
      throw new PresentationException("The Name \"" + tag.getLabel() + "\" is already use.",
          Status.BAD_REQUEST);
    }

    TagDTO newTag = this.tagUCC.add(tag);
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("tag", newTag);
  }

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
