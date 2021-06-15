/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;
import org.mindrot.jbcrypt.BCrypt;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import api.utils.BusinessException;
import api.utils.PatternChecker;
import jakarta.ws.rs.core.Response.Status;
import views.Views;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserImpl implements User {

  @JsonView(Views.Public.class)
  private int id;
  @JsonView(Views.Public.class)
  private String userName;
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
  private int address;
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
    return userName;
  }

  @Override
  public void setUserName(String userName) {
    this.userName = userName;
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
    if (email == null) {
      throw new BusinessException("The email can't be null.", Status.BAD_REQUEST);
    }

    PatternChecker.checkEmailPattern(email);
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
    if (registrationDate == null) {
      throw new BusinessException("The registration date can't be null.", Status.BAD_REQUEST);
    }
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

  public int getAddress() {
    return address;
  }

  public void setAddress(int address) {
    this.address = address;
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
    if (facebook != null) {
      PatternChecker.checkFacebookPattern(facebook);
    }
    this.facebook = facebook;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    if (instagram != null) {
      PatternChecker.checkInstagramPattern(instagram);
    }
    this.instagram = instagram;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    if (twitter != null) {
      PatternChecker.checkTwitterPattern(twitter);
    }
    this.twitter = twitter;
  }

  public String getYoutube() {
    return youtube;
  }

  public void setYoutube(String youtube) {
    if (youtube != null) {
      PatternChecker.checkYoutubePattern(youtube);
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
  public void fullFillUser(int id, String username, String lastName, String firstName, String email,
      boolean isBoss, Timestamp registrationDate, String password, int profilePicture, int address,
      String phoneNumber, String facebook, String instagram, String twitter, String youtube,
      int hairColor, int hairSize, int eye, int height, int weight, int firstNationality,
      int secondNationality, int shoeSize, int jacketSize, int pantSize, int chest, char braCup,
      int waistSize, int hipSize, int neckSize, int headSize) {

    setID(id);
    setUserName(username);
    setLastName(lastName);
    setFirstName(firstName);
    setEmail(email);
    setBoss(isBoss);
    setRegistrationDate(registrationDate);
    setPassword(password);
    setProfilePicture(profilePicture);
    setAddress(address);
    setPhoneNumber(phoneNumber);
    setFacebook(facebook);
    setInstagram(instagram);
    setTwitter(twitter);
    setYoutube(youtube);
    setHairColor(hairColor);
    setHairSize(hairSize);
    setEye(eye);
    setHeight(height);
    setWeight(weight);
    setFirstNationality(firstNationality);
    setSecondNationality(secondNationality);
    setShoeSize(shoeSize);
    setJacketSize(jacketSize);
    setPantSize(pantSize);
    setChest(chest);
    setBraCup(braCup);
    setWaistSize(waistSize);
    setHipSize(hipSize);
    setNeckSize(neckSize);
    setHeadSize(headSize);
  }

  @Override
  public String toString() {
    return "UserImpl [id=" + id + ", userName=" + userName + ", lastName=" + lastName
        + ", firstName=" + firstName + ", email=" + email + ", isBoss=" + isBoss
        + ", registrationDate=" + registrationDate + ", profilePicture=" + profilePicture
        + ", address=" + address + ", phoneNumber=" + phoneNumber + ", facebook=" + facebook
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
