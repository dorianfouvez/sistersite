package services;

public interface DirectorCinemaDAO {

  // ******************** Static's Methods ********************

  public static String getAllDirectorCinemaAttributes() {
    return " drci.cinema_id, drci.director_id";
  }

  public static String getDirectorCinemaTableName() {
    return " ambre_fouvez.directors_cinema drci";
  }

}
