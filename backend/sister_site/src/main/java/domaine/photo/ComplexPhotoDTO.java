/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;
import domaine.user.UserDTO;

@JsonDeserialize(as = ComplexPhotoImpl.class)
public interface ComplexPhotoDTO extends BasicPhotoDTO {

  MakeupArtistDTO getMakeupArtist();

  void setMakeupArtist(MakeupArtistDTO makeupArtist);

  PhotographerDTO getPhotographer();

  void setPhotographer(PhotographerDTO photographer);

  UserDTO getSharer();

  void setSharer(UserDTO sharer);

  List<TagDTO> getTags();

  void setTags(List<TagDTO> tags);

  void fullFillPhoto(int id, String name, String picture, MakeupArtistDTO makeupArtist,
      PhotographerDTO photographer, UserDTO sharer, Timestamp date, List<TagDTO> tags);

}
