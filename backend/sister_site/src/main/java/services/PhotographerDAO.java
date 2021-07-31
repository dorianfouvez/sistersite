/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.photographer.PhotographerDTO;

public interface PhotographerDAO {

  PhotographerDTO findById(int id);

  List<PhotographerDTO> getAll();



  // ******************** Static's Methods ********************

  static String getAllPhotographerAttributes() {
    return " pg.id, pg.name";
  }

  static String getPhotographerAbbreviation() {
    return "pg";
  }

  static String getPhotographerTableName() {
    return " ambre_fouvez.photographers pg";
  }

}
