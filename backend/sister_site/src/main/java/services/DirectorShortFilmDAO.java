package services;

public interface DirectorShortFilmDAO {

  // ******************** Static's Methods ********************

  public static String getAllDirectorShortFilmAttributes() {
    return " drsf.short_film_id, drsf.director_id";
  }

  public static String getDirectorShortFilmTableName() {
    return " ambre_fouvez.directors_short_film drsf";
  }

}
