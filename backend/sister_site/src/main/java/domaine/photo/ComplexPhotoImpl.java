/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.user.UserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexPhotoImpl implements ComplexPhotoDTO {

  private int id;
  private String name;
  private String picture;
  private MakeupArtistDTO makeupArtist;
  private PhotographerDTO photographer;
  private UserDTO sharer;
  private Timestamp date;

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPicture() {
    return this.picture;
  }

  @Override
  public void setPicture(String picture) {
    this.picture = picture;
  }

  @Override
  public Timestamp getDate() {
    return this.date;
  }

  @Override
  public void setDate(Timestamp date) {
    this.date = date;
  }

  @Override
  public MakeupArtistDTO getMakeupArtist() {
    return this.makeupArtist;
  }

  @Override
  public void setMakeupArtist(MakeupArtistDTO makeupArtist) {
    this.makeupArtist = makeupArtist;
  }

  @Override
  public PhotographerDTO getPhotographer() {
    return this.photographer;
  }

  @Override
  public void setPhotographer(PhotographerDTO photographer) {
    this.photographer = photographer;
  }

  @Override
  public UserDTO getSharer() {
    return this.sharer;
  }

  @Override
  public void setSharer(UserDTO sharer) {
    this.sharer = sharer;
  }

  @Override
  public void fullFillPhoto(int id, String name, String picture, MakeupArtistDTO makeupArtist,
      PhotographerDTO photographer, UserDTO sharer, Timestamp date) {
    setId(id);
    setName(name);
    setPicture(picture);
    setMakeupArtist(makeupArtist);
    setPhotographer(photographer);
    setSharer(sharer);
    setDate(date);
  }

  @Override
  public String toString() {
    return "ComplexPhotoImpl [id=" + id + ", name=" + name + ", picture=" + picture
        + ", makeupArtist=" + makeupArtist + ", photographer=" + photographer + ", sharer=" + sharer
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
    ComplexPhotoImpl other = (ComplexPhotoImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
