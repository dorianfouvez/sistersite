package services;

public interface NationalityDAO {

  // ******************** Static's Methods ********************

  public static String getAllNationalityAttributes() {
    return getAllNationalityAttributesWithName("n");
  }

  public static String getAllNationalityAttributesWithName(String name) {
    return " " + name + ".id, " + name + ".nationality";
  }

  public static String getNationalityTableName() {
    return getAllNationalityAttributesWithName("n");
  }

  public static String getNationalityTableNameWithName(String name) {
    return " ambre_fouvez.nationalities " + name;
  }

}
