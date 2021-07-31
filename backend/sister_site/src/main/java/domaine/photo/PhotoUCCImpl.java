/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.List;
import api.utils.BusinessException;
import domaine.tag_photo.TagPhotoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.PhotoDAO;

public class PhotoUCCImpl implements PhotoUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private PhotoDAO photoDAO;



  @Override
  public PhotoDTO findById(int id) {
    dalservices.startTransaction();
    PhotoDTO photoDTO = photoDAO.findById(id);
    if (photoDTO == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Photo doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return photoDTO;
  }

  @Override
  public boolean nameAlreadyExist(String name) {
    dalservices.startTransaction();
    PhotoDTO photoDTO = photoDAO.findByName(name);
    dalservices.commitTransaction();
    if (photoDTO == null) {
      return false;
    }
    return true;
  }

  @Override
  public List<PhotoDTO> addPhotos(List<PhotoDTO> photos, List<TagPhotoDTO> tagsPhotos) {
    dalservices.startTransaction();
    // TODO Create photo
    // TODO Create tags_photo
    dalservices.commitTransaction();
    throw new UnsupportedOperationException("Not implemented yet!");
  }

}
