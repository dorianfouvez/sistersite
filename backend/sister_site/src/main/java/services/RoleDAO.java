package services;

public interface RoleDAO {

  // ******************** Static's Methods ********************

  public static String getAllRoleAttributes() {
    return " r.id, r.name";
  }

  public static String getRoleTableName() {
    return " ambre_fouvez.roles r";
  }

}
