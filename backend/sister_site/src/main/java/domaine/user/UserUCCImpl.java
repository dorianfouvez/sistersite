/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.util.List;
import api.utils.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.UserDAO;

public class UserUCCImpl implements UserUCC {

  @Inject
  private UserDAO userDao;

  @Inject
  private DalServices dalservices;



  @Override
  public UserDTO findById(int id) {
    dalservices.startTransaction();
    UserDTO user = this.userDao.findById(id);
    if (user == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("User doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return user;
  }

  @Override
  public ComplexUserDTO findComplexUserById(int id) {
    dalservices.startTransaction();
    ComplexUserDTO user = this.userDao.findComplexById(id);
    if (user == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Complex user doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return user;
  }

  @Override
  public List<UserDTO> getAll() {
    dalservices.startTransaction();
    List<UserDTO> list = userDao.getAll();
    dalservices.commitTransaction();
    return list;
  }

  @Override
  public UserDTO login(String username, String password) {
    dalservices.startTransaction();
    UserDTO user = this.userDao.findByUserName(username);
    if (user == null || !((User) user).checkPassword(password)) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Username or password incorrect", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return user;
  }

}
