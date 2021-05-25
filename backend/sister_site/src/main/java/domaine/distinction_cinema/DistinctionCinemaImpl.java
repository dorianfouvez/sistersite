/**
 * @author Fouvez Dorian.
 */
package domaine.distinction_cinema;

public class DistinctionCinemaImpl implements DistinctionCinemaDTO {

  private int cinema;
  private int distinction;

  @Override
  public int getCinema() {
    return cinema;
  }

  @Override
  public void setCinema(int cinema) {
    this.cinema = cinema;
  }

  @Override
  public int getDistinction() {
    return distinction;
  }

  @Override
  public void setDistinction(int distinction) {
    this.distinction = distinction;
  }

  @Override
  public String toString() {
    return "DistinctionCinemaImpl [distinction=" + distinction + ", cinema=" + cinema + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + cinema;
    result = prime * result + distinction;
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
    DistinctionCinemaImpl other = (DistinctionCinemaImpl) obj;
    if (cinema != other.cinema) {
      return false;
    }
    if (distinction != other.distinction) {
      return false;
    }
    return true;
  }

}
