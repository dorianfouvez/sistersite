/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import api.utils.BusinessException;
import jakarta.ws.rs.core.Response.Status;
import views.Views;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserImpl implements User {

  @JsonView(Views.Public.class)
  private int id;
  @JsonView(Views.Public.class)
  private String username;
  @JsonView(Views.Public.class)
  private String lastName;
  @JsonView(Views.Public.class)
  private String firstName;
  @JsonView(Views.Public.class)
  private String email;
  @JsonView(Views.Public.class)
  private boolean isBoss;
  @JsonView(Views.Public.class)
  private Timestamp registrationDate;
  @JsonView(Views.Public.class)
  private int profilePicture;
  @JsonView(Views.Public.class)
  private int adress;
  @JsonView(Views.Public.class)
  private String phoneNumber;
  @JsonView(Views.Public.class)
  private String facebook;
  @JsonView(Views.Public.class)
  private String instagram;
  @JsonView(Views.Public.class)
  private String twitter;
  @JsonView(Views.Public.class)
  private String youtube;
  @JsonView(Views.Public.class)
  private int hairColor;
  @JsonView(Views.Public.class)
  private int hairSize;
  @JsonView(Views.Public.class)
  private int eye;
  @JsonView(Views.Public.class)
  private int height;
  @JsonView(Views.Public.class)
  private int weight;
  @JsonView(Views.Public.class)
  private int firstNationality;
  @JsonView(Views.Public.class)
  private int secondNationality;
  @JsonView(Views.Public.class)
  private int shoeSize;
  @JsonView(Views.Public.class)
  private int jacketSize;
  @JsonView(Views.Public.class)
  private int pantSize;
  @JsonView(Views.Public.class)
  private int chest;
  @JsonView(Views.Public.class)
  private char braCup;
  @JsonView(Views.Public.class)
  private int waistSize;
  @JsonView(Views.Public.class)
  private int hipSize;
  @JsonView(Views.Public.class)
  private int neckSize;
  @JsonView(Views.Public.class)
  private int headSize;

  @JsonView(Views.Internal.class)
  private String password;

  @Override
  public int getID() {
    return id;
  }

  @Override
  public void setID(int id) {
    this.id = id;
  }

  @Override
  public String getUserName() {
    return username;
  }

  @Override
  public void setUserName(String username) {
    this.username = username;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    String regex = "^[\\w\\.\\/\\\\$é~#èà&=+*-]+@[\\w\\.]+\\.[a-zA-Z]{2,4}$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(twitter);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.email = email;
  }

  @Override
  public boolean isBoss() {
    return isBoss;
  }

  @Override
  public void setBoss(boolean isBoss) {
    this.isBoss = isBoss;
  }

  @Override
  public Timestamp getRegistrationDate() {
    return registrationDate;
  }

  @Override
  public void setRegistrationDate(Timestamp registrationDate) {
    if (registrationDate.after(new Timestamp(System.currentTimeMillis()))) {
      throw new BusinessException("The registration date can't be after now.", Status.BAD_REQUEST);
    }
    this.registrationDate = registrationDate;
  }

  public int getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(int profilePicture) {
    this.profilePicture = profilePicture;
  }

  public int getAdress() {
    return adress;
  }

  public void setAdress(int adress) {
    this.adress = adress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    String regex = "^https:\\/\\/www\\.facebook\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(facebook);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.facebook = facebook;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    String regex = "^https:\\/\\/www\\.instagram\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+\\/$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(instagram);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.instagram = instagram;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    String regex = "^https:\\/\\/www\\.twitter\\.com\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(twitter);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.twitter = twitter;
  }

  public String getYoutube() {
    return youtube;
  }

  public void setYoutube(String youtube) {
    String regex = "^https:\\/\\/www\\.youtube\\.com\\/channel\\/[\\w\\.\\/\\\\$é~#èà&=+*-]+$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(youtube);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.youtube = youtube;
  }

  public int getHairColor() {
    return hairColor;
  }

  public void setHairColor(int hairColor) {
    this.hairColor = hairColor;
  }

  public int getHairSize() {
    return hairSize;
  }

  public void setHairSize(int hairSize) {
    this.hairSize = hairSize;
  }

  public int getEye() {
    return eye;
  }

  public void setEye(int eye) {
    this.eye = eye;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getFirstNationality() {
    return firstNationality;
  }

  public void setFirstNationality(int firstNationality) {
    this.firstNationality = firstNationality;
  }

  public int getSecondNationality() {
    return secondNationality;
  }

  public void setSecondNationality(int secondNationality) {
    this.secondNationality = secondNationality;
  }

  public int getShoeSize() {
    return shoeSize;
  }

  public void setShoeSize(int shoeSize) {
    this.shoeSize = shoeSize;
  }

  public int getJacketSize() {
    return jacketSize;
  }

  public void setJacketSize(int jacketSize) {
    this.jacketSize = jacketSize;
  }

  public int getPantSize() {
    return pantSize;
  }

  public void setPantSize(int pantSize) {
    this.pantSize = pantSize;
  }

  public int getChest() {
    return chest;
  }

  public void setChest(int chest) {
    this.chest = chest;
  }

  public char getBraCup() {
    return braCup;
  }

  public void setBraCup(char braCup) {
    this.braCup = braCup;
  }

  public int getWaistSize() {
    return waistSize;
  }

  public void setWaistSize(int waistSize) {
    this.waistSize = waistSize;
  }

  public int getHipSize() {
    return hipSize;
  }

  public void setHipSize(int hipSize) {
    this.hipSize = hipSize;
  }

  public int getNeckSize() {
    return neckSize;
  }

  public void setNeckSize(int neckSize) {
    this.neckSize = neckSize;
  }

  public int getHeadSize() {
    return headSize;
  }

  public void setHeadSize(int headSize) {
    this.headSize = headSize;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String hashPassword) {
    this.password = hashPassword;
  }

  @Override
  public boolean checkPassword(String password) {
    return BCrypt.checkpw(password, this.password);
  }

  @Override
  public String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  @Override
  public String toString() {
    return "UserImpl [id=" + id + ", username=" + username + ", lastName=" + lastName
        + ", firstName=" + firstName + ", email=" + email + ", isBoss=" + isBoss
        + ", registrationDate=" + registrationDate + ", profilePicture=" + profilePicture
        + ", adress=" + adress + ", phoneNumber=" + phoneNumber + ", facebook=" + facebook
        + ", instagram=" + instagram + ", twitter=" + twitter + ", youtube=" + youtube
        + ", hairColor=" + hairColor + ", hairSize=" + hairSize + ", eye=" + eye + ", height="
        + height + ", weight=" + weight + ", firstNationality=" + firstNationality
        + ", secondNationality=" + secondNationality + ", shoeSize=" + shoeSize + ", jacketSize="
        + jacketSize + ", pantSize=" + pantSize + ", chest=" + chest + ", braCup=" + braCup
        + ", waistSize=" + waistSize + ", hipSize=" + hipSize + ", neckSize=" + neckSize
        + ", headSize=" + headSize + ", password=" + password + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UserImpl other = (UserImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
