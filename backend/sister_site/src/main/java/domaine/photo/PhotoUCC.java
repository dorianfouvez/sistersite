/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.List;
import domaine.tag_photo.TagPhotoDTO;

public interface PhotoUCC {

  PhotoDTO findById(int id);

  boolean nameAlreadyExist(String name);

  List<PhotoDTO> addPhotos(List<PhotoDTO> photos, List<TagPhotoDTO> tagsPhotos);

  List<PhotoDTO> getBook(int tagId);

}
