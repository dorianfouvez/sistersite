/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.util.List;

public interface UserUCC {

  UserDTO findById(int id);

  ComplexUserDTO findComplexUserById(int id);

  List<UserDTO> getAll();

  UserDTO login(String username, String password);

}

