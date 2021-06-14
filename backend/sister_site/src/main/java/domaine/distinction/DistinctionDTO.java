/**
 * @author Fouvez Dorian.
 */
package domaine.distinction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DistinctionImpl.class)
public interface DistinctionDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  void fullFillDistinction(int id, String name);

}
