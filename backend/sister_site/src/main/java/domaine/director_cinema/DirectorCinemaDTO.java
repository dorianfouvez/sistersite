/**
 * @author Fouvez Dorian.
 */
package domaine.director_cinema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DirectorCinemaImpl.class)
public interface DirectorCinemaDTO {

  int getCinema();

  void setCinema(int cinema);

  int getDirector();

  void setDirector(int director);

}
