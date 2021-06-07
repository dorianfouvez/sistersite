package services;

public interface DirectorDAO {

  // ******************** Static's Methods ********************

  static String getAllDirectorAttributes() {
    return " dr.id, dr.name";
  }

  static String getDirectorTableName() {
    return " ambre_fouvez.directors dr";
  }

}
