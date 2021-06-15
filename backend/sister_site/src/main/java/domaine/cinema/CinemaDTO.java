/**
 * @author Fouvez Dorian.
 */
package domaine.cinema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CinemaImpl.class)
public interface CinemaDTO extends CinemaWithoutRoleDTO {

  int getRole();

  void setRole(int role);

}
