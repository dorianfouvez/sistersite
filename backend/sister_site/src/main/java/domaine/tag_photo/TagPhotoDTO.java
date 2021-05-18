/**
 * @author Fouvez Dorian.
 */
package domaine.tag_photo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = TagPhotoImpl.class)
public interface TagPhotoDTO {

  int getPhotoId();

  void setPhotoId(int photoId);

  int getTagId();

  void setTagId(int tagId);

}
