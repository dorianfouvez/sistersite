/**
 * @author Fouvez Dorian.
 */
package services;

public interface DirectorCinemaDAO {

  // ******************** Static's Methods ********************

  static String getAllDirectorCinemaAttributes() {
    return " drci.cinema_id, drci.director_id";
  }

  static String getDirectorCinemaTableName() {
    return " ambre_fouvez.directors_cinema drci";
  }

}
