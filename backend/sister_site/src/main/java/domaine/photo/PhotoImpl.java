/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoImpl implements PhotoDTO {

  private int id;
  private String name;
  private String picture;
  private int makeupArtist;
  private int photographer;
  private int sharer;
  private Timestamp date;

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
  public int getMakeupArtist() {
    return makeupArtist;
  }

  @Override
  public void setMakeupArtist(int makeupArtist) {
    this.makeupArtist = makeupArtist;
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
  public int getSharer() {
    return sharer;
  }

  @Override
  public void setSharer(int sharer) {
    this.sharer = sharer;
  }

  @Override
  public Timestamp getDate() {
    return date;
  }

  @Override
  public void setDate(Timestamp date) {
    this.date = date;
  }

  @Override
  public void fullFillPhoto(int id, String name, String picture, int makeupArtist, int photographer,
      int sharer, Timestamp date) {
    setId(id);
    setPicture(picture);
    setName(name);
    setMakeupArtist(makeupArtist);
    setPhotographer(photographer);
    setSharer(sharer);
    setDate(date);
  }

  @Override
  public String toString() {
    return "PhotoImpl [id=" + id + ", name=" + name + ", make-up artist=" + makeupArtist
        + ", picture=" + picture + ", photographer=" + photographer + ", sharer=" + sharer
        + ", date=" + date + "]";
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
