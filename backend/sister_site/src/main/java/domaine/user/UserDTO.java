/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImpl.class)
public interface UserDTO {

  int getID();

  void setID(int id);

  String getUserName();

  /**
   * Change the username of the user if is not already use in the DB.
   * 
   * @param username : not already use in the DB and has to be Not Null.
   */
  void setUserName(String username);

  String getLastName();

  /**
   * Change the last name.
   * 
   * @param lastName : has to be Not Null.
   */
  void setLastName(String lastName);

  String getFirstName();

  /**
   * Change the first name.
   * 
   * @param firstName : has to be Not Null.
   */
  void setFirstName(String firstName);

  String getEmail();

  /**
   * Change the email of the user if is not already use in the DB.
   * 
   * @param email : not already use in the DB and has to be Not Null.
   */
  void setEmail(String email);

  boolean isBoss();

  void setBoss(boolean isBoss);

  Timestamp getRegistrationDate();

  void setRegistrationDate(Timestamp timestamp);

  String getPassword();

  /**
   * change the password.
   * 
   * @param hashPassword : has to be Not Null
   */
  void setPassword(String hashPassword);
}
