/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.photo.ComplexPhotoDTO;
import domaine.photo.PhotoDTO;
import domaine.user.UserDTO;

public interface PhotoDAO {

  PhotoDTO add(PhotoDTO photo);

  PhotoDTO delete(int id);

  PhotoDTO findById(int id);

  PhotoDTO findByName(String name);

  PhotoDTO findByNameButNotFor(int id, String name);

  List<PhotoDTO> getAll(int userId);

  List<ComplexPhotoDTO> getAllFullInfosPhotoFor(int id);

  List<PhotoDTO> getBook(int tagId);

  boolean isOwnPhoto(UserDTO user, int photoId);

  PhotoDTO update(PhotoDTO photo);



  // ******************** Static's Methods ********************

  static String getAllPhotoAttributes() {
    return getAllPhotoAttributesWithName(getPhotoAbbreviation());
  }

  static String getAllPhotoAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".name, " + name + ".picture, " + name + ".makeup_artist, "
        + name + ".photographer, " + name + ".sharer, " + name + ".date";
  }

  static String getPhotoAbbreviation() {
    return "p";
  }

  static String getPhotoTableName() {
    return getPhotoTableNameWithName(getPhotoAbbreviation());
  }

  static String getPhotoTableNameWithName(String name) {
    return " ambre_fouvez.photos " + name;
  }

  static String getPhotoTableNameWithoutAbbreviation() {
    return " ambre_fouvez.photos";
  }

}
