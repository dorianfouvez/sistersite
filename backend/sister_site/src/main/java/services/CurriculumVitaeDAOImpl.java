/**
 * @author Fouvez Dorian.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.address.AddressDTO;
import domaine.cinema.ComplexCinemaDTO;
import domaine.color.ColorDTO;
import domaine.curriculum_vitae.ComplexCurriculumVitaeDTO;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ComplexShortFilmDTO;
import domaine.size.SizeDTO;
import domaine.strength.StrengthWithOrderDTO;
import domaine.training.TrainingWithOrderDTO;
import domaine.user.ComplexUserDTO;
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
  public ComplexCurriculumVitaeDTO getFullInfosCurriculumVitae(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + CurriculumVitaeDAO.getAllCurriculumVitaeAttributes() + ","
            + UserDAO.getAllUserAttributes() + "," + PhotoDAO.getAllPhotoAttributes() + ","
            + AddressDAO.getAllAddressAttributes() + "," + ColorDAO.getAllColorAttributes() + ","
            + SizeDAO.getAllSizeAttributes() + "," + ColorDAO.getAllColorAttributesWithName("c2")
            + "," + NationalityDAO.getAllNationalityAttributes() + ","
            + NationalityDAO.getAllNationalityAttributesWithName("n2") + ","
            + ProfessionDAO.getAllProfessionAttributes() + ","
            + PhotoDAO.getAllPhotoAttributesWithName("p2") + " FROM"
            + CurriculumVitaeDAO.getCurriculumVitaeTableName() + " JOIN"
            + UserDAO.getUserTableName() + " ON u.id = cv.user_id" + " JOIN"
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
    ComplexCurriculumVitaeDTO cv;
    try {
      ps.setInt(1, id);
      cv = createFullFillComplexCVFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
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

  private ComplexCurriculumVitaeDTO createFullFillComplexCVFromResultSet(ResultSet rs) {
    ComplexCurriculumVitaeDTO cv = domaineFactory.getComplexCurriculumVitaeDTO();

    try {
      PhotoDTO profilePicture = domaineFactory.getPhotoDTO();
      profilePicture.fullFillPhoto(rs.getInt(15), rs.getString(39), rs.getString(40), rs.getInt(41),
          rs.getInt(42), rs.getInt(43), rs.getTimestamp(44));

      AddressDTO address = domaineFactory.getAddressDTO();
      address.fullFillAddress(rs.getInt(16), rs.getString(46), rs.getString(47), rs.getString(48),
          rs.getString(49), rs.getString(50), rs.getString(51));

      ColorDTO hairColor = domaineFactory.getColorDTO();
      hairColor.fullFillColor(rs.getInt(22), rs.getString(53));

      SizeDTO hairSize = domaineFactory.getSizeDTO();
      hairSize.fullFillSize(rs.getInt(23), rs.getString(55));

      ColorDTO eye = domaineFactory.getColorDTO();
      eye.fullFillColor(rs.getInt(24), rs.getString(57));

      NationalityDTO firstNationality = domaineFactory.getNationalityDTO();
      firstNationality.fullFillNationality(rs.getInt(27), rs.getString(59));

      NationalityDTO secondNationality = domaineFactory.getNationalityDTO();
      secondNationality.fullFillNationality(rs.getInt(28), rs.getString(61));

      ComplexUserDTO user = domaineFactory.getComplexUserDTO();
      user.fullFillUser(rs.getInt(3), rs.getString(8), rs.getString(9), rs.getString(10),
          rs.getString(11), rs.getBoolean(12), rs.getTimestamp(13), rs.getString(14),
          profilePicture, address, rs.getString(17), rs.getString(18), rs.getString(19),
          rs.getString(20), rs.getString(21), hairColor, hairSize, eye, rs.getInt(25),
          rs.getInt(26), firstNationality, secondNationality, rs.getInt(29), rs.getInt(30),
          rs.getInt(31), rs.getInt(32), rs.getString(33).charAt(0), rs.getInt(34), rs.getInt(35),
          rs.getInt(36), rs.getInt(37));

      ProfessionDTO profession = domaineFactory.getProfessionDTO();
      profession.fullFillProfession(rs.getInt(4), rs.getString(63));

      PhotoDTO backgroundPicture = domaineFactory.getPhotoDTO();
      backgroundPicture.fullFillPhoto(rs.getInt(6), rs.getString(65), rs.getString(66),
          rs.getInt(67), rs.getInt(68), rs.getInt(69), rs.getTimestamp(70));

      cv.fullFillCV(rs.getInt(1), rs.getString(2), user, profession, rs.getString(5),
          backgroundPicture, new ArrayList<StrengthWithOrderDTO>(),
          new ArrayList<TrainingWithOrderDTO>(), new ArrayList<ComplexShortFilmDTO>(),
          new ArrayList<ComplexCinemaDTO>());

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the cv from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the cv from ResultSet.", e);
    }
    return cv;
  }

  private ComplexCurriculumVitaeDTO createFullFillComplexCVFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    ComplexCurriculumVitaeDTO cv = domaineFactory.getComplexCurriculumVitaeDTO();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        cv = createFullFillComplexCVFromResultSet(rs);
      }
    }
    return cv;
  }

}
