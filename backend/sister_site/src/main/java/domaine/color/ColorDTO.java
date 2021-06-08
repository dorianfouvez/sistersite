/**
 * @author Fouvez Dorian.
 */
package domaine.color;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ColorImpl.class)
public interface ColorDTO {

  int getId();

  void setId(int id);

  String getColor();

  void setColor(String color);

  void fullFillColor(int id, String color);

}
