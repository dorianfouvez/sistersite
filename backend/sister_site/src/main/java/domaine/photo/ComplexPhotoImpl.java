/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photographer.PhotographerDTO;
import domaine.tag.TagDTO;
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
  private List<TagDTO> tags;

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
  public List<TagDTO> getTags() {
    return this.tags;
  }

  @Override
  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  @Override
  public void fullFillPhoto(int id, String name, String picture, MakeupArtistDTO makeupArtist,
      PhotographerDTO photographer, UserDTO sharer, Timestamp date, List<TagDTO> tags) {
    setId(id);
    setName(name);
    setPicture(picture);
    setMakeupArtist(makeupArtist);
    setPhotographer(photographer);
    setSharer(sharer);
    setDate(date);
    setTags(tags);
  }

  @Override
  public String toString() {
    String result = "ComplexPhotoImpl [id=" + id + ", name=" + name + ", picture=" + picture
        + ", makeupArtist=" + makeupArtist.toString() + ", photographer=" + photographer.toString()
        + ", sharer=" + sharer.toString() + ", date=" + date + ", tags=[";

    for (TagDTO tagDTO : tags) {
      result += tagDTO.toString();
    }

    result += "]]";
    return result;
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
