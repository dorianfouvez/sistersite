package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.strength.StrengthWithOrderDTO;
import jakarta.inject.Inject;

public class StrengthDAOImpl implements StrengthDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<StrengthWithOrderDTO> getAllStrengthWithOrderForCV(int cvId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + StrengthDAO.getAllStrengthAttributes() + "," + StrengthCVDAO.getAllStrengthCVAttributes()
        + " FROM" + StrengthDAO.getStrengthTableName() + " JOIN"
        + StrengthCVDAO.getStrengthCVTableName() + " ON stcv.strength = st.id"
        + " WHERE stcv.curriculum_vitae = ?" + " ORDER BY stcv.order_number");
    List<StrengthWithOrderDTO> strengths = new ArrayList<StrengthWithOrderDTO>();
    try {
      ps.setInt(1, cvId);
      strengths = createArrayOfFullFillStrengthWithOrderFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return strengths;
  }



  // ******************** Private's Methods ********************

  private StrengthWithOrderDTO createFullFillStrengthFromResultSet(ResultSet rs) {
    StrengthWithOrderDTO strength = this.domaineFactory.getStrengthWithOrderDTO();
    try {
      int id = rs.getInt(1);
      String label = rs.getString(2);
      int orderNumber = rs.getInt(5);

      strength.fullFillStrengthWithOrder(id, label, orderNumber);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the strength from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the strength from ResultSet.", e);
    }
    return strength;
  }

  private List<StrengthWithOrderDTO> createArrayOfFullFillStrengthWithOrderFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<StrengthWithOrderDTO> strengths = new ArrayList<StrengthWithOrderDTO>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        strengths.add(createFullFillStrengthFromResultSet(rs));
      }
    }
    return strengths;
  }

}
