/**
 * @author Fouvez Dorian.
 */
package domaine.short_film_cv;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ShortFilmCVImpl.class)
public interface ShortFilmCVDTO {

  int getCurriculumVitae();

  void setCurriculumVitae(int curriculumVitae);

  int getShortFilm();

  void setShortFilm(int shortFilm);

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

}
