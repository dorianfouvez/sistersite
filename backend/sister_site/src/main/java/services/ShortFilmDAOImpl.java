package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import api.utils.FatalException;
import domaine.DomaineFactory;
import domaine.company.CompanyDTO;
import domaine.director.DirectorDTO;
import domaine.role.RoleDTO;
import domaine.short_film.ComplexShortFilmDTO;
import jakarta.inject.Inject;

public class ShortFilmDAOImpl implements ShortFilmDAO {

  @Inject
  private DirectorDAO directorDAO;

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;

  @Override
  public List<ComplexShortFilmDTO> getAllComplexShortFilmForCV(int cvId) {
    PreparedStatement ps = this.dalBackendServices
        .getPreparedStatement("SELECT" + ShortFilmDAO.getAllShortFilmAttributes() + ","
            + ShortFilmCVDAO.getAllShortFilmCVAttributes() + "," + RoleDAO.getAllRoleAttributes()
            + "," + CompanyShortFilmDAO.getAllCompanyShortFilmAttributes() + ","
            + CompanyDAO.getAllCompanyAttributes() + " FROM" + ShortFilmDAO.getShortFilmTableName()
            + " JOIN" + ShortFilmCVDAO.getShortFilmCVTableName() + " ON sfcv.short_film = sf.id"
            + " JOIN" + RoleDAO.getRoleTableName() + " ON sf.role = r.id" + " LEFT JOIN"
            + CompanyShortFilmDAO.getCompanyShortFilmTableName() + " ON sf.id = cpsf.short_film_id"
            + " LEFT JOIN" + CompanyDAO.getCompanyTableName() + " ON cpsf.company_id = cp.id"
            + " WHERE sfcv.curriculum_vitae = ?" + " ORDER BY sf.year, sf.id");
    List<ComplexShortFilmDTO> shortFilms = new ArrayList<ComplexShortFilmDTO>();
    try {
      ps.setInt(1, cvId);
      shortFilms = createArrayOfFullFillComplexShortFilmFromPreparedStatement(ps);

    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }

    return shortFilms;
  }



  // ******************** Private's Methods ********************

  private ComplexShortFilmDTO createFullFillComplexShortFilmFromResultSet(ResultSet rs) {
    ComplexShortFilmDTO shortFilm = this.domaineFactory.getComplexShortFilmDTO();
    try {
      int id = rs.getInt(1);
      String title = rs.getString(2);
      int year = rs.getInt(4);
      int orderNumber = rs.getInt(7);

      RoleDTO role = this.domaineFactory.getRoleDTO();
      int roleId = rs.getInt(3);
      String roleName = rs.getString(9);
      role.fullFillRole(roleId, roleName);

      CompanyDTO company = this.domaineFactory.getCompanyDTO();
      int companyId = rs.getInt(11);
      String companyName = rs.getString(13);
      company.fullFillCompany(companyId, companyName);
      if (company.getId() == 0 && company.getName() == null) {
        company = null;
      }

      List<DirectorDTO> directors = this.directorDAO.getAllDirectorForShortFilm(id);

      shortFilm.fullFillComplexShortFilm(id, title, role, year, orderNumber, company, directors);

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill the short film from ResultSet.",
          e);
    } catch (NullPointerException e) {
      throw new FatalException("Error when create and fully fill the short film from ResultSet.",
          e);
    }
    return shortFilm;
  }

  private List<ComplexShortFilmDTO> createArrayOfFullFillComplexShortFilmFromPreparedStatement(
      PreparedStatement ps) throws SQLException {
    List<ComplexShortFilmDTO> shortFilms = new ArrayList<ComplexShortFilmDTO>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        shortFilms.add(createFullFillComplexShortFilmFromResultSet(rs));
      }
    }
    return shortFilms;
  }

}
