/**
 * @author Fouvez Dorian.
 */
package api;

import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.makeup_artist.MakeupArtistUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/makeupArtists")
public class MakeupArtistResource {

  @Inject
  private MakeupArtistUCC makeupArtistUCC;



  /**
   * Add the make-up artist.
   *
   * @param makeupArtist who contains all the information needed.
   * @return a response with a token who contains the make-up artist.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Authorize
  public Response add(MakeupArtistDTO makeupArtist) {
    if (this.makeupArtistUCC.nameAlreadyExist(makeupArtist.getName())) {
      throw new PresentationException("The Name \"" + makeupArtist.getName() + "\" is already use.",
          Status.BAD_REQUEST);
    }
    MakeupArtistDTO newMakeupArtist = this.makeupArtistUCC.add(makeupArtist);
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("makeupArtist", newMakeupArtist);
  }

}
