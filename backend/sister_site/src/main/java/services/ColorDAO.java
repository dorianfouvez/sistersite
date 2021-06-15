/**
 * @author Fouvez Dorian.
 */
package services;

public interface ColorDAO {

  // ******************** Static's Methods ********************

  static String getAllColorAttributes() {
    return getAllColorAttributesWithName("c");
  }

  static String getAllColorAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".color";
  }

  static String getColorTableName() {
    return getColorTableNameWithName("c");
  }

  static String getColorTableNameWithName(String name) {
    return " ambre_fouvez.colours " + name;
  }

}
