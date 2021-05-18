/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.user.UserDTO;

public interface UserDAO {

  UserDTO findById(int id);

  UserDTO findByUserName(String username);

  List<UserDTO> getAll();

}

