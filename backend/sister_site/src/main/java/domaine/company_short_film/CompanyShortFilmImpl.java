/**
 * @author Fouvez Dorian.
 */
package domaine.company_short_film;

public class CompanyShortFilmImpl implements CompanyShortFilmDTO {

  private int shortFilm;
  private int company;

  @Override
  public int getShortFilm() {
    return shortFilm;
  }

  @Override
  public void setShortFilm(int shortFilm) {
    this.shortFilm = shortFilm;
  }

  @Override
  public int getCompany() {
    return company;
  }

  @Override
  public void setCompany(int company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "CompanyShortFilmImpl [shortFilm=" + shortFilm + ", company=" + company + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + company;
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
    CompanyShortFilmImpl other = (CompanyShortFilmImpl) obj;
    if (company != other.company) {
      return false;
    }
    if (shortFilm != other.shortFilm) {
      return false;
    }
    return true;
  }

}
