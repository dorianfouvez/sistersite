/**
 * @author Fouvez Dorian.
 */
package domaine.tag;

import java.util.List;
import api.utils.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.TagDAO;

public class TagUCCImpl implements TagUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private TagDAO tagDAO;



  @Override
  public TagDTO findById(int id) {
    dalservices.startTransaction();
    TagDTO tagDTO = this.tagDAO.findById(id);
    if (tagDTO == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Tag doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return tagDTO;
  }

  @Override
  public List<TagDTO> getAll() {
    dalservices.startTransaction();
    List<TagDTO> tags = this.tagDAO.getAllSortedByLabel();
    dalservices.commitTransaction();
    return tags;
  }

  @Override
  public TagDTO add(TagDTO tag) {
    dalservices.startTransaction();
    TagDTO tagDTO = this.tagDAO.add(tag);
    dalservices.commitTransaction();
    return tagDTO;
  }

  @Override
  public boolean labelAlreadyExist(String label) {
    dalservices.startTransaction();
    TagDTO tagDTO = this.tagDAO.findByLabel(label);
    dalservices.commitTransaction();
    if (tagDTO == null) {
      return false;
    }
    return true;
  }

}
