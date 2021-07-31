/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.tag.TagDTO;

public interface TagDAO {

  TagDTO findById(int id);

  List<TagDTO> getAllSortedByLabel();



  // ******************** Static's Methods ********************

  static String getAllTagAttributes() {
    return " ta.id, ta.label";
  }

  static String getTagAbbreviation() {
    return "ta";
  }

  static String getTagTableName() {
    return " ambre_fouvez.tags ta";
  }

}
