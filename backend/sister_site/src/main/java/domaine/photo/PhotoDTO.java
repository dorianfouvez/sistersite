/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PhotoImpl.class)
public interface PhotoDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  String getPicture();

  void setPicture(String picture);

  int getPhotographer();

  void setPhotographer(int photographer);

  void fullFillPhoto(int id, String name, String picture, int photographer);

}
