/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.company.CompanyDTO;
import domaine.director.DirectorDTO;
import domaine.role.RoleDTO;

@JsonDeserialize(as = ComplexShortFilmImpl.class)
public interface ComplexShortFilmDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  RoleDTO getRole();

  void setRole(RoleDTO role);

  int getYear();

  void setYear(int year);

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

  CompanyDTO getCompany();

  void setCompany(CompanyDTO company);

  List<DirectorDTO> getDirectors();

  void setDirectors(List<DirectorDTO> directors);

  void fullFillComplexShortFilm(int id, String title, RoleDTO role, int year, int orderNumber,
      CompanyDTO company, List<DirectorDTO> directors);

}
