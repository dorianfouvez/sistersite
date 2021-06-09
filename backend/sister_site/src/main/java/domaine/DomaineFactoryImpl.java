/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.address.AddressDTO;
import domaine.address.AddressImpl;
import domaine.cinema.ComplexCinemaDTO;
import domaine.cinema.ComplexCinemaImpl;
import domaine.color.ColorDTO;
import domaine.color.ColorImpl;
import domaine.curriculum_vitae.ComplexCurriculumVitaeDTO;
import domaine.curriculum_vitae.ComplexCurriculumVitaeImpl;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import domaine.curriculum_vitae.CurriculumVitaeImpl;
import domaine.nationality.NationalityDTO;
import domaine.nationality.NationalityImpl;
import domaine.photo.PhotoDTO;
import domaine.photo.PhotoImpl;
import domaine.profession.ProfessionDTO;
import domaine.profession.ProfessionImpl;
import domaine.short_film.ComplexShortFilmDTO;
import domaine.short_film.ComplexShortFilmImpl;
import domaine.size.SizeDTO;
import domaine.size.SizeImpl;
import domaine.strength.StrengthWithOrderDTO;
import domaine.strength.StrengthWithOrderImpl;
import domaine.tag.TagDTO;
import domaine.tag.TagImpl;
import domaine.tag_photo.TagPhotoDTO;
import domaine.tag_photo.TagPhotoImpl;
import domaine.training.TrainingWithOrderDTO;
import domaine.training.TrainingWithOrderImpl;
import domaine.user.ComplexUserDTO;
import domaine.user.ComplexUserImpl;
import domaine.user.UserDTO;
import domaine.user.UserImpl;

public class DomaineFactoryImpl implements DomaineFactory {

  @Override
  public AddressDTO getAddressDTO() {
    return new AddressImpl();
  }

  @Override
  public ColorDTO getColorDTO() {
    return new ColorImpl();
  }

  @Override
  public ComplexCinemaDTO getComplexCinemaDTO() {
    return new ComplexCinemaImpl();
  }

  @Override
  public ComplexCurriculumVitaeDTO getComplexCurriculumVitaeDTO() {
    return new ComplexCurriculumVitaeImpl();
  }

  @Override
  public ComplexShortFilmDTO getComplexShortFilmDTO() {
    return new ComplexShortFilmImpl();
  }

  @Override
  public ComplexUserDTO getComplexUserDTO() {
    return new ComplexUserImpl();
  }

  @Override
  public CurriculumVitaeDTO getCurriculumVitaeDTO() {
    return new CurriculumVitaeImpl();
  }

  @Override
  public NationalityDTO getNationalityDTO() {
    return new NationalityImpl();
  }

  @Override
  public PhotoDTO getPhotoDTO() {
    return new PhotoImpl();
  }

  @Override
  public ProfessionDTO getProfessionDTO() {
    return new ProfessionImpl();
  }

  @Override
  public SizeDTO getSizeDTO() {
    return new SizeImpl();
  }

  @Override
  public StrengthWithOrderDTO getStrengthWithOrderDTO() {
    return new StrengthWithOrderImpl();
  }

  @Override
  public TagDTO getTagDTO() {
    return new TagImpl();
  }

  @Override
  public TagPhotoDTO getTagPhotoDTO() {
    return new TagPhotoImpl();
  }

  @Override
  public TrainingWithOrderDTO getTrainingWithOrderDTO() {
    return new TrainingWithOrderImpl();
  }

  @Override
  public UserDTO getUserDTO() {
    return new UserImpl();
  }

}
