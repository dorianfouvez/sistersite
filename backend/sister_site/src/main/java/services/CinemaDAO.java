package services;

public interface CinemaDAO {

  // ******************** Static's Methods ********************

  public static String getAllCinemaAttributes() {
    return " ci.id, ci.title, ci.role, ci.year";
  }

  public static String getCinemaTableName() {
    return " ambre_fouvez.cinemas ci";
  }

}
