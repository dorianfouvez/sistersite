package services;

public interface PhotographerDAO {

  // ******************** Static's Methods ********************

  public static String getAllPhotographerAttributes() {
    return " pg.id, pg.name";
  }

  public static String getPhotographerTableName() {
    return " ambre_fouvez.photographers pg";
  }

}
