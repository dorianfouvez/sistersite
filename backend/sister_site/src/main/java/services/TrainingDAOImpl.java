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
import domaine.training.TrainingWithOrderDTO;
import jakarta.inject.Inject;

public class TrainingDAOImpl implements TrainingDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<TrainingWithOrderDTO> getAllTrainingWithOrderForCV(int cvId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + TrainingDAO.getAllTrainingAttributes() + "," + TrainingCVDAO.getAllTrainingCVAttributes()
        + " FROM" + TrainingDAO.getTrainingTableName() + " JOIN"
        + TrainingCVDAO.getTrainingCVTableName() + " ON trcv.training = tr.id"
        + " WHERE trcv.curriculum_vitae = ?" + " ORDER BY trcv.order_number");
    List<TrainingWithOrderDTO> trainings = new ArrayList<TrainingWithOrderDTO>();
    try {
      ps.setInt(1, cvId);
      trainings = createArrayOfFullFillTrainingWithOrderFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return trainings;
  }



  // ******************** Private's Methods ********************

  private TrainingWithOrderDTO createFullFillTrainingFromResultSet(ResultSet rs) {
    TrainingWithOrderDTO training = this.domaineFactory.getTrainingWithOrderDTO();
    try {
      int id = rs.getInt(1);
      int startYear = rs.getInt(2);
      int endYear = rs.getInt(3);
      String label = rs.getString(4);
      String explanations = rs.getString(5);
      int orderNumber = rs.getInt(8);

      training.fullFillTrainingWithOrder(id, startYear, endYear, label, explanations, orderNumber);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the training from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the training from ResultSet.", e);
    }
    return training;
  }

  private List<TrainingWithOrderDTO> createArrayOfFullFillTrainingWithOrderFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<TrainingWithOrderDTO> trainings = new ArrayList<TrainingWithOrderDTO>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        trainings.add(createFullFillTrainingFromResultSet(rs));
      }
    }
    return trainings;
  }

}
