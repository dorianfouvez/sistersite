/**
 * @author Fouvez Dorian.
 */
package domaine.cinema;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.director.DirectorDTO;
import domaine.distinction.DistinctionDTO;
import domaine.role.RoleDTO;

@JsonDeserialize(as = ComplexCinemaImpl.class)
public interface ComplexCinemaDTO {

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

  List<DistinctionDTO> getDistinctions();

  void setDistinctions(List<DistinctionDTO> company);

  List<DirectorDTO> getDirectors();

  void setDirectors(List<DirectorDTO> directors);

  void fullFillComplexCinema(int id, String title, RoleDTO role, int year, int orderNumber,
      List<DistinctionDTO> distinctions, List<DirectorDTO> directors);

}
