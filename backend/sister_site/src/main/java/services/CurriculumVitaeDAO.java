package services;

import domaine.curriculum_vitae.CurriculumVitaeDTO;

public interface CurriculumVitaeDAO {

  CurriculumVitaeDTO findById(int id);

  void getFullInfosCurriculumVitae(int id);

}
