/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ShortFilmImpl.class)
public interface ShortFilmDTO extends BasicShortFilmDTO {

  int getRole();

  void setRole(int role);

}
