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
import domaine.distinction.DistinctionDTO;
import jakarta.inject.Inject;

public class DistinctionDAOImpl implements DistinctionDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<DistinctionDTO> getAllDistinctionForCinema(int cinemaId) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + DistinctionDAO.getAllDistinctionAttributes() + ","
            + DistinctionCinemaDAO.getAllDistinctionCinemaAttributes() + " FROM"
            + DistinctionDAO.getDistinctionTableName() + " JOIN"
            + DistinctionCinemaDAO.getDistinctionCinemaTableName()
            + " ON dsci.distinction_id = ds.id" + " WHERE dsci.cinema_id = ?");
    List<DistinctionDTO> distinctions = new ArrayList<>();
    try {
      ps.setInt(1, cinemaId);
      distinctions = createArrayOfFullFillDistinctionsFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return distinctions;
  }



  // ******************** Private's Methods ********************

  private DistinctionDTO createFullFillDistinctionFromResultSet(ResultSet rs) {
    DistinctionDTO distinction = this.domaineFactory.getDistinctionDTO();
    try {
      int id = rs.getInt(1);
      String name = rs.getString(2);

      distinction.fullFillDistinction(id, name);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the distinction from ResultSet.",
          e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the distinction from ResultSet.",
          e);
    }
    return distinction;
  }

  private List<DistinctionDTO> createArrayOfFullFillDistinctionsFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<DistinctionDTO> distinctions = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        distinctions.add(createFullFillDistinctionFromResultSet(rs));
      }
    }
    return distinctions;
  }

}
