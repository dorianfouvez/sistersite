/**
 * @author Fouvez Dorian.
 */
package domaine.director;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DirectorImpl.class)
public interface DirectorDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  void fullFillDirector(int id, String name);

}
