package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.cinema.ComplexCinemaDTO;
import domaine.director.DirectorDTO;
import domaine.distinction.DistinctionDTO;
import domaine.role.RoleDTO;
import jakarta.inject.Inject;

public class CinemaDAOImpl implements CinemaDAO {

  @Inject
  private DirectorDAO directorDAO;

  @Inject
  private DistinctionDAO distinctionDAO;

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<ComplexCinemaDTO> getAllComplexCinemaForCV(int cvId) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT" + CinemaDAO.getAllCinemaAttributes() + "," + CinemaCVDAO.getAllCinemaCVAttributes()
            + "," + RoleDAO.getAllRoleAttributes() + " FROM" + CinemaDAO.getCinemaTableName()
            + " JOIN" + CinemaCVDAO.getCinemaCVTableName() + " ON cicv.cinema = ci.id" + " JOIN"
            + RoleDAO.getRoleTableName() + " ON ci.role = r.id" + " WHERE cicv.curriculum_vitae = ?"
            + " ORDER BY ci.year, ci.id");
    List<ComplexCinemaDTO> shortFilms = new ArrayList<>();
    try {
      ps.setInt(1, cvId);
      shortFilms = createArrayOfFullFillComplexCinemaFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return shortFilms;
  }



  // ******************** Private's Methods ********************

  private ComplexCinemaDTO createFullFillComplexCinemaFromResultSet(ResultSet rs) {
    ComplexCinemaDTO cinema = this.domaineFactory.getComplexCinemaDTO();
    try {
      int id = rs.getInt(1);
      String title = rs.getString(2);
      int year = rs.getInt(4);
      int orderNumber = rs.getInt(7);

      RoleDTO role = this.domaineFactory.getRoleDTO();
      int roleId = rs.getInt(3);
      String roleName = rs.getString(9);
      role.fullFillRole(roleId, roleName);

      List<DistinctionDTO> distinctions = this.distinctionDAO.getAllDistinctionForCinema(id);

      List<DirectorDTO> directors = this.directorDAO.getAllDirectorForCinema(id);

      cinema.fullFillComplexCinema(id, title, role, year, orderNumber, distinctions, directors);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the cinema from ResultSet.", e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the cinema from ResultSet.", e);
    }
    return cinema;
  }

  private List<ComplexCinemaDTO> createArrayOfFullFillComplexCinemaFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<ComplexCinemaDTO> cinemas = new ArrayList<>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        cinemas.add(createFullFillComplexCinemaFromResultSet(rs));
      }
    }
    return cinemas;
  }

}
