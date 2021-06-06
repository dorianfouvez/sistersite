package services;

public interface ShortFilmCVDAO {

  // ******************** Static's Methods ********************

  public static String getAllShortFilmCVAttributes() {
    return " sfcv.curriculum_vitae, sfcv.short_film, sfcv.order_number";
  }

  public static String getShortFilmCVTableName() {
    return " ambre_fouvez.short_films_curriculum_vitae sfcv";
  }

}
