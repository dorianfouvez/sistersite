/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;
import domaine.user.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddPhotoInformationImpl implements AddPhotoInformationDTO {

  private List<PhotographerDTO> photographers = new ArrayList<PhotographerDTO>();
  private List<UserDTO> sharers = new ArrayList<UserDTO>();
  private List<TagDTO> tags = new ArrayList<TagDTO>();



  @Override
  public List<PhotographerDTO> getPhotographers() {
    return photographers;
  }

  @Override
  public void setPhotographers(List<PhotographerDTO> photographers) {
    this.photographers = photographers;
  }

  @Override
  public List<UserDTO> getSharers() {
    return sharers;
  }

  @Override
  public void setSharers(List<UserDTO> sharers) {
    this.sharers = sharers;
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
    String result = "Photographers:[";

    for (PhotographerDTO photographer : photographers) {
      result += photographer.toString() + ",";
    }
    result += "]\nSharers:[";

    for (UserDTO sharer : sharers) {
      result += sharer.toString() + ",";
    }
    result += "]\nTags:[";

    for (TagDTO tag : tags) {
      result += tag.toString() + ",";
    }
    result += "]";

    return result;
  }

}
