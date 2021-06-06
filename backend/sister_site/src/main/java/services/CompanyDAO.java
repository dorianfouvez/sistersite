package services;

public interface CompanyDAO {

  // ******************** Static's Methods ********************

  public static String getAllCompanyAttributes() {
    return " cp.id, cp.name";
  }

  public static String getCompanyTableName() {
    return " ambre_fouvez.companies cp";
  }

}
