package services;

public interface NationalityDAO {

  // ******************** Static's Methods ********************

  public static String getAllNationalityAttributes() {
    return " n.id, n.nationality";
  }

  public static String getNationalityTableName() {
    return " ambre_fouvez.nationalities n";
  }

}
