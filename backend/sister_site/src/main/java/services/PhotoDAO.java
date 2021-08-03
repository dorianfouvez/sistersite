/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.photo.ComplexPhotoDTO;
import domaine.photo.PhotoDTO;

public interface PhotoDAO {

  PhotoDTO findById(int id);

  PhotoDTO findByName(String name);

  PhotoDTO add(PhotoDTO photo);

  PhotoDTO delete(int id);

  List<PhotoDTO> getBook(int tagId);

  List<PhotoDTO> getAll(int userId);

  List<ComplexPhotoDTO> getAllFullInfosPhoto(int id);



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
