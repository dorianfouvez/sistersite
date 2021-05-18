/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.photo.PhotoDTO;
import domaine.photo.PhotoImpl;
import domaine.user.UserDTO;
import domaine.user.UserImpl;

public class DomaineFactoryImpl implements DomaineFactory {

  @Override
  public UserDTO getUserDTO() {
    return new UserImpl();
  }

  @Override
  public PhotoDTO getPhotoDTO() {
    return new PhotoImpl();
  }


}
