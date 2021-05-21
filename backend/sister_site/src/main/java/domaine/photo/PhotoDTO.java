/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PhotoImpl.class)
public interface PhotoDTO {

  int getId();

  void setId(int id);

  String getPicture();

  void setPicture(String picture);

  String getName();

  void setName(String name);

  int getPhotographer();

  void setPhotographer(int photographer);

}
