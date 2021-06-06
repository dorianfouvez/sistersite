package services;

public interface CompanyShortFilmDAO {

  // ******************** Static's Methods ********************

  public static String getAllCompanyShortFilmAttributes() {
    return " cpsf.short_film_id, cpsf.company_id";
  }

  public static String getCompanyShortFilmTableName() {
    return " ambre_fouvez.companies_short_film cpsf";
  }

}
