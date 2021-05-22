/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ShortFilmImpl.class)
public interface ShortFilmDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  int getRole();

  void setRole(int role);

  int getYear();

  void setYear(int year);

}
