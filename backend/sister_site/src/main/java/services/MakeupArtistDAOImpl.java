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
import domaine.makeup_artist.MakeupArtistDTO;
import jakarta.inject.Inject;

public class MakeupArtistDAOImpl implements MakeupArtistDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public MakeupArtistDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + MakeupArtistDAO.getAllMakeupArtistAttributes() + " FROM"
            + MakeupArtistDAO.getMakeupArtistTableName() + " WHERE id = ?");
    MakeupArtistDTO makeupArtist = null;
    try {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          makeupArtist = createFullFillMakeupArtist(rs);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error findById", e);
    }
    return makeupArtist;
  }

  @Override
  public List<MakeupArtistDTO> getAll() {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + MakeupArtistDAO.getAllMakeupArtistAttributes() + " FROM"
            + MakeupArtistDAO.getMakeupArtistTableName() + " ORDER BY "
            + MakeupArtistDAO.getMakeupArtistAbbreviation() + ".id");
    List<MakeupArtistDTO> makeupArtists = new ArrayList<MakeupArtistDTO>();
    try {
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          MakeupArtistDTO makeupArtist = createFullFillMakeupArtist(rs);
          makeupArtists.add(makeupArtist);
        }
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error getAll makeupArtists", e);
    }
    return makeupArtists;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a make-up Artist from the ResultSet.
   * 
   * @param rs ResultSet who contains the make-up Artist.
   * @return the created and fully filled make-up Artist.
   */
  private MakeupArtistDTO createFullFillMakeupArtist(ResultSet rs) {
    MakeupArtistDTO makeupArtist = domaineFactory.getMakeupArtistDTO();

    try {
      makeupArtist.setId(rs.getInt(1));
      makeupArtist.setName(rs.getString(2));
      makeupArtist.setInstagram(rs.getString(3));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error fullFillMakeupArtist", e);
    }

    return makeupArtist;
  }

}
