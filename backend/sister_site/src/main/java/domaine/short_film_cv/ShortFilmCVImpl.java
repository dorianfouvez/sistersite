/**
 * @author Fouvez Dorian.
 */
package domaine.short_film_cv;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortFilmCVImpl implements ShortFilmCVDTO {

  private int curriculumVitae;
  private int shortFilm;
  private int orderNumber;

  @Override
  public int getCurriculumVitae() {
    return curriculumVitae;
  }

  @Override
  public void setCurriculumVitae(int curriculumVitae) {
    this.curriculumVitae = curriculumVitae;
  }

  @Override
  public int getShortFilm() {
    return shortFilm;
  }

  @Override
  public void setShortFilm(int shortFilm) {
    this.shortFilm = shortFilm;
  }

  @Override
  public int getOrderNumber() {
    return orderNumber;
  }

  @Override
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public String toString() {
    return "ShortFilmCVImpl [curriculumVitae=" + curriculumVitae + ", shortFilm=" + shortFilm
        + ", orderNumber=" + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + curriculumVitae;
    result = prime * result + shortFilm;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ShortFilmCVImpl other = (ShortFilmCVImpl) obj;
    if (curriculumVitae != other.curriculumVitae) {
      return false;
    }
    if (shortFilm != other.shortFilm) {
      return false;
    }
    return true;
  }

}
