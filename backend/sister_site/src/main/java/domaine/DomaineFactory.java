/**
 * @author Fouvez Dorian.
 */
package domaine;

import domaine.photo.PhotoDTO;
import domaine.user.UserDTO;

public interface DomaineFactory {

  UserDTO getUserDTO();

  PhotoDTO getPhotoDTO();

}
