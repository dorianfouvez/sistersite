/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.tag.TagDTO;

public interface TagDAO {

  TagDTO add(TagDTO tag);

  TagDTO findById(int id);

  TagDTO findByLabel(String label);

  List<TagDTO> getAllSortedByLabel();



  // ******************** Static's Methods ********************

  static String getAllTagAttributes() {
    return " " + getTagAbbreviation() + ".id, " + getTagAbbreviation() + ".label";
  }

  static String getTagAbbreviation() {
    return "ta";
  }

  static String getTagTableName() {
    return getTagTableNameWithoutAbbreviation() + " " + getTagAbbreviation();
  }

  static String getTagTableNameWithoutAbbreviation() {
    return " ambre_fouvez.tags";
  }

}
