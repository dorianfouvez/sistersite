package services;

public interface ShortFilmDAO {

  // ******************** Static's Methods ********************

  static String getAllShortFilmAttributes() {
    return " sf.id, sf.title, sf.role, sf.year";
  }

  static String getShortFilmTableName() {
    return " ambre_fouvez.short_films sf";
  }

}
