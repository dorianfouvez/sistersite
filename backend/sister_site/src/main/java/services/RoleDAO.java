/**
 * @author Fouvez Dorian.
 */
package services;

public interface RoleDAO {

  // ******************** Static's Methods ********************

  static String getAllRoleAttributes() {
    return " r.id, r.name";
  }

  static String getRoleTableName() {
    return " ambre_fouvez.roles r";
  }

}
