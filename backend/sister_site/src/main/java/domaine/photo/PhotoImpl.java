/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoImpl implements PhotoDTO {

  private int id;
  private String name;
  private String picture;
  private int photographer;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPicture() {
    return picture;
  }

  @Override
  public void setPicture(String picture) {
    this.picture = picture;
  }

  @Override
  public int getPhotographer() {
    return photographer;
  }

  @Override
  public void setPhotographer(int photographer) {
    this.photographer = photographer;
  }

  @Override
  public void fullFillPhoto(int id, String name, String picture, int photographer) {
    setId(id);
    setPicture(picture);
    setName(name);
    setPhotographer(photographer);
  }

  @Override
  public String toString() {
    return "PhotoImpl [id=" + id + ", name=" + name + ", picture=" + picture + ", photographer="
        + photographer + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    PhotoImpl other = (PhotoImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
