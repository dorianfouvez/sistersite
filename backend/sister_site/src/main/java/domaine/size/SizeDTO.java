/**
 * @author Fouvez Dorian.
 */
package domaine.size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SizeImpl.class)
public interface SizeDTO {

  int getId();

  void setId(int id);

  String getSize();

  void setSize(String size);

  void fullFillSize(int id, String size);

}
