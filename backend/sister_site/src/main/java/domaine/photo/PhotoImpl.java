/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoImpl implements PhotoDTO {

  private int id;
  private String picture;
  private String name;
  private String photographerName;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
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
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPhotographerName() {
    return photographerName;
  }

  @Override
  public void setPhotographerName(String photographername) {
    this.photographerName = photographername;
  }

  @Override
  public String toString() {
    return "PhotoImpl [id=" + id + ", picture=" + picture + ", name=" + name + ", photographerName="
        + photographerName + "]";
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
