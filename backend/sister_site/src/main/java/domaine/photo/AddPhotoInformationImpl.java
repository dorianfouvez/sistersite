/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddPhotoInformationImpl implements AddPhotoInformationDTO {

  private List<MakeupArtistDTO> makeupArtists = new ArrayList<MakeupArtistDTO>();
  private List<PhotographerDTO> photographers = new ArrayList<PhotographerDTO>();
  private List<TagDTO> tags = new ArrayList<TagDTO>();



  @Override
  public List<MakeupArtistDTO> getMakeupArtists() {
    return makeupArtists;
  }

  @Override
  public void setMakeupArtists(List<MakeupArtistDTO> makeupArtists) {
    this.makeupArtists = makeupArtists;
  }

  @Override
  public List<PhotographerDTO> getPhotographers() {
    return photographers;
  }

  @Override
  public void setPhotographers(List<PhotographerDTO> photographers) {
    this.photographers = photographers;
  }

  @Override
  public List<TagDTO> getTags() {
    return tags;
  }

  @Override
  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    String result = "MakeupArtists:[";

    for (MakeupArtistDTO makeupArtist : makeupArtists) {
      result += makeupArtist.toString() + ",";
    }
    result += "]\nPhotographers:[";

    for (PhotographerDTO photographer : photographers) {
      result += photographer.toString() + ",";
    }
    result += "]\nTags:[";

    for (TagDTO tag : tags) {
      result += tag.toString() + ",";
    }
    result += "]";

    return result;
  }

}
