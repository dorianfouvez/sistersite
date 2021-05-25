/**
 * @author Fouvez Dorian.
 */
package domaine.director_short_film;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DirectorShortFilmImpl.class)
public interface DirectorShortFilmDTO {

  int getShortFilm();

  void setShortFilm(int shortFilm);

  int getDirector();

  void setDirector(int director);

}
