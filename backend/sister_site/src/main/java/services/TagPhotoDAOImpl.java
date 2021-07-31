/**
 * @author Fouvez Dorian.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
          photo = createFullFillTagPhoto(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findById", e);
    }
    return photo;
  }

  @Override
  public TagPhotoDTO add(TagPhotoDTO tagPhoto) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("INSERT INTO" + TagPhotoDAO.getTagPhotoTableName() + " VALUES(?,?)");

    try {
      ps = setAllPsAttributNotNull(ps, tagPhoto);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error add tag photo", e);
    }
    return findById(tagPhoto.getPhotoId(), tagPhoto.getTagId());
  }



  // ******************** Private's Methods ********************

  private TagPhotoDTO createFullFillTagPhoto(ResultSet rs) {
    TagPhotoDTO tagPhoto = domaineFactory.getTagPhotoDTO();

    try {
      tagPhoto.setPhotoId(rs.getInt(1));
      tagPhoto.setTagId(rs.getInt(2));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillTagPhoto", e);
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
