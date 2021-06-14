package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.director.DirectorDTO;
import jakarta.inject.Inject;

public class DirectorDAOImpl implements DirectorDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<DirectorDTO> getAllDirectorForCinema(int cinemaId) {
    List<DirectorDTO> directors =
        getAllDirectorForJointure(cinemaId, DirectorCinemaDAO.getAllDirectorCinemaAttributes(),
            DirectorCinemaDAO.getDirectorCinemaTableName() + " ON drci.director_id = dr.id",
            "drci.cinema_id");

    return directors;
  }

  @Override
  public List<DirectorDTO> getAllDirectorForShortFilm(int shortFilmId) {
    List<DirectorDTO> directors = getAllDirectorForJointure(shortFilmId,
        DirectorShortFilmDAO.getAllDirectorShortFilmAttributes(),
        DirectorShortFilmDAO.getDirectorShortFilmTableName() + " ON drsf.director_id = dr.id",
        "drsf.short_film_id");

    return directors;
  }



  // ******************** Private's Methods ********************

  private List<DirectorDTO> getAllDirectorForJointure(int jointureId, String jointureAttributes,
      String jointureTable, String jointureWhere) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + DirectorDAO.getAllDirectorAttributes() + ","
            + jointureAttributes + " FROM" + DirectorDAO.getDirectorTableName() + " JOIN"
            + jointureTable + " WHERE " + jointureWhere + " = ?");
    List<DirectorDTO> directors = new ArrayList<>();
    try {
      ps.setInt(1, jointureId);
      directors = createArrayOfFullFillDirectorsFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return directors;
  }

  private DirectorDTO createFullFillDirectorFromResultSet(ResultSet rs) {
    DirectorDTO director = this.domaineFactory.getDirectorDTO();
    try {
      int id = rs.getInt(1);
      String name = rs.getString(2);

      director.fullFillDirector(id, name);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the director from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the director from ResultSet.", e);
    }
    return director;
  }

  private List<DirectorDTO> createArrayOfFullFillDirectorsFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<DirectorDTO> directors = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        directors.add(createFullFillDirectorFromResultSet(rs));
      }
    }
    return directors;
  }

}
