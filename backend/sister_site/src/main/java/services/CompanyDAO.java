package services;

public interface CompanyDAO {

  // ******************** Static's Methods ********************

  static String getAllCompanyAttributes() {
    return " cp.id, cp.name";
  }

  static String getCompanyTableName() {
    return " ambre_fouvez.companies cp";
  }

}
