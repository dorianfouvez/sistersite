/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.address.AddressDTO;
import domaine.address.AddressImpl;
import domaine.color.ColorDTO;
import domaine.color.ColorImpl;
import domaine.curriculum_vitae.CurriculumVitaeDTO;
import domaine.curriculum_vitae.CurriculumVitaeImpl;
import domaine.nationality.NationalityDTO;
import domaine.nationality.NationalityImpl;
import domaine.photo.PhotoDTO;
import domaine.photo.PhotoImpl;
import domaine.size.SizeDTO;
import domaine.size.SizeImpl;
import domaine.tag.TagDTO;
import domaine.tag.TagImpl;
import domaine.tag_photo.TagPhotoDTO;
import domaine.tag_photo.TagPhotoImpl;
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
  public SizeDTO getSizeDTO() {
    return new SizeImpl();
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
  public UserDTO getUserDTO() {
    return new UserImpl();
  }

}
