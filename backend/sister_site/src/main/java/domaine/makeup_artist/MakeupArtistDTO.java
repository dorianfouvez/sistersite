/**
 * @author Fouvez Dorian.
 */
package domaine.makeup_artist;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = MakeupArtistImpl.class)
public interface MakeupArtistDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  String getInstagram();

  void setInstagram(String instagram);

}
