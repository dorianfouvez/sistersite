package api;

import api.filters.AnonymousOrAuthorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.DomaineFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Singleton
@Path("/curriculumVitea")
public class CurriculumVitaeResource {

  @Inject
  private DomaineFactory domaineFactory;

  @GET
  @Path("/{id}")
  @AnonymousOrAuthorize
  public Response getAllInfosForCV(@PathParam("id") int id) {
    if (id < 1) {
      throw new PresentationException("Id cannot be under 1", Status.BAD_REQUEST);
    }

    // 24 - 25 table ...

    return ResponseMaker.createEmptyResponseOK();
  }

}
