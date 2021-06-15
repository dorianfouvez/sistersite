/**
 * @author Fouvez Dorian.
 */
package services;

public interface PhotographerDAO {

  // ******************** Static's Methods ********************

  static String getAllPhotographerAttributes() {
    return " pg.id, pg.name";
  }

  static String getPhotographerTableName() {
    return " ambre_fouvez.photographers pg";
  }

}
