/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.util.List;

public interface UserUCC {

  UserDTO login(String username, String password);

  UserDTO findById(int id);

  List<UserDTO> getAll();

}

