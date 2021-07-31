/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;

@JsonDeserialize(as = AddPhotoInformationImpl.class)
public interface AddPhotoInformationDTO {

  List<MakeupArtistDTO> getMakeupArtists();

  void setMakeupArtists(List<MakeupArtistDTO> makeupArtists);

  List<PhotographerDTO> getPhotographers();

  void setPhotographers(List<PhotographerDTO> photographers);

  List<TagDTO> getTags();

  void setTags(List<TagDTO> tags);

}
