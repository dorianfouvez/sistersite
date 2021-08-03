/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;

public interface BasicUserDTO {

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

  double getHeight();

  void setHeight(double height);

  int getWeight();

  void setWeight(int weight);

  int getShoeSize();

  void setShoeSize(int shoeSize);

  int getJacketSize();

  void setJacketSize(int jacketSize);

  int getPantSize();

  void setPantSize(int pantSize);

  double getChest();

  void setChest(double chest);

  char getBraCup();

  void setBraCup(char braCup);

  double getWaistSize();

  void setWaistSize(double waistSize);

  double getHipSize();

  void setHipSize(double hipSize);

  double getNeckSize();

  void setNeckSize(double neckSize);

  double getHeadSize();

  void setHeadSize(double headSize);

  String getPassword();

  /**
   * change the password.
   * 
   * @param hashPassword : has to be Not Null
   */
  void setPassword(String hashPassword);

}
