/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

public interface PhotoUCC {

  PhotoDTO findById(int id);

  void delete(int id);

}
