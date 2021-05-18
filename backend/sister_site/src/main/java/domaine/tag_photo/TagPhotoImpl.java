/**
 * @author Fouvez Dorian.
 */
package domaine.tag_photo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagPhotoImpl implements TagPhotoDTO {

  private int photoId;
  private int tagId;

  @Override
  public int getPhotoId() {
    return photoId;
  }

  @Override
  public void setPhotoId(int photoId) {
    this.photoId = photoId;
  }

  @Override
  public int getTagId() {
    return tagId;
  }

  @Override
  public void setTagId(int tagId) {
    this.tagId = tagId;
  }

  @Override
  public String toString() {
    return "TagPhotoImpl [photoId=" + photoId + ", tagId=" + tagId + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + photoId;
    result = prime * result + tagId;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TagPhotoImpl other = (TagPhotoImpl) obj;
    if (photoId != other.photoId) {
      return false;
    }
    if (tagId != other.tagId) {
      return false;
    }
    return true;
  }



}
