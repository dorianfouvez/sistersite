/**
 * @author Fouvez Dorian.
 */
package domaine.director_short_film;

public class DirectorShortFilmImpl implements DirectorShortFilmDTO {

  private int shortFilm;
  private int director;

  @Override
  public int getShortFilm() {
    return shortFilm;
  }

  @Override
  public void setShortFilm(int shortFilm) {
    this.shortFilm = shortFilm;
  }

  @Override
  public int getDirector() {
    return director;
  }

  @Override
  public void setDirector(int director) {
    this.director = director;
  }

  @Override
  public String toString() {
    return "DirectorShortFilmImpl [director=" + director + ", shortFilm=" + shortFilm + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + shortFilm;
    result = prime * result + director;
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
    DirectorShortFilmImpl other = (DirectorShortFilmImpl) obj;
    if (shortFilm != other.shortFilm) {
      return false;
    }
    if (director != other.director) {
      return false;
    }
    return true;
  }

}
