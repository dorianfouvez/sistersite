package services;

public interface DirectorDAO {

  // ******************** Static's Methods ********************

  public static String getAllDirectorAttributes() {
    return " dr.id, dr.name";
  }

  public static String getDirectorTableName() {
    return " ambre_fouvez.directors dr";
  }

}
