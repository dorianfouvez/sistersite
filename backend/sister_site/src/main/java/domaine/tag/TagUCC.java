/**
 * @author Fouvez Dorian.
 */
package domaine.tag;

import java.util.List;

public interface TagUCC {

  TagDTO findById(int id);

  List<TagDTO> getAll();

  TagDTO add(TagDTO tag);

  boolean labelAlreadyExist(String label);

}
