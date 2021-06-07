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
   * Change the userName of the user if is not already use in the DB.
   * 
   * @param userName : not already use in the DB and has to be Not Null.
   */
  void setUserName(String userName);

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

  /**
   * Change the regitration date.
   * 
   * @param registrationDate has to be Not Null.
   */
  void setRegistrationDate(Timestamp registrationDate);

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

  String getPhoneNumber();

  /**
   * Change the phone number.
   * 
   * @param phoneNumber has to be Not Null.
   */
  void setPhoneNumber(String phoneNumber);

  String getFacebook();

  void setFacebook(String facebook);

  String getInstagram();

  void setInstagram(String instagram);

  String getTwitter();

  void setTwitter(String twitter);

  String getYoutube();

  void setYoutube(String youtube);

  int getHairColor();

  void setHairColor(int hairColor);

  int getHairSize();

  void setHairSize(int hairSize);

  int getEye();

  void setEye(int eye);

  int getHeight();

  void setHeight(int height);

  int getWeight();

  void setWeight(int weight);

  int getFirstNationality();

  void setFirstNationality(int firstNationality);

  int getSecondNationality();

  void setSecondNationality(int secondNationality);

  int getShoeSize();

  void setShoeSize(int shoeSize);

  int getJacketSize();

  void setJacketSize(int jacketSize);

  int getPantSize();

  void setPantSize(int pantSize);

  int getChest();

  void setChest(int chest);

  char getBraCup();

  void setBraCup(char braCup);

  int getWaistSize();

  void setWaistSize(int waistSize);

  int getHipSize();

  void setHipSize(int hipSize);

  int getNeckSize();

  void setNeckSize(int neckSize);

  int getHeadSize();

  void setHeadSize(int headSize);

  String getPassword();

  /**
   * change the password.
   * 
   * @param hashPassword : has to be Not Null
   */
  void setPassword(String hashPassword);

  void fullfillUser(int id, String username, String lastName, String firstName, String email,
      boolean isBoss, Timestamp registrationDate, String password, int profilePicture, int address,
      String phoneNumber, String facebook, String instagram, String twitter, String youtube,
      int hairColor, int hairSize, int eye, int height, int weight, int firstNationality,
      int secondNationality, int shoeSize, int jacketSize, int pantSize, int chest, char braCup,
      int waistSize, int hipSize, int neckSize, int headSize);

}
