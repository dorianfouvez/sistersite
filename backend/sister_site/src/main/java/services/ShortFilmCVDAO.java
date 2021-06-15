/**
 * @author Fouvez Dorian.
 */
package services;

public interface ShortFilmCVDAO {

  // ******************** Static's Methods ********************

  static String getAllShortFilmCVAttributes() {
    return " sfcv.curriculum_vitae, sfcv.short_film, sfcv.order_number";
  }

  static String getShortFilmCVTableName() {
    return " ambre_fouvez.short_films_curriculum_vitae sfcv";
  }

}
