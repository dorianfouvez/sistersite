/**
 * @author Fouvez Dorian.
 */
package services;

public interface NationalityDAO {

  // ******************** Static's Methods ********************

  static String getAllNationalityAttributes() {
    return getAllNationalityAttributesWithName(getNationalityAbbreviation());
  }

  static String getAllNationalityAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".nationality";
  }

  static String getNationalityAbbreviation() {
    return "n";
  }

  static String getNationalityTableName() {
    return getNationalityTableNameWithName(getNationalityAbbreviation());
  }

  static String getNationalityTableNameWithName(String name) {
    return " ambre_fouvez.nationalities " + name;
  }

  static String getUserNationalityJoint(String userAttribute) {
    return " LEFT JOIN" + NationalityDAO.getNationalityTableName() + " ON "
        + UserDAO.getUserAbbreviation() + "." + userAttribute + " = "
        + NationalityDAO.getNationalityAbbreviation() + ".id";
  }

  static String getUserNationalityJointWithName(String userAttribute, String name) {
    return " LEFT JOIN" + NationalityDAO.getNationalityTableNameWithName(name) + " ON "
        + UserDAO.getUserAbbreviation() + "." + userAttribute + " = " + name + ".id";
  }

}
