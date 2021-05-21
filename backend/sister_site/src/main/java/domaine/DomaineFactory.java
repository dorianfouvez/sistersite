/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.adress.AdressDTO;
import domaine.color.ColorDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.size.SizeDTO;
import domaine.tag.TagDTO;
import domaine.tag_photo.TagPhotoDTO;
import domaine.user.UserDTO;

public interface DomaineFactory {

  AdressDTO getAdressDTO();

  ColorDTO getColorDTO();

  NationalityDTO getNationalityDTO();

  PhotoDTO getPhotoDTO();

  SizeDTO getSizeDTO();

  TagDTO getTagDTO();

  TagPhotoDTO getTagPhotoDTO();

  UserDTO getUserDTO();

}
