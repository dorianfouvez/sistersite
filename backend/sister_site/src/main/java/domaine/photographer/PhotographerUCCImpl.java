/**
 * @author Fouvez Dorian.
 */
package domaine.photographer;

import java.util.List;
import api.utils.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.PhotographerDAO;

public class PhotographerUCCImpl implements PhotographerUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private PhotographerDAO photographerDAO;



  @Override
  public PhotographerDTO findById(int id) {
    dalservices.startTransaction();
    PhotographerDTO photographerDTO = this.photographerDAO.findById(id);
    if (photographerDTO == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Photographer doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return photographerDTO;
  }

  @Override
  public List<PhotographerDTO> getAll() {
    dalservices.startTransaction();
    List<PhotographerDTO> photographers = this.photographerDAO.getAll();
    dalservices.commitTransaction();
    return photographers;
  }

  @Override
  public PhotographerDTO add(PhotographerDTO photographer) {
    dalservices.startTransaction();
    PhotographerDTO photographerDTO = this.photographerDAO.add(photographer);
    dalservices.commitTransaction();
    return photographerDTO;
  }

  @Override
  public boolean nameAlreadyExist(String name) {
    dalservices.startTransaction();
    PhotographerDTO photographerDTO = this.photographerDAO.findByName(name);
    dalservices.commitTransaction();
    if (photographerDTO == null) {
      return false;
    }
    return true;
  }

}
