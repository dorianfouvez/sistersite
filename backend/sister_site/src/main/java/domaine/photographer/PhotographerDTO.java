/**
 * @author Fouvez Dorian.
 */
package domaine.photographer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PhotographerImpl.class)
public interface PhotographerDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

}
