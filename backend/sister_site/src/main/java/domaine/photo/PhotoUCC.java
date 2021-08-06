/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.List;
import domaine.tag_photo.TagPhotoDTO;
import domaine.user.UserDTO;

public interface PhotoUCC {

  List<PhotoDTO> addPhotos(List<PhotoDTO> photos, List<TagPhotoDTO> tagsPhotos);

  PhotoDTO delete(int id, UserDTO user);

  PhotoDTO findById(int id);

  List<ComplexPhotoDTO> getAllFor(int userId);

  List<PhotoDTO> getBook(int tagId);

  boolean nameAlreadyExist(String name);

  boolean nameAlreadyExistButNotFor(int id, String name);

  PhotoDTO update(PhotoDTO photo, TagPhotoDTO tagPhoto, int lastTagId, UserDTO user);

}
