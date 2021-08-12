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
import domaine.address.AddressDTO;
import domaine.color.ColorDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.size.SizeDTO;
import domaine.user.ComplexUserDTO;
import domaine.user.UserDTO;
import jakarta.inject.Inject;

public class UserDAOImpl implements UserDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public UserDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + UserDAO.getAllUserAttributes() + " FROM" + UserDAO.getUserTableName() + " WHERE id = ?");
    UserDTO user;
    try {
      ps.setInt(1, id);
      user = createFullFillUserFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    return user;
  }

  @Override
  public UserDTO findByUserName(String username) {
    PreparedStatement ps =
        this.dalBackendServices.getPreparedStatement("SELECT" + UserDAO.getAllUserAttributes()
            + " FROM" + UserDAO.getUserTableName() + " WHERE u.username = ?");
    UserDTO user;
    try {
      ps.setString(1, username);
      user = createFullFillUserFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    if (user.getUserName() == null) {
      return null;
    }
    return user;
  }

  @Override
  public ComplexUserDTO findComplexById(int id) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT" + UserDAO.getAllUserAttributes() + "," + PhotoDAO.getAllPhotoAttributes() + ","
            + AddressDAO.getAllAddressAttributes() + "," + ColorDAO.getAllColorAttributes() + ","
            + SizeDAO.getAllSizeAttributes() + "," + ColorDAO.getAllColorAttributesWithName("c2")
            + "," + NationalityDAO.getAllNationalityAttributes() + ","
            + NationalityDAO.getAllNationalityAttributesWithName("n2") + " FROM"
            + UserDAO.getUserTableName() + PhotoDAO.getUserPhotoJoint()
            + AddressDAO.getUserAddressJoint() + ColorDAO.getUserColorJoint("hair_color")
            + SizeDAO.getUserSizeJoint() + ColorDAO.getUserColorJoint("eye", "c2")
            + NationalityDAO.getUserNationalityJoint("first_nationality")
            + NationalityDAO.getUserNationalityJointWithName("second_nationality", "n2") + " WHERE "
            + UserDAO.getUserAbbreviation() + ".id = ?");
    ComplexUserDTO user;
    try {
      ps.setInt(1, id);
      user = createFullFillComplexUserFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    return user;
  }

  @Override
  public List<UserDTO> getAll() {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT "
        + UserDAO.getAllUserAttributes() + " FROM" + UserDAO.getUserTableName() + " ORDER BY u.id");

    List<UserDTO> list = new ArrayList<UserDTO>();

    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        UserDTO user = createFullFillUserFromResultSet(rs);
        list.add(user);
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("error fullFillUsers", e);
    }

    return list;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a user. Or throw a FatalException if a SQLException is caught.
   * 
   * @param rs ResultSet who contains the user.
   * @return the UserDTO in the ResultSet.
   */
  private UserDTO createFullFillUserFromResultSet(ResultSet rs) {
    UserDTO user = domaineFactory.getUserDTO();
    try {
      user.setID(rs.getInt(1));
      user.setUserName(rs.getString(2));
      user.setLastName(rs.getString(3));
      user.setFirstName(rs.getString(4));
      user.setEmail(rs.getString(5));
      user.setBoss(rs.getBoolean(6));
      user.setRegistrationDate(rs.getTimestamp(7));
      user.setPassword(rs.getString(8));
      user.setProfilePicture(rs.getInt(9));
      user.setAddress(rs.getInt(10));
      user.setPhoneNumber(rs.getString(11));
      user.setFacebook(rs.getString(12));
      user.setInstagram(rs.getString(13));
      user.setTwitter(rs.getString(14));
      user.setYoutube(rs.getString(15));
      user.setHairColor(rs.getInt(16));
      user.setHairSize(rs.getInt(17));
      user.setEye(rs.getInt(18));
      user.setHeight(rs.getInt(19));
      user.setWeight(rs.getInt(20));
      user.setFirstNationality(rs.getInt(21));
      user.setSecondNationality(rs.getInt(22));
      user.setShoeSize(rs.getInt(23));
      user.setJacketSize(rs.getInt(24));
      user.setPantSize(rs.getInt(25));
      user.setChest(rs.getInt(26));
      String bracup = rs.getString(27);
      if (bracup != null) {
        user.setBraCup(bracup.charAt(0));
      }
      user.setWaistSize(rs.getInt(28));
      user.setHipSize(rs.getInt(29));
      user.setNeckSize(rs.getInt(30));
      user.setHeadSize(rs.getInt(31));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill a users from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill a users from ResultSet.", e);
    }
    return user;
  }

  /**
   * Create and fully fill a user with the PreparedStatement from de db. Or throws SQLException.
   * 
   * @param ps the PreparedStatement already Set.
   * @return the user filled from the PreparedStatement.
   * @throws SQLException if problems.
   */
  private UserDTO createFullFillUserFromPreparedStatement(PreparedStatement ps)
      throws SQLException {
    UserDTO user = domaineFactory.getUserDTO();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        user = createFullFillUserFromResultSet(rs);
      }
    }
    return user;
  }

  private ComplexUserDTO createFullFillComplexUserFromResultSet(ResultSet rs) {
    int userIndex = 1;
    int profilePictureIndex = 32;
    int addressIndex = 39;
    int hairColorIndex = 46;
    int hairSizeIndex = 48;
    int eyeIndex = 50;
    int firstNationalityIndex = 52;
    int secondNationalityIndex = 54;

    ComplexUserDTO user = this.domaineFactory.getComplexUserDTO();
    PhotoDTO profilePicture = this.domaineFactory.getPhotoDTO();
    AddressDTO address = this.domaineFactory.getAddressDTO();
    ColorDTO hairColor = this.domaineFactory.getColorDTO();
    SizeDTO hairSize = this.domaineFactory.getSizeDTO();
    ColorDTO eye = this.domaineFactory.getColorDTO();
    NationalityDTO firstNationality = this.domaineFactory.getNationalityDTO();
    NationalityDTO secondNationality = this.domaineFactory.getNationalityDTO();
    try {
      user.setID(rs.getInt(userIndex++));
      user.setUserName(rs.getString(userIndex++));
      user.setLastName(rs.getString(userIndex++));
      user.setFirstName(rs.getString(userIndex++));
      user.setEmail(rs.getString(userIndex++));
      user.setBoss(rs.getBoolean(userIndex++));
      user.setRegistrationDate(rs.getTimestamp(userIndex++));
      user.setPassword(rs.getString(userIndex++));
      profilePicture.setId(rs.getInt(userIndex++));
      address.setId(rs.getInt(userIndex++));
      user.setPhoneNumber(rs.getString(userIndex++));
      user.setFacebook(rs.getString(userIndex++));
      user.setInstagram(rs.getString(userIndex++));
      user.setTwitter(rs.getString(userIndex++));
      user.setYoutube(rs.getString(userIndex++));
      hairColor.setId(rs.getInt(userIndex++));
      hairSize.setId(rs.getInt(userIndex++));
      eye.setId(rs.getInt(userIndex++));
      user.setHeight(rs.getInt(userIndex++));
      user.setWeight(rs.getInt(userIndex++));
      firstNationality.setId(rs.getInt(userIndex++));
      secondNationality.setId(rs.getInt(userIndex++));
      user.setShoeSize(rs.getInt(userIndex++));
      user.setJacketSize(rs.getInt(userIndex++));
      user.setPantSize(rs.getInt(userIndex++));
      user.setChest(rs.getInt(userIndex++));
      String bracup = rs.getString(userIndex++);
      if (bracup != null) {
        user.setBraCup(bracup.charAt(0));
      }
      user.setWaistSize(rs.getInt(userIndex++));
      user.setHipSize(rs.getInt(userIndex++));
      user.setNeckSize(rs.getInt(userIndex++));
      user.setHeadSize(rs.getInt(userIndex++));

      profilePictureIndex++;
      profilePicture.setName(rs.getString(profilePictureIndex++));
      profilePicture.setPicture(rs.getString(profilePictureIndex++));
      profilePicture.setMakeupArtist(rs.getInt(profilePictureIndex++));
      profilePicture.setPhotographer(rs.getInt(profilePictureIndex++));
      profilePicture.setSharer(rs.getInt(profilePictureIndex++));
      profilePicture.setDate(rs.getTimestamp(profilePictureIndex++));
      user.setProfilePicture(profilePicture);

      addressIndex++;
      address.setCountry(rs.getString(addressIndex++));
      address.setCommune(rs.getString(addressIndex++));
      address.setPostcode(rs.getString(addressIndex++));
      address.setStreet(rs.getString(addressIndex++));
      address.setBuildingNumber(rs.getString(addressIndex++));
      address.setUnitNumber(rs.getString(addressIndex++));
      user.setAddress(address);

      hairColorIndex++;
      hairColor.setColor(rs.getString(hairColorIndex++));
      user.setHairColor(hairColor);

      hairSizeIndex++;
      hairSize.setSize(rs.getString(hairSizeIndex++));
      user.setHairSize(hairSize);

      eyeIndex++;
      eye.setColor(rs.getString(eyeIndex++));
      user.setEye(eye);

      firstNationalityIndex++;
      firstNationality.setNationality(rs.getString(firstNationalityIndex++));
      user.setFirstNationality(firstNationality);

      secondNationalityIndex++;
      secondNationality.setNationality(rs.getString(secondNationalityIndex++));
      user.setSecondNationality(secondNationality);

    } catch (SQLException e) {
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill a complex user from ResultSet.",
          e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill a complex user from ResultSet.",
          e);
    }
    return user;
  }

  private ComplexUserDTO createFullFillComplexUserFromPreparedStatement(PreparedStatement ps)
      throws SQLException {
    ComplexUserDTO user = this.domaineFactory.getComplexUserDTO();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        user = createFullFillComplexUserFromResultSet(rs);
      }
    }
    return user;
  }

}
