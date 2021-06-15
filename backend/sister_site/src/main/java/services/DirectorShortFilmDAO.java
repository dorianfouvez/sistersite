/**
 * @author Fouvez Dorian.
 */
package services;

public interface DirectorShortFilmDAO {

  // ******************** Static's Methods ********************

  static String getAllDirectorShortFilmAttributes() {
    return " drsf.short_film_id, drsf.director_id";
  }

  static String getDirectorShortFilmTableName() {
    return " ambre_fouvez.directors_short_film drsf";
  }

}
