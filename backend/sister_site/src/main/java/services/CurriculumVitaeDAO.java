package services;

import domaine.curriculum_vitae.CurriculumVitaeDTO;

public interface CurriculumVitaeDAO {

  CurriculumVitaeDTO findById(int id);

  Object[] getFullInfosCurriculumVitae(int id);



  // ******************** Static's Methods ********************

  static String getAllCurriculumVitaeAttributes() {
    return " cv.id, cv.title, cv.user_id, cv.profession, cv.playing_age, cv.background_picture";
  }

  static String getCurriculumVitaeTableName() {
    return " ambre_fouvez.curriculum_vitae cv";
  }

}
