/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.short_film.ComplexShortFilmDTO;

public interface ShortFilmDAO {

  List<ComplexShortFilmDTO> getAllComplexShortFilmForCV(int cvId);



  // ******************** Static's Methods ********************

  static String getAllShortFilmAttributes() {
    return " sf.id, sf.title, sf.role, sf.year";
  }

  static String getShortFilmTableName() {
    return " ambre_fouvez.short_films sf";
  }

}
