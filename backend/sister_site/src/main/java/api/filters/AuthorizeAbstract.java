/**
 * @author Fouvez Dorian.
 */
package api.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import api.utils.PresentationException;
import domaine.user.UserDTO;
import domaine.user.UserUCC;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import utils.Config;

public abstract class AuthorizeAbstract {

  private final Algorithm jwtAlgorithm = Algorithm.HMAC256(Config.getProperty("JWTSecret"));
  private final JWTVerifier jwtVerifier =
      JWT.require(this.jwtAlgorithm).withIssuer("auth0").build();

  @Inject
  private UserUCC userUCC;

  /**
   * get a user from the token or abort the request.
   * 
   * @param requestContext contains in header the token as "Authorization".
   * @return a user or null if the token isn't there.
   * @throws PresentationException if Expired or Malformed token.
   */
  public UserDTO decodedToken(ContainerRequestContext requestContext) {
    String token = requestContext.getHeaderString("Authorization");
    if (token == null) {
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
          .entity("A token is needed to access this resource").build());
      return null;
    } else {
      return decodeIfToken(token);
    }
  }

  /**
   * get a user from a token.
   * 
   * @param token contains the id of the user.
   * @return a user or null if the user doesn't exist.
   * @throws PresentationException if Expired or Malformed token.
   */
  public UserDTO decodeIfToken(String token) {
    if (token.startsWith("\"") && token.endsWith("\"")) {
      token = token.substring(1, token.length() - 1);
    }
    DecodedJWT decodedToken = null;
    try {
      decodedToken = this.jwtVerifier.verify(token);
    } catch (TokenExpiredException e) {
      throw new PresentationException("Expired token", e, Status.UNAUTHORIZED);
    } catch (Exception e) {
      throw new PresentationException("Malformed token", e, Status.UNAUTHORIZED);
    }
    return this.userUCC.findById(decodedToken.getClaim("user").asInt());
  }

}
