/**
 * @author Fouvez Dorian.
 */
package services;

public interface NationalityDAO {

  // ******************** Static's Methods ********************

  static String getAllNationalityAttributes() {
    return getAllNationalityAttributesWithName("n");
  }

  static String getAllNationalityAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".nationality";
  }

  static String getNationalityTableName() {
    return getNationalityTableNameWithName("n");
  }

  static String getNationalityTableNameWithName(String name) {
    return " ambre_fouvez.nationalities " + name;
  }

}
