/**
 * @author Fouvez Dorian.
 */
package domaine.cinema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CinemaImpl.class)
public interface CinemaDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  int getRole();

  void setRole(int role);

  int getYear();

  void setYear(int year);

}
