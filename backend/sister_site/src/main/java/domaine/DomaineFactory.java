/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.address.AddressDTO;
import domaine.color.ColorDTO;
import domaine.curriculum_vitae.ComplexCurriculumVitaeDTO;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.size.SizeDTO;
import domaine.tag.TagDTO;
import domaine.tag_photo.TagPhotoDTO;
import domaine.user.ComplexUserDTO;
import domaine.user.UserDTO;

public interface DomaineFactory {

  AddressDTO getAddressDTO();

  ColorDTO getColorDTO();

  ComplexCurriculumVitaeDTO getComplexCurriculumVitaeDTO();

  ComplexUserDTO getComplexUserDTO();

  CurriculumVitaeDTO getCurriculumVitaeDTO();

  NationalityDTO getNationalityDTO();

  PhotoDTO getPhotoDTO();

  ProfessionDTO getProfessionDTO();

  SizeDTO getSizeDTO();

  TagDTO getTagDTO();

  TagPhotoDTO getTagPhotoDTO();

  UserDTO getUserDTO();

}
