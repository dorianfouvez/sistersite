/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.user.UserDTO;

@JsonDeserialize(as = ComplexPhotoImpl.class)
public interface ComplexPhotoDTO extends BasicPhotoDTO {

  MakeupArtistDTO getMakeupArtist();

  void setMakeupArtist(MakeupArtistDTO makeupArtist);

  PhotographerDTO getPhotographer();

  void setPhotographer(PhotographerDTO photographer);

  UserDTO getSharer();

  void setSharer(UserDTO sharer);

  void fullFillPhoto(int id, String name, String picture, MakeupArtistDTO makeupArtist,
      PhotographerDTO photographer, UserDTO sharer, Timestamp date);

}
