/**
 * @author e-Baron.
 */
package api.filters;

import java.io.IOException;
import domaine.user.UserDTO;
import jakarta.inject.Singleton;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;


@Singleton
@Provider
@Authorize
public class AuthorizationRequestFilter extends AuthorizeAbstract
    implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    UserDTO user = decodedToken(requestContext);
    if (user != null) {
      requestContext.setProperty("user", user);
    }
  }

}
