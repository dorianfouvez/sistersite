/**
 * @author Fouvez Dorian.
 */
package domaine.user;

import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.address.AddressDTO;
import domaine.color.ColorDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.size.SizeDTO;

@JsonDeserialize(as = ComplexUserImpl.class)
public interface ComplexUserDTO extends BasicUserDTO {

  PhotoDTO getProfilePicture();

  /**
   * Change the profile picture.
   * 
   * @param profilePicture has to be not null.
   */
  void setProfilePicture(PhotoDTO profilePicture);

  AddressDTO getAddress();

  /**
   * Change the address.
   * 
   * @param address has to be not null.
   */
  void setAddress(AddressDTO address);

  ColorDTO getHairColor();

  void setHairColor(ColorDTO hairColor);

  SizeDTO getHairSize();

  void setHairSize(SizeDTO hairSize);

  ColorDTO getEye();

  void setEye(ColorDTO eye);

  NationalityDTO getFirstNationality();

  void setFirstNationality(NationalityDTO firstNationality);

  NationalityDTO getSecondNationality();

  void setSecondNationality(NationalityDTO secondNationality);

  void fullFillUser(int id, String username, String lastName, String firstName, String email,
      boolean isBoss, Timestamp registrationDate, String password, PhotoDTO profilePicture,
      AddressDTO address, String phoneNumber, String facebook, String instagram, String twitter,
      String youtube, ColorDTO hairColor, SizeDTO hairSize, ColorDTO eye, int height, int weight,
      NationalityDTO firstNationality, NationalityDTO secondNationality, int shoeSize,
      int jacketSize, int pantSize, int chest, char braCup, int waistSize, int hipSize,
      int neckSize, int headSize);

}
