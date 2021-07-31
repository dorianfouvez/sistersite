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
    return " " + getPhotographerAbbreviation() + ".id, " + getPhotographerAbbreviation() + ".name, "
        + getPhotographerAbbreviation() + ".instagram";
  }

  static String getPhotographerAbbreviation() {
    return "pg";
  }

  static String getPhotographerTableName() {
    return " ambre_fouvez.photographers " + getPhotographerAbbreviation();
  }

}
