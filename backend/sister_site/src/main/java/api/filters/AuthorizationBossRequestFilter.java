/**
 * @author Fouvez Dorian.
 */
package api.filters;

import java.io.IOException;
import api.utils.PresentationException;
import domaine.user.UserDTO;
import jakarta.inject.Singleton;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;


@Singleton
@Provider
@AuthorizeBoss
public class AuthorizationBossRequestFilter extends AuthorizeAbstract
    implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    UserDTO user = decodedToken(requestContext);
    if (user != null) {
      if (user.isBoss()) {
        requestContext.setProperty("user", user);
      } else {
        throw new PresentationException("You are not a boss.", Status.UNAUTHORIZED);
      }
    } else {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
          .entity("Can't find a user from this token.").build());
      return;
    }
  }
}
