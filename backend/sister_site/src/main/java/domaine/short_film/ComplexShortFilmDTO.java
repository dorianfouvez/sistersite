/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

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

  DirectorDTO getDirector();

  void setDirector(DirectorDTO director);

}
