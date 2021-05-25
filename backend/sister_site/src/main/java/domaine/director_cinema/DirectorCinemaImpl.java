/**
 * @author Fouvez Dorian.
 */
package domaine.director_cinema;

public class DirectorCinemaImpl implements DirectorCinemaDTO {

  private int cinema;
  private int director;

  @Override
  public int getCinema() {
    return cinema;
  }

  @Override
  public void setCinema(int cinema) {
    this.cinema = cinema;
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
    return "DirectorCinemaImpl [director=" + director + ", cinema=" + cinema + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + cinema;
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
    DirectorCinemaImpl other = (DirectorCinemaImpl) obj;
    if (cinema != other.cinema) {
      return false;
    }
    if (director != other.director) {
      return false;
    }
    return true;
  }

}
