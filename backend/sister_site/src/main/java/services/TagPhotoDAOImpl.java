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
import domaine.tag_photo.TagPhotoDTO;
import jakarta.inject.Inject;

public class TagPhotoDAOImpl implements TagPhotoDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public TagPhotoDTO add(TagPhotoDTO tagPhoto) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "INSERT INTO" + TagPhotoDAO.getTagPhotoTableNameWithoutAbbreviation() + " VALUES(?,?)");

    try {
      ps = this.setAllPsAttributNotNull(ps, tagPhoto);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException("Error add tag photo", e);
    }
    return this.findById(tagPhoto.getPhotoId(), tagPhoto.getTagId());
  }

  @Override
  public List<TagPhotoDTO> deleteAllFor(int photoId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("DELETE FROM"
        + TagPhotoDAO.getTagPhotoTableNameWithoutAbbreviation() + " WHERE photo_id = ?");

    try {
      ps.setInt(1, photoId);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error delete tag photo", e);
    }
    return findAllFor(photoId);
  }

  @Override
  public List<TagPhotoDTO> findAllFor(int photoId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + TagPhotoDAO.getAllTagPhotoAttributes() + " FROM" + TagPhotoDAO.getTagPhotoTableName()
        + " WHERE " + TagPhotoDAO.getTagPhotoAbbreviation() + ".photo_id = ?");
    List<TagPhotoDTO> photos = new ArrayList<>();
    try {
      ps.setInt(1, photoId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          TagPhotoDTO photo = this.createFullFillTagPhoto(rs);
          photos.add(photo);
        }
      }
    } catch (SQLException e) {
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException("Error find all tag photo by photo id", e);
    }
    return photos;
  }

  @Override
  public TagPhotoDTO findById(int photoId, int tagId) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + TagPhotoDAO.getAllTagPhotoAttributes() + " FROM"
            + TagPhotoDAO.getTagPhotoTableName() + " WHERE " + TagPhotoDAO.getTagPhotoAbbreviation()
            + ".photo_id = ? AND " + TagPhotoDAO.getTagPhotoAbbreviation() + ".tag_id = ?");
    TagPhotoDTO photo = null;
    try {
      ps.setInt(1, photoId);
      ps.setInt(2, tagId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          photo = this.createFullFillTagPhoto(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException("Error find tag photo by id", e);
    }
    return photo;
  }

  @Override
  public TagPhotoDTO update(TagPhotoDTO tagPhoto, int lastTagId) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("UPDATE" + TagPhotoDAO.getTagPhotoTableNameWithoutAbbreviation()
            + " SET photo_id = ?, tag_id = ? WHERE photo_id = ? AND tag_id = ?");
    try {
      ps = this.setAllPsAttributNotNull(ps, tagPhoto);
      ps.setInt(3, tagPhoto.getPhotoId());
      ps.setInt(4, lastTagId);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) this.dalBackendServices).rollbackTransaction();
      throw new FatalException("Error update tag photo.", e);
    }
    return this.findById(tagPhoto.getPhotoId(), tagPhoto.getTagId());
  }



  // ******************** Private's Methods ********************

  private TagPhotoDTO createFullFillTagPhoto(ResultSet rs) {
    TagPhotoDTO tagPhoto = domaineFactory.getTagPhotoDTO();

    try {
      tagPhoto.setPhotoId(rs.getInt(1));
      tagPhoto.setTagId(rs.getInt(2));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error full fill tag photo", e);
    }

    return tagPhoto;
  }

  private PreparedStatement setAllPsAttributNotNull(PreparedStatement ps, TagPhotoDTO tagPhoto)
      throws SQLException {
    ps.setInt(1, tagPhoto.getPhotoId());
    ps.setInt(2, tagPhoto.getTagId());

    return ps;
  }

}
