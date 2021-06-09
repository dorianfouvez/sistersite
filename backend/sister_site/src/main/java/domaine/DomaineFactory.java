/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.address.AddressDTO;
import domaine.cinema.ComplexCinemaDTO;
import domaine.color.ColorDTO;
import domaine.curriculum_vitae.ComplexCurriculumVitaeDTO;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ComplexShortFilmDTO;
import domaine.size.SizeDTO;
import domaine.strength.StrengthWithOrderDTO;
import domaine.tag.TagDTO;
import domaine.tag_photo.TagPhotoDTO;
import domaine.training.TrainingWithOrderDTO;
import domaine.user.ComplexUserDTO;
import domaine.user.UserDTO;

public interface DomaineFactory {

  AddressDTO getAddressDTO();

  ColorDTO getColorDTO();

  ComplexCinemaDTO getComplexCinemaDTO();

  ComplexCurriculumVitaeDTO getComplexCurriculumVitaeDTO();

  ComplexShortFilmDTO getComplexShortFilmDTO();

  ComplexUserDTO getComplexUserDTO();

  CurriculumVitaeDTO getCurriculumVitaeDTO();

  NationalityDTO getNationalityDTO();

  PhotoDTO getPhotoDTO();

  ProfessionDTO getProfessionDTO();

  SizeDTO getSizeDTO();

  StrengthWithOrderDTO getStrengthWithOrderDTO();

  TagDTO getTagDTO();

  TagPhotoDTO getTagPhotoDTO();

  TrainingWithOrderDTO getTrainingWithOrderDTO();

  UserDTO getUserDTO();

}
