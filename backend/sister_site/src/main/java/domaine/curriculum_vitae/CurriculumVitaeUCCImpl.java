/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import api.utils.BusinessException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.CinemaDAO;
import services.CurriculumVitaeDAO;
import services.DalServices;
import services.ShortFilmDAO;
import services.StrengthDAO;
import services.TrainingDAO;

public class CurriculumVitaeUCCImpl implements CurriculumVitaeUCC {

  @Inject
  private CinemaDAO cinemaDAO;

  @Inject
  private CurriculumVitaeDAO curriculumVitaeDAO;

  @Inject
  private ShortFilmDAO shortFilmDAO;

  @Inject
  private StrengthDAO strengthDAO;

  @Inject
  private TrainingDAO trainingDAO;

  @Inject
  private DalServices dalservices;



  @Override
  public ComplexCurriculumVitaeDTO getFullCurriculumVitae(int id) {
    dalservices.startTransaction();
    ComplexCurriculumVitaeDTO cv = curriculumVitaeDAO.getFullInfosCurriculumVitae(id);
    if (cv == null || cv.getId() == 0) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Curriculum Vitae doesn't exist", Status.BAD_REQUEST);
    }

    cv.setStrengths(this.strengthDAO.getAllStrengthWithOrderForCV(id));
    cv.setTrainings(this.trainingDAO.getAllTrainingWithOrderForCV(id));
    cv.setShortFilms(this.shortFilmDAO.getAllComplexShortFilmForCV(id));
    cv.setCinemas(this.cinemaDAO.getAllComplexCinemaForCV(id));

    dalservices.commitTransaction();

    return cv;
  }

}
