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
import domaine.tag.TagDTO;
import jakarta.inject.Inject;

public class TagDAOImpl implements TagDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public TagDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement("SELECT"
        + TagDAO.getAllTagAttributes() + " FROM" + TagDAO.getTagTableName() + " WHERE id = ?");
    TagDTO tag = null;
    try {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          tag = createFullFillTag(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findById", e);
    }
    return tag;
  }

  @Override
  public List<TagDTO> getAll() {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + TagDAO.getAllTagAttributes() + " FROM"
            + TagDAO.getTagTableName() + " ORDER BY " + TagDAO.getTagAbbreviation() + ".id");
    List<TagDTO> tags = new ArrayList<TagDTO>();
    try {
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          TagDTO tag = createFullFillTag(rs);
          tags.add(tag);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getAll tags", e);
    }
    return tags;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a tag from the ResultSet.
   * 
   * @param rs ResultSet who contains the tag.
   * @return the created and fully filled tag.
   */
  private TagDTO createFullFillTag(ResultSet rs) {
    TagDTO tag = domaineFactory.getTagDTO();

    try {
      tag.setId(rs.getInt(1));
      tag.setLabel(rs.getString(2));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillTag", e);
    }

    return tag;
  }

}
