/**
 * @author Fouvez Dorian.
 */
package domaine.makeup_artist;

import java.util.List;
import api.utils.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.MakeupArtistDAO;

public class MakeupArtistUCCImpl implements MakeupArtistUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private MakeupArtistDAO makeupArtistDAO;



  @Override
  public MakeupArtistDTO findById(int id) {
    dalservices.startTransaction();
    MakeupArtistDTO makeupArtistDTO = makeupArtistDAO.findById(id);
    if (makeupArtistDTO == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Make-up Artist doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return makeupArtistDTO;
  }

  @Override
  public List<MakeupArtistDTO> getAll() {
    dalservices.startTransaction();
    List<MakeupArtistDTO> makeupArtists = makeupArtistDAO.getAll();
    dalservices.commitTransaction();
    return makeupArtists;
  }

  @Override
  public MakeupArtistDTO add(MakeupArtistDTO makeupArtist) {
    dalservices.startTransaction();
    MakeupArtistDTO makeupArtistDTO = this.makeupArtistDAO.add(makeupArtist);
    dalservices.commitTransaction();
    return makeupArtistDTO;
  }

  @Override
  public boolean nameAlreadyExist(String name) {
    dalservices.startTransaction();
    MakeupArtistDTO makeupArtistDTO = this.makeupArtistDAO.findByName(name);
    dalservices.commitTransaction();
    if (makeupArtistDTO == null) {
      return false;
    }
    return true;
  }

  @Override
  public boolean instagramAlreadyExist(String instagram) {
    dalservices.startTransaction();
    MakeupArtistDTO makeupArtistDTO = this.makeupArtistDAO.findByInstagram(instagram);
    dalservices.commitTransaction();
    if (makeupArtistDTO == null) {
      return false;
    }
    return true;
  }

}
