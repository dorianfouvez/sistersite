/**
 * @author Fouvez Dorian.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.photographer.PhotographerDTO;
import jakarta.inject.Inject;

public class PhotographerDAOImpl implements PhotographerDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public PhotographerDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + PhotographerDAO.getAllPhotographerAttributes() + " FROM"
            + PhotographerDAO.getPhotographerTableName() + " WHERE id = ?");
    PhotographerDTO photographer = null;
    try {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          photographer = createFullFillPhotographer(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findById", e);
    }
    return photographer;
  }

  @Override
  public List<PhotographerDTO> getAll() {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + PhotographerDAO.getAllPhotographerAttributes() + " FROM"
            + PhotographerDAO.getPhotographerTableName() + " ORDER BY "
            + PhotographerDAO.getPhotographerAbbreviation() + ".id");
    List<PhotographerDTO> photographers = new ArrayList<PhotographerDTO>();
    try {
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          PhotographerDTO photographer = createFullFillPhotographer(rs);
          photographers.add(photographer);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getAll photographers", e);
    }
    return photographers;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a photo from the ResultSet.
   * 
   * @param rs ResultSet who contains the photo.
   * @return the created and fully filled photo.
   */
  private PhotographerDTO createFullFillPhotographer(ResultSet rs) {
    PhotographerDTO photographer = domaineFactory.getPhotographerDTO();

    try {
      photographer.setId(rs.getInt(1));
      photographer.setName(rs.getString(2));
      photographer.setInstagram(rs.getString(3));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillPhotographer", e);
    }

    return photographer;
  }

}
