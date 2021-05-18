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
import domaine.user.UserDTO;
import jakarta.inject.Inject;

public class UserDAOImpl implements UserDAO {

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private DalBackendServices dalBackendServices;



  @Override
  public UserDTO findById(int id) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT id, username, last_name, first_name, email, is_boss, registration_date, password"
            + " FROM ambre_fouvez.users WHERE id = ?");
    UserDTO user;
    try {
      ps.setInt(1, id);
      user = createFullFillUserFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    return user;
  }

  @Override
  public UserDTO findByUserName(String username) {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT id, username, last_name, first_name, email, is_boss, registration_date, password"
            + " FROM ambre_fouvez.users WHERE username = ?");
    UserDTO user;
    try {
      ps.setString(1, username);
      user = createFullFillUserFromPreparedStatement(ps);
    } catch (SQLException e) {
      e.printStackTrace();
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException(e.getMessage(), e);
    }
    if (user.getUserName() == null) {
      return null;
    }
    return user;
  }

  @Override
  public List<UserDTO> getAll() {
    PreparedStatement ps = this.dalBackendServices.getPreparedStatement(
        "SELECT id, username, last_name, first_name, email, is_boss, registration_date, password"
            + " FROM ambre_fouvez.users" + " ORDER BY id");

    List<UserDTO> list = new ArrayList<UserDTO>();

    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        UserDTO user = createFullFillUsersFromResultSet(rs);
        list.add(user);
      }
    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("error fullFillUsers", e);
    }

    return list;
  }



  // ******************** Private's Methods ********************

  /**
   * Create and fully fill a user. Or throw a FatalException if a SQLException is caught.
   * 
   * @param rs ResultSet who contains the user.
   * @return the UserDTO in the ResultSet.
   */
  private UserDTO createFullFillUsersFromResultSet(ResultSet rs) {
    UserDTO user = domaineFactory.getUserDTO();
    try {
      user.setID(rs.getInt(1));
      user.setUserName(rs.getString(2));
      user.setLastName(rs.getString(3));
      user.setFirstName(rs.getString(4));
      user.setEmail(rs.getString(5));
      user.setBoss(rs.getBoolean(6));
      user.setRegistrationDate(rs.getTimestamp(7));
      user.setPassword(rs.getString(8));

    } catch (SQLException e) {
      ((DalServices) dalBackendServices).rollbackTransaction();
      throw new FatalException("Error when create and fully fill a users from ResultSet.", e);
    }
    return user;
  }

  /**
   * Create and fully fill a user with the PreparedStatement from de db. Or throws SQLException.
   * 
   * @param ps the PreparedStatement already Set.
   * @return the user filled from the PreparedStatement.
   * @throws SQLException if problems.
   */
  private UserDTO createFullFillUserFromPreparedStatement(PreparedStatement ps)
      throws SQLException {
    UserDTO user = domaineFactory.getUserDTO();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        user = createFullFillUsersFromResultSet(rs);
      }
    }
    return user;
  }

}
