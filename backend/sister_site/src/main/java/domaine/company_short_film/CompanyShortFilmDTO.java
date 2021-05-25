/**
 * @author Fouvez Dorian.
 */
package domaine.company_short_film;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CompanyShortFilmImpl.class)
public interface CompanyShortFilmDTO {

  int getShortFilm();

  void setShortFilm(int shortFilm);

  int getCompany();

  void setCompany(int company);

}
