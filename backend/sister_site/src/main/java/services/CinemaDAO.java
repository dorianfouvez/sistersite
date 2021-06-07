package services;

public interface CinemaDAO {

  // ******************** Static's Methods ********************

  static String getAllCinemaAttributes() {
    return " ci.id, ci.title, ci.role, ci.year";
  }

  static String getCinemaTableName() {
    return " ambre_fouvez.cinemas ci";
  }

}
