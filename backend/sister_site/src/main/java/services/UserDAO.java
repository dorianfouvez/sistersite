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
    return " " + getUserAbbreviation() + ".id, " + getUserAbbreviation() + ".username, "
        + getUserAbbreviation() + ".last_name, " + getUserAbbreviation() + ".first_name, "
        + getUserAbbreviation() + ".email, " + getUserAbbreviation() + ".is_boss, "
        + getUserAbbreviation() + ".registration_date, " + getUserAbbreviation() + ".password, "
        + getUserAbbreviation() + ".profile_picture, " + getUserAbbreviation() + ".address, "
        + getUserAbbreviation() + ".phone_number, " + getUserAbbreviation() + ".facebook, "
        + getUserAbbreviation() + ".instagram, " + getUserAbbreviation() + ".twitter, "
        + getUserAbbreviation() + ".youtube, " + getUserAbbreviation() + ".hair_color, "
        + getUserAbbreviation() + ".hair_size, " + getUserAbbreviation() + ".eye, "
        + getUserAbbreviation() + ".height, " + getUserAbbreviation() + ".weight, "
        + getUserAbbreviation() + ".first_nationality, " + getUserAbbreviation()
        + ".second_nationality, " + getUserAbbreviation() + ".shoe_size, " + getUserAbbreviation()
        + ".jacket_size, " + getUserAbbreviation() + ".pant_size, " + getUserAbbreviation()
        + ".chest, " + getUserAbbreviation() + ".bra_cup, " + getUserAbbreviation()
        + ".waist_size, " + getUserAbbreviation() + ".hip_size, " + getUserAbbreviation()
        + ".neck_size, " + getUserAbbreviation() + ".head_size";
  }

  static String getUserAbbreviation() {
    return "u";
  }

  static String getUserTableName() {
    return " ambre_fouvez.users " + getUserAbbreviation();
  }

}

