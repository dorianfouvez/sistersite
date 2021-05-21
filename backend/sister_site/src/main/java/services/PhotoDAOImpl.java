/**
 * @author Fouvez Dorian.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.photo.PhotoDTO;
import jakarta.inject.Inject;

public class PhotoDAOImpl implements PhotoDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public PhotoDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT id," + " name, picture, photographer" + " FROM ambre_fouvez.photos WHERE id = ?");
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
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT id," + " name, picture, photographer" + " FROM ambre_fouvez.photos WHERE name = ?");
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
  public List<PhotoDTO> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public PhotoDTO add(PhotoDTO photo) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("INSERT INTO ambre_fouvez.photos" + " VALUES(DEFAULT,?,?,?)");

    try {
      setAllPsAttributNotNull(ps, photo);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error add photo", e);
    }
    return findByName(photo.getName());
  }

  @Override
  public PhotoDTO delete(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("DELETE FROM ambre_fouvez.photos" + " WHERE id = ?");

    try {
      ps.setInt(1, id);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error delete photo", e);
    }
    return findById(id);
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
      photo.setPhotographer(rs.getInt(4));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillPhoto", e);
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
    ps.setInt(3, photo.getPhotographer());

    return ps;
  }

}
