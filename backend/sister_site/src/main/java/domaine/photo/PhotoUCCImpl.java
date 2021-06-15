/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import api.utils.BusinessException;
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

}
