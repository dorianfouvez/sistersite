/**
 * @author Fouvez Dorian.
 */
package domaine.tag;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = TagImpl.class)
public interface TagDTO {

  int getId();

  void setId(int id);

  String getLabel();

  void setLabel(String label);

}
