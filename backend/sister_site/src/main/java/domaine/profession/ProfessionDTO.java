/**
 * @author Fouvez Dorian.
 */
package domaine.profession;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ProfessionImpl.class)
public interface ProfessionDTO {

  int getId();

  void setId(int id);

  String getLabel();

  void setLabel(String label);

  void fullFillProfession(int id, String label);

}
