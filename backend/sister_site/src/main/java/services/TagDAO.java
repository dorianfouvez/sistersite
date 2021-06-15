/**
 * @author Fouvez Dorian.
 */
package services;

public interface TagDAO {

  // ******************** Static's Methods ********************

  static String getAllTagAttributes() {
    return " ta.id, ta.label";
  }

  static String getTagTableName() {
    return " ambre_fouvez.tags ta";
  }

}
