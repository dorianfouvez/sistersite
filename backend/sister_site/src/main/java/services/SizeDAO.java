/**
 * @author Fouvez Dorian.
 */
package services;

public interface SizeDAO {

  // ******************** Static's Methods ********************

  static String getAllSizeAttributes() {
    return " " + getSizeAbbreviation() + ".id, " + getSizeAbbreviation() + ".size";
  }

  static String getSizeAbbreviation() {
    return "s";
  }

  static String getSizeTableName() {
    return " ambre_fouvez.sizes " + getSizeAbbreviation();
  }

  static String getUserSizeJoint() {
    return " LEFT JOIN" + SizeDAO.getSizeTableName() + " ON " + UserDAO.getUserAbbreviation()
        + ".hair_size = " + SizeDAO.getSizeAbbreviation() + ".id";
  }

}
