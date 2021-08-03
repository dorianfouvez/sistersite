/**
 * @author Fouvez Dorian.
 */
package domaine.photographer;

import java.util.List;

public interface PhotographerUCC {

  PhotographerDTO findById(int id);

  List<PhotographerDTO> getAll();

  PhotographerDTO add(PhotographerDTO photographer);

  boolean nameAlreadyExist(String name);

  boolean instagramAlreadyExist(String instagram);

}
