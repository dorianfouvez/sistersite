/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.adress.AdressDTO;
import domaine.adress.AdressImpl;
import domaine.color.ColorDTO;
import domaine.color.ColorImpl;
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
  public AdressDTO getAdressDTO() {
    return new AdressImpl();
  }

  @Override
  public ColorDTO getColorDTO() {
    return new ColorImpl();
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
