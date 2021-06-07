/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.photo.PhotoDTO;

public interface PhotoDAO {

  PhotoDTO findById(int id);

  PhotoDTO findByName(String name);

  List<PhotoDTO> getAll();

  PhotoDTO add(PhotoDTO photo);

  PhotoDTO delete(int id);



  // ******************** Static's Methods ********************

  static String getAllPhotoAttributes() {
    return getAllPhotoAttributesWithName("p");
  }

  static String getAllPhotoAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".name, " + name + ".picture, " + name + ".photographer";
  }

  static String getPhotoTableName() {
    return getPhotoTableNameWithName("p");
  }

  static String getPhotoTableNameWithName(String name) {
    return " ambre_fouvez.photos " + name;
  }

}
