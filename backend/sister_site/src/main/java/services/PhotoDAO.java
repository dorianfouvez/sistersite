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
    return " p.id, p.name, p.picture, p.photographer";
  }

  static String getPhotoTableName() {
    return " ambre_fouvez.photos p";
  }

}
