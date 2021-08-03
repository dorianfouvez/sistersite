/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImpl.class)
public interface UserDTO extends BasicUserDTO {

  int getProfilePicture();

  /**
   * Change the profile picture.
   * 
   * @param profilePicture the id of the picture and has to be more then 0.
   */
  void setProfilePicture(int profilePicture);

  int getAddress();

  /**
   * Change the address.
   * 
   * @param address the id of the address and has to be more than 0.
   */
  void setAddress(int address);

  int getHairColor();

  void setHairColor(int hairColor);

  int getHairSize();

  void setHairSize(int hairSize);

  int getEye();

  void setEye(int eye);

  int getFirstNationality();

  void setFirstNationality(int firstNationality);

  int getSecondNationality();

  void setSecondNationality(int secondNationality);

  void fullFillUser(int id, String username, String lastName, String firstName, String email,
      boolean isBoss, Timestamp registrationDate, String password, int profilePicture, int address,
      String phoneNumber, String facebook, String instagram, String twitter, String youtube,
      int hairColor, int hairSize, int eye, double height, int weight, int firstNationality,
      int secondNationality, int shoeSize, int jacketSize, int pantSize, double chest, char braCup,
      double waistSize, double hipSize, double neckSize, double headSize);

}
