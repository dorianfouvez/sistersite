package services;

import domaine.curriculum_vitae.CurriculumVitaeDTO;

public interface CurriculumVitaeDAO {

  CurriculumVitaeDTO findById(int id);

  void getFullInfosCurriculumVitae(int id);



  // ******************** Static's Methods ********************

  public static String getAllCurriculumVitaeAttributes() {
    return " cv.id, cv.title, cv.user_id, cv.profession, cv.playing_age, cv.background_picture";
  }

  public static String getCurriculumVitaeTableName() {
    return " ambre_fouvez.curriculum_vitae cv";
  }

}
