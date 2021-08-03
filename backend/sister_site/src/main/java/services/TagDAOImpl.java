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
  public TagDTO findByLabel(String label) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + TagDAO.getAllTagAttributes() + " FROM"
            + TagDAO.getTagTableName() + " WHERE " + TagDAO.getTagAbbreviation() + ".label = ?");
    TagDTO tag = null;
    try {
      ps.setString(1, label);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          tag = createFullFillTag(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findByLabel", e);
    }
    return tag;
  }

  @Override
  public List<TagDTO> getAllSortedByLabel() {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + TagDAO.getAllTagAttributes() + " FROM"
            + TagDAO.getTagTableName() + " ORDER BY " + TagDAO.getTagAbbreviation() + ".label");
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

  @Override
  public TagDTO add(TagDTO tag) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "INSERT INTO" + TagDAO.getTagTableNameWithoutAbbreviation() + " VALUES(DEFAULT,?)");

    try {
      ps = setAllPsAttributWithoutId(ps, tag);

      ps.executeUpdate();
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error add tag", e);
    }
    return findByLabel(tag.getLabel());
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

  private PreparedStatement setAllPsAttributWithoutId(PreparedStatement ps, TagDTO tag)
      throws SQLException {
    ps.setString(1, tag.getLabel());

    return ps;
  }

}
