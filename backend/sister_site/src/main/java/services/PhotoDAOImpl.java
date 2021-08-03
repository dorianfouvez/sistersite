/**
 * @author Fouvez Dorian.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.photo.ComplexPhotoDTO;
import domaine.photo.PhotoDTO;
import domaine.photographer.PhotographerDTO;
import domaine.user.UserDTO;
import jakarta.inject.Inject;

public class PhotoDAOImpl implements PhotoDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public PhotoDTO findById(int id) {
    PreparedStatement ps =
        this.dalBackendServices.getPreparedStatement("SELECT" + PhotoDAO.getAllPhotoAttributes()
            + " FROM" + PhotoDAO.getPhotoTableName() + " WHERE id = ?");
    PhotoDTO photo = null;
    try {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          photo = createFullFillPhoto(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findById", e);
    }
    return photo;
  }

  @Override
  public PhotoDTO findByName(String name) {
    PreparedStatement ps =
        this.dalBackendServices.getPreparedStatement("SELECT" + PhotoDAO.getAllPhotoAttributes()
            + " FROM" + PhotoDAO.getPhotoTableName() + " WHERE name = ?");
    PhotoDTO photo = domaineFactory.getPhotoDTO();
    try {
      ps.setString(1, name);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          photo = createFullFillPhoto(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findByName", e);
    }
    if (photo.getName() == null) {
      return null;
    }
    return photo;
  }

  @Override
  public PhotoDTO add(PhotoDTO photo) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("INSERT INTO"
        + PhotoDAO.getPhotoTableNameWithoutAbbreviation() + " VALUES(DEFAULT,?,?,?,?,?,?)");

    try {
      setAllPsAttributNotNull(ps, photo);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error add photo", e);
    }
    // System.out.println(photo.getName());
    return findByName(photo.getName());
  }

  @Override
  public PhotoDTO delete(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("DELETE FROM" + PhotoDAO.getPhotoTableName() + " WHERE id = ?");

    try {
      ps.setInt(1, id);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error delete photo", e);
    }
    return findById(id);
  }

  @Override
  public List<PhotoDTO> getBook(int tagId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT" + PhotoDAO.getAllPhotoAttributes() + " FROM" + PhotoDAO.getPhotoTableName() + ","
            + TagPhotoDAO.getTagPhotoTableName() + " WHERE " + TagPhotoDAO.getTagPhotoAbbreviation()
            + ".photo_id = " + PhotoDAO.getPhotoAbbreviation() + ".id AND "
            + TagPhotoDAO.getTagPhotoAbbreviation() + ".tag_id = ?");
    List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
    try {
      ps.setInt(1, tagId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          PhotoDTO photo = createFullFillPhoto(rs);
          photos.add(photo);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getBook", e);
    }
    return photos;
  }

  @Override
  public List<PhotoDTO> getAll(int userId) {
    PreparedStatement ps =
        this.dalBackendServices.getPreparedStatement("SELECT" + PhotoDAO.getAllPhotoAttributes()
            + " FROM" + PhotoDAO.getPhotoTableName() + " WHERE " + PhotoDAO.getPhotoAbbreviation()
            + ".sharer = -1 OR " + PhotoDAO.getPhotoAbbreviation() + ".sharer = ?");
    List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
    try {
      ps.setInt(1, userId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          PhotoDTO photo = createFullFillPhoto(rs);
          photos.add(photo);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getAll", e);
    }
    return photos;
  }

  @Override
  public List<ComplexPhotoDTO> getAllFullInfosPhoto(int userId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + PhotoDAO.getAllPhotoAttributes() + "," + MakeupArtistDAO.getAllMakeupArtistAttributes()
        + "," + PhotographerDAO.getAllPhotographerAttributes() + ","
        + UserDAO.getAllUserAttributes() + " FROM" + PhotoDAO.getPhotoTableName() + " JOIN"
        + MakeupArtistDAO.getMakeupArtistTableName() + " ON "
        + MakeupArtistDAO.getMakeupArtistAbbreviation() + ".id = " + PhotoDAO.getPhotoAbbreviation()
        + ".makeup_artist JOIN" + PhotographerDAO.getPhotographerTableName() + " ON "
        + PhotographerDAO.getPhotographerAbbreviation() + ".id = " + PhotoDAO.getPhotoAbbreviation()
        + ".photographer JOIN" + UserDAO.getUserTableName() + " ON " + UserDAO.getUserAbbreviation()
        + ".id = " + PhotoDAO.getPhotoAbbreviation() + ".sharer WHERE "
        + PhotoDAO.getPhotoAbbreviation() + ".sharer = -1 OR " + PhotoDAO.getPhotoAbbreviation()
        + ".sharer = ? ORDER BY " + PhotoDAO.getPhotoAbbreviation() + ".id");
    List<ComplexPhotoDTO> photos = new ArrayList<>();
    try {
      ps.setInt(1, userId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          ComplexPhotoDTO photo = createFullFillComplexPhoto(rs);
          photos.add(photo);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getAllFullInfosPhoto", e);
    }
    return photos;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a photo from the ResultSet.
   * 
   * @param rs ResultSet who contains the photo.
   * @return the created and fully filled photo.
   */
  private PhotoDTO createFullFillPhoto(ResultSet rs) {
    PhotoDTO photo = domaineFactory.getPhotoDTO();

    try {
      photo.setId(rs.getInt(1));
      photo.setName(rs.getString(2));
      photo.setPicture(rs.getString(3));
      photo.setMakeupArtist(rs.getInt(4));
      photo.setPhotographer(rs.getInt(5));
      photo.setSharer(rs.getInt(6));
      photo.setDate(rs.getTimestamp(7));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillPhoto", e);
    }

    return photo;
  }

  private ComplexPhotoDTO createFullFillComplexPhoto(ResultSet rs) {
    ComplexPhotoDTO photo = domaineFactory.getComplexPhotoDTO();

    try {
      photo.setId(rs.getInt(1));
      photo.setName(rs.getString(2));
      photo.setPicture(rs.getString(3));
      Timestamp date = rs.getTimestamp(7);
      if (date != null) {
        photo.setDate(date);
      }

      MakeupArtistDTO makeupArtist = this.domaineFactory.getMakeupArtistDTO();
      makeupArtist.setId(rs.getInt(4));
      makeupArtist.setName(rs.getString(9));
      String instagramMakeupArtist = rs.getString(10);
      if (instagramMakeupArtist != null && !instagramMakeupArtist.equals("")) {
        makeupArtist.setInstagram(instagramMakeupArtist);
      }
      photo.setMakeupArtist(makeupArtist);

      PhotographerDTO photographer = this.domaineFactory.getPhotographerDTO();
      photographer.setId(rs.getInt(5));
      photographer.setName(rs.getString(12));
      String instagramPhotographer = rs.getString(13);
      if (instagramPhotographer != null && !instagramPhotographer.equals("")) {
        photographer.setInstagram(instagramPhotographer);
      }
      photo.setPhotographer(photographer);

      UserDTO sharer = this.domaineFactory.getUserDTO();
      sharer.setID(rs.getInt(6));
      sharer.setUserName(rs.getString(15));
      sharer.setLastName(rs.getString(16));
      sharer.setFirstName(rs.getString(17));
      sharer.setEmail(rs.getString(18));
      sharer.setBoss(rs.getBoolean(19));
      sharer.setRegistrationDate(rs.getTimestamp(20));
      sharer.setPassword(rs.getString(21));
      sharer.setProfilePicture(rs.getInt(22));
      sharer.setAddress(rs.getInt(23));
      sharer.setPhoneNumber(rs.getString(24));
      // TODO check for null
      sharer.setFacebook(rs.getString(25));
      String instagram = rs.getString(26);
      if (instagram != null && !instagram.equals("")) {
        sharer.setInstagram(instagram);
      }
      // TODO check for null
      sharer.setTwitter(rs.getString(27));
      // TODO check for null
      sharer.setYoutube(rs.getString(28));
      sharer.setHairColor(rs.getInt(29));
      sharer.setHairSize(rs.getInt(30));
      sharer.setEye(rs.getInt(31));
      sharer.setHeight(rs.getDouble(32));
      sharer.setWeight(rs.getInt(33));
      sharer.setFirstNationality(rs.getInt(34));
      sharer.setSecondNationality(rs.getInt(35));
      sharer.setShoeSize(rs.getInt(36));
      sharer.setJacketSize(rs.getInt(37));
      sharer.setPantSize(rs.getInt(38));
      sharer.setChest(rs.getDouble(39));
      sharer.setBraCup(rs.getString(40).charAt(0));
      sharer.setWaistSize(rs.getDouble(41));
      sharer.setHipSize(rs.getDouble(42));
      sharer.setNeckSize(rs.getDouble(43));
      sharer.setHeadSize(rs.getDouble(44));
      photo.setSharer(sharer);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillComplexPhoto", e);
    }

    return photo;
  }

  /**
   * Set all the value not null for a photo in the PreparedStatement without the id.
   * 
   * @param ps PreparedStatement who contains a INSERT of a photo.
   * @param photo the photo to insert in the db.
   * @return PreparedStatement fully set with the photo'sattributes.
   * @throws SQLException if a error comes when set the PreparedStatement.
   */
  private PreparedStatement setAllPsAttributNotNull(PreparedStatement ps, PhotoDTO photo)
      throws SQLException {
    ps.setString(1, photo.getName());
    ps.setString(2, photo.getPicture());
    ps.setInt(3, photo.getMakeupArtist());
    ps.setInt(4, photo.getPhotographer());
    ps.setInt(5, photo.getSharer());
    ps.setTimestamp(6, photo.getDate()); // TODO Need to Check if when it's null it's working.

    return ps;
  }

}
