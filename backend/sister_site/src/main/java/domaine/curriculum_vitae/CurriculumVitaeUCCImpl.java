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
import services.UserDAO;

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
  private UserDAO userDAO;

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

    // TODO
    cv.setStrengths(this.strengthDAO.getAllStrengthWithOrderForCV(id));
    cv.setTrainings(this.trainingDAO.getAllTrainingWithOrderForCV(id));
    cv.setShortFilms(this.shortFilmDAO.getAllComplexShortFilmForCV(id));
    cv.setCinemas(this.cinemaDAO.getAllComplexCinemaForCV(id));

    /*
     * Object[] result = new Object[28]; int i = 0;
     * 
     * // CV CurriculumVitaeDTO cv = curriculumVitaeDAO.findById(id); if (cv == null) { dalservices.rollbackTransaction(); throw new
     * BusinessException("Curriculum Vitae doesn't exist", Status.BAD_REQUEST); } result[i++] = cv;
     * 
     * // Profession ProfessionDTO profession = null; if (profession == null) { dalservices.rollbackTransaction(); throw new
     * BusinessException("Profession doesn't exist", Status.BAD_REQUEST); } result[i++] = profession;
     * 
     * // Background Picture PhotoDTO backgroundPicture;
     * 
     * // User UserDTO user = userDAO.findById(cv.getUserId()); if (user == null) { dalservices.rollbackTransaction(); throw new
     * BusinessException("User doesn't exist", Status.BAD_REQUEST); }
     * 
     * // Profile Picture PhotoDTO profilePicture;
     * 
     * // Address AddressDTO address;
     * 
     * // Hair Color ColorDTO hairColor;
     * 
     * // Hair Size SizeDTO hairSize;
     * 
     * // Eye ColorDTO eye;
     * 
     * // First Nationality NationalityDTO firstNationality;
     * 
     * // Second Nationality NationalityDTO secondNationality;
     * 
     * // List of Strengths List<StrengthDTO> strengths;
     * 
     * // Link for Strengths List<StrengthCVDTO> strengthsCV;
     * 
     * // List of Trainings List<TrainingDTO> trainings;
     * 
     * // Link for Trainings List<TrainingCVDTO> trainingsCV;
     * 
     * // List of ShortFilms List<ShortFilmDTO> shortFilms;
     * 
     * // Link for ShortFilms List<ShortFilmCVDTO> shortFilmsCV;
     * 
     * // Roles for ShortFilms List<RoleDTO> rolesShortFilms;
     * 
     * // Directors for ShortFilms List<DirectorDTO> directorsShortFilms;
     * 
     * // Link for Director for ShortFilms List<DirectorShortFilmDTO> directorsShortFilmsLinks;
     * 
     * // Companies for ShortFilms List<CompanyDTO> companies;
     * 
     * // Link for Companies for ShortFilms List<CompanyShortFilmDTO> companiesShortFilms;
     * 
     * // List of Cinemas List<CinemaDTO> cinemas;
     * 
     * // Link for Cinemas List<CinemaCVDTO> cinemasCV;
     * 
     * // Roles for Cinemas List<RoleDTO> rolesCinemas;
     * 
     * // Directors for Cinemas List<DirectorDTO> directorsCinemas;
     * 
     * // Link for Director for Cinemas List<DirectorCinemaDTO> directorsCinemasLinks;
     * 
     * // Distinctions for Cinemas List<DistinctionDTO> distinctions;
     * 
     * // Link for Distinctions for Cinemas List<DistinctionCinemaDTO> distinctionsCinemas;
     */

    dalservices.commitTransaction();

    return cv;
  }

}
