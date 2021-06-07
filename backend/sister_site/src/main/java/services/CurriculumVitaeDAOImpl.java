package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import jakarta.inject.Inject;

public class CurriculumVitaeDAOImpl implements CurriculumVitaeDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public CurriculumVitaeDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + CurriculumVitaeDAO.getAllCurriculumVitaeAttributes()
            + " FROM" + CurriculumVitaeDAO.getCurriculumVitaeTableName() + " WHERE id = ?");
    CurriculumVitaeDTO cv;
    try {
      ps.setInt(1, id);
      cv = createFullFillCVFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    return cv;
  }

  @Override
  public Object[] getFullInfosCurriculumVitae(int id) {
    // TODO finish it
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + CurriculumVitaeDAO.getAllCurriculumVitaeAttributes() + ","
            + UserDAO.getAllUserAttributes() + "," + PhotoDAO.getAllPhotoAttributes() + ","
            + AddressDAO.getAllAddressAttributes() + "," + ColorDAO.getAllColorAttributes() + ","
            + SizeDAO.getAllSizeAttributes() + "," + ColorDAO.getAllColorAttributesWithName("c2")
            + "," + NationalityDAO.getAllNationalityAttributes() + ","
            + NationalityDAO.getAllNationalityAttributesWithName("n2") + ","
            + ProfessionDAO.getAllProfessionAttributes() + ","
            + PhotoDAO.getAllPhotoAttributesWithName("p2") + " FROM"
            + CurriculumVitaeDAO.getCurriculumVitaeTableName() + "," + " JOIN"
            + UserDAO.getUserTableName() + " ON u.id = cv.user" + " JOIN"
            + PhotoDAO.getPhotoTableName() + " ON p.id = u.profile_picture" + " LEFT JOIN"
            + AddressDAO.getAddressTableName() + " ON a.id = u.address" + " LEFT JOIN"
            + ColorDAO.getColorTableName() + " ON c.id = u.hair_color" + " LEFT JOIN"
            + SizeDAO.getSizeTableName() + " ON s.id = u.hair_size" + " LEFT JOIN"
            + ColorDAO.getColorTableNameWithName("c2") + " ON c2.id = u.eye" + " LEFT JOIN"
            + NationalityDAO.getNationalityTableName() + " ON n.id = u.first_nationality"
            + " LEFT JOIN" + NationalityDAO.getNationalityTableNameWithName("n2")
            + " ON n2.id = u.second_nationality" + " JOIN" + ProfessionDAO.getProfessionTableName()
            + " ON pr.id = cv.profession" + " JOIN" + PhotoDAO.getPhotoTableNameWithName("p2")
            + " ON p2.id = cv.background_picture" + " WHERE cv.id = ?");
    Object[] cv = new Object[11];
    int i = 0;

    return cv;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a cv. Or throw a FatalException if a SQLException is caught.
   * 
   * @param rs ResultSet who contains the cv.
   * @return the UserDTO in the ResultSet.
   */
  private CurriculumVitaeDTO createFullFillCVFromResultSet(ResultSet rs) {
    CurriculumVitaeDTO cv = domaineFactory.getCurriculumVitaeDTO();
    try {
      cv.setId(rs.getInt(1));
      cv.setTitle(rs.getString(2));
      cv.setUserId(rs.getInt(3));
      cv.setProfession(rs.getInt(4));
      cv.setPlayingAge(rs.getString(5));
      cv.setBackgroundPicture(rs.getInt(6));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the cv from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the cv from ResultSet.", e);
    }
    return cv;
  }

  /**
   * Create and fully fill a cv with the PreparedStatement from de db. Or throws SQLException.
   * 
   * @param ps the PreparedStatement already Set.
   * @return the cv filled from the PreparedStatement.
   * @throws SQLException if problems.
   */
  private CurriculumVitaeDTO createFullFillCVFromPreparedStatement(PreparedStatement ps)
      throws SQLException {
    CurriculumVitaeDTO cv = domaineFactory.getCurriculumVitaeDTO();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        cv = createFullFillCVFromResultSet(rs);
      }
    }
    return cv;
  }

}
