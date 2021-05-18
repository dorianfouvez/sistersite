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


  @Override
  public void delete(int id) {
    dalservices.startTransaction();

    /*
     * PhotoFurnitureDTO photoFurnitureDTO = photoFurnitureDAO.delete(id); if (photoFurnitureDTO != null) { dalservices.rollbackTransaction(); throw new
     * BusinessException("Photo_Furniture doesn't delete", Status.BAD_REQUEST); } PhotoDTO photoDTO = photoDAO.delete(id); if (photoDTO != null) {
     * dalservices.rollbackTransaction(); throw new BusinessException("Photo doesn't delete", Status.BAD_REQUEST); }
     */

    dalservices.commitTransaction();
  }

}
