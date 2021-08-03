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
import domaine.address.AddressDTO;
import domaine.color.ColorDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.size.SizeDTO;
import jakarta.ws.rs.core.Response.Status;
import views.Views;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexUserImpl implements ComplexUser {

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
  private PhotoDTO profilePicture;
  @JsonView(Views.Public.class)
  private AddressDTO address;
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
  private ColorDTO hairColor;
  @JsonView(Views.Public.class)
  private SizeDTO hairSize;
  @JsonView(Views.Public.class)
  private ColorDTO eye;
  @JsonView(Views.Public.class)
  private double height;
  @JsonView(Views.Public.class)
  private int weight;
  @JsonView(Views.Public.class)
  private NationalityDTO firstNationality;
  @JsonView(Views.Public.class)
  private NationalityDTO secondNationality;
  @JsonView(Views.Public.class)
  private int shoeSize;
  @JsonView(Views.Public.class)
  private int jacketSize;
  @JsonView(Views.Public.class)
  private int pantSize;
  @JsonView(Views.Public.class)
  private double chest;
  @JsonView(Views.Public.class)
  private char braCup;
  @JsonView(Views.Public.class)
  private double waistSize;
  @JsonView(Views.Public.class)
  private double hipSize;
  @JsonView(Views.Public.class)
  private double neckSize;
  @JsonView(Views.Public.class)
  private double headSize;

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

  @Override
  public PhotoDTO getProfilePicture() {
    return profilePicture;
  }

  @Override
  public void setProfilePicture(PhotoDTO profilePicture) {
    this.profilePicture = profilePicture;
  }

  @Override
  public AddressDTO getAddress() {
    return address;
  }

  @Override
  public void setAddress(AddressDTO address) {
    this.address = address;
  }

  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String getFacebook() {
    return facebook;
  }

  @Override
  public void setFacebook(String facebook) {
    if (facebook != null) {
      PatternChecker.checkFacebookPattern(facebook);
    }
    this.facebook = facebook;
  }

  @Override
  public String getInstagram() {
    return instagram;
  }

  @Override
  public void setInstagram(String instagram) {
    if (instagram != null) {
      PatternChecker.checkInstagramPattern(instagram);
    }
    this.instagram = instagram;
  }

  @Override
  public String getTwitter() {
    return twitter;
  }

  @Override
  public void setTwitter(String twitter) {
    if (twitter != null) {
      PatternChecker.checkTwitterPattern(twitter);
    }
    this.twitter = twitter;
  }

  @Override
  public String getYoutube() {
    return youtube;
  }

  @Override
  public void setYoutube(String youtube) {
    if (youtube != null) {
      PatternChecker.checkYoutubePattern(youtube);
    }
    this.youtube = youtube;
  }

  @Override
  public ColorDTO getHairColor() {
    return hairColor;
  }

  @Override
  public void setHairColor(ColorDTO hairColor) {
    this.hairColor = hairColor;
  }

  @Override
  public SizeDTO getHairSize() {
    return hairSize;
  }

  @Override
  public void setHairSize(SizeDTO hairSize) {
    this.hairSize = hairSize;
  }

  @Override
  public ColorDTO getEye() {
    return eye;
  }

  @Override
  public void setEye(ColorDTO eye) {
    this.eye = eye;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public NationalityDTO getFirstNationality() {
    return firstNationality;
  }

  @Override
  public void setFirstNationality(NationalityDTO firstNationality) {
    this.firstNationality = firstNationality;
  }

  @Override
  public NationalityDTO getSecondNationality() {
    return secondNationality;
  }

  @Override
  public void setSecondNationality(NationalityDTO secondNationality) {
    this.secondNationality = secondNationality;
  }

  @Override
  public int getShoeSize() {
    return shoeSize;
  }

  @Override
  public void setShoeSize(int shoeSize) {
    this.shoeSize = shoeSize;
  }

  @Override
  public int getJacketSize() {
    return jacketSize;
  }

  @Override
  public void setJacketSize(int jacketSize) {
    this.jacketSize = jacketSize;
  }

  @Override
  public int getPantSize() {
    return pantSize;
  }

  @Override
  public void setPantSize(int pantSize) {
    this.pantSize = pantSize;
  }

  @Override
  public double getChest() {
    return chest;
  }

  @Override
  public void setChest(double chest) {
    this.chest = chest;
  }

  @Override
  public char getBraCup() {
    return braCup;
  }

  @Override
  public void setBraCup(char braCup) {
    this.braCup = braCup;
  }

  @Override
  public double getWaistSize() {
    return waistSize;
  }

  @Override
  public void setWaistSize(double waistSize) {
    this.waistSize = waistSize;
  }

  @Override
  public double getHipSize() {
    return hipSize;
  }

  @Override
  public void setHipSize(double hipSize) {
    this.hipSize = hipSize;
  }

  @Override
  public double getNeckSize() {
    return neckSize;
  }

  @Override
  public void setNeckSize(double neckSize) {
    this.neckSize = neckSize;
  }

  @Override
  public double getHeadSize() {
    return headSize;
  }

  @Override
  public void setHeadSize(double headSize) {
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
      boolean isBoss, Timestamp registrationDate, String password, PhotoDTO profilePicture,
      AddressDTO address, String phoneNumber, String facebook, String instagram, String twitter,
      String youtube, ColorDTO hairColor, SizeDTO hairSize, ColorDTO eye, double height, int weight,
      NationalityDTO firstNationality, NationalityDTO secondNationality, int shoeSize,
      int jacketSize, int pantSize, double chest, char braCup, double waistSize, double hipSize,
      double neckSize, double headSize) {

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
    return "ComplexUserImpl [id=" + id + ", userName=" + userName + ", lastName=" + lastName
        + ", firstName=" + firstName + ", email=" + email + ", isBoss=" + isBoss
        + ", registrationDate=" + registrationDate + ", profilePicture=" + profilePicture.toString()
        + ", address=" + address.toString() + ", phoneNumber=" + phoneNumber + ", facebook="
        + facebook + ", instagram=" + instagram + ", twitter=" + twitter + ", youtube=" + youtube
        + ", hairColor=" + hairColor.toString() + ", hairSize=" + hairSize.toString() + ", eye="
        + eye.toString() + ", height=" + height + ", weight=" + weight + ", firstNationality="
        + firstNationality.toString() + ", secondNationality=" + secondNationality.toString()
        + ", shoeSize=" + shoeSize + ", jacketSize=" + jacketSize + ", pantSize=" + pantSize
        + ", chest=" + chest + ", braCup=" + braCup + ", waistSize=" + waistSize + ", hipSize="
        + hipSize + ", neckSize=" + neckSize + ", headSize=" + headSize + ", password=" + password
        + "]";
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
    ComplexUserImpl other = (ComplexUserImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
