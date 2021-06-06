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



  // ******************** Static's Methods ********************

  static String getAllUserAttributes() {
    return " u.id, u.username, u.last_name, u.first_name, u.email, u.is_boss, u.registration_date, u.password,"
        + " u.profile_picture, u.address, u.phone_number, u.facebook, u.instagram, u.twitter, u.youtube,"
        + " u.hair_color, u.hair_size, u.eye, u.height, u.weight, u.first_nationality, u.second_nationality,"
        + " u.shoe_size, u.jacket_size, u.pant_size, u.chest, u.bra_cup, u.waist_size, u.hip_size, u.neck_size,"
        + " u.head_size";
  }

  static String getUserTableName() {
    return " ambre_fouvez.users u";
  }

}

