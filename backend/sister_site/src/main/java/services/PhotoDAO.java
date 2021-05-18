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

}
