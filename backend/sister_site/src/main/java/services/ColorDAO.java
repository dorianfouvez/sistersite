/**
 * @author Fouvez Dorian.
 */
package services;

public interface ColorDAO {

  // ******************** Static's Methods ********************

  static String getAllColorAttributes() {
    return getAllColorAttributesWithName(getColorAbbreviation());
  }

  static String getAllColorAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".color";
  }

  static String getColorAbbreviation() {
    return "c";
  }

  static String getColorTableName() {
    return getColorTableNameWithName(getColorAbbreviation());
  }

  static String getColorTableNameWithName(String name) {
    return " ambre_fouvez.colours " + name;
  }

  static String getUserColorJoint(String userAttribute) {
    return " LEFT JOIN" + ColorDAO.getColorTableName() + " ON " + UserDAO.getUserAbbreviation()
        + "." + userAttribute + " = " + ColorDAO.getColorAbbreviation() + ".id";
  }

  static String getUserColorJoint(String userAttribute, String name) {
    return " LEFT JOIN" + ColorDAO.getColorTableNameWithName(name) + " ON "
        + UserDAO.getUserAbbreviation() + "." + userAttribute + " = " + name + ".id";
  }

}
