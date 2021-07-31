/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;
import domaine.user.UserDTO;

@JsonDeserialize(as = AddPhotoInformationImpl.class)
public interface AddPhotoInformationDTO {

  List<PhotographerDTO> getPhotographers();

  void setPhotographers(List<PhotographerDTO> photographers);

  List<UserDTO> getSharers();

  void setSharers(List<UserDTO> sharers);

  List<TagDTO> getTags();

  void setTags(List<TagDTO> tags);

}
