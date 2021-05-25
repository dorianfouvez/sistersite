/**
 * @author Fouvez Dorian.
 */
package domaine.strength;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = StrengthImpl.class)
public interface StrengthDTO {

  int getId();

  void setId(int id);

  String getLabel();

  void setLabel(String label);

}
