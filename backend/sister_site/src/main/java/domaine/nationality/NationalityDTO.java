/**
 * @author Fouvez Dorian.
 */
package domaine.nationality;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = NationalityImpl.class)
public interface NationalityDTO {

  int getId();

  void setId(int id);

  String getNationality();

  void setNationality(String nationality);

  void fullFillNationality(int id, String nationality);

}
