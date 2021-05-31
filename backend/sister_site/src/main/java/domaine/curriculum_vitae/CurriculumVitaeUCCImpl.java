package domaine.curriculum_vitae;

import java.util.List;
import api.utils.BusinessException;
import domaine.address.AddressDTO;
import domaine.cinema.CinemaDTO;
import domaine.cinema_cv.CinemaCVDTO;
import domaine.color.ColorDTO;
import domaine.company.CompanyDTO;
import domaine.company_short_film.CompanyShortFilmDTO;
import domaine.director.DirectorDTO;
import domaine.director_cinema.DirectorCinemaDTO;
import domaine.director_short_film.DirectorShortFilmDTO;
import domaine.distinction.DistinctionDTO;
import domaine.distinction_cinema.DistinctionCinemaDTO;
import domaine.nationality.NationalityDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.role.RoleDTO;
import domaine.short_film.ShortFilmDTO;
import domaine.short_film_cv.ShortFilmCVDTO;
import domaine.size.SizeDTO;
import domaine.strength.StrengthDTO;
import domaine.strength_cv.StrengthCVDTO;
import domaine.training.TrainingDTO;
import domaine.training_cv.TrainingCVDTO;
import domaine.user.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.CurriculumVitaeDAO;
import services.DalServices;
import services.UserDAO;

public class CurriculumVitaeUCCImpl implements CurriculumVitaeUCC {

  @Inject
  private UserDAO userDAO;

  @Inject
  private CurriculumVitaeDAO curriculumVitaeDAO;

  @Inject
  private DalServices dalservices;



  @Override
  public void getFullCurriculumVitae(int id) {
    dalservices.startTransaction();
    Object[] result = new Object[28];
    int i = 0;

    // CV
    CurriculumVitaeDTO cv = curriculumVitaeDAO.findById(id);
    if (cv == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Curriculum Vitae doesn't exist", Status.BAD_REQUEST);
    }
    result[i++] = cv;

    // Profession
    ProfessionDTO profession = null;
    if (profession == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Profession doesn't exist", Status.BAD_REQUEST);
    }
    result[i++] = profession;

    // Background Picture
    PhotoDTO backgroundPicture;

    // User
    UserDTO user = userDAO.findById(cv.getUserId());
    if (user == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("User doesn't exist", Status.BAD_REQUEST);
    }

    // Profile Picture
    PhotoDTO profilePicture;

    // Address
    AddressDTO address;

    // Hair Color
    ColorDTO hairColor;

    // Hair Size
    SizeDTO hairSize;

    // Eye
    ColorDTO eye;

    // First Nationality
    NationalityDTO firstNationality;

    // Second Nationality
    NationalityDTO secondNationality;

    // List of Strengths
    List<StrengthDTO> strengths;

    // Link for Strengths
    List<StrengthCVDTO> strengthsCV;

    // List of Trainings
    List<TrainingDTO> trainings;

    // Link for Trainings
    List<TrainingCVDTO> trainingsCV;

    // List of ShortFilms
    List<ShortFilmDTO> shortFilms;

    // Link for ShortFilms
    List<ShortFilmCVDTO> shortFilmsCV;

    // Roles for ShortFilms
    List<RoleDTO> rolesShortFilms;

    // Directors for ShortFilms
    List<DirectorDTO> directorsShortFilms;

    // Link for Director for ShortFilms
    List<DirectorShortFilmDTO> directorsShortFilmsLinks;

    // Companies for ShortFilms
    List<CompanyDTO> companies;

    // Link for Companies for ShortFilms
    List<CompanyShortFilmDTO> companiesShortFilms;

    // List of Cinemas
    List<CinemaDTO> cinemas;

    // Link for Cinemas
    List<CinemaCVDTO> cinemasCV;

    // Roles for Cinemas
    List<RoleDTO> rolesCinemas;

    // Directors for Cinemas
    List<DirectorDTO> directorsCinemas;

    // Link for Director for Cinemas
    List<DirectorCinemaDTO> directorsCinemasLinks;

    // Distinctions for Cinemas
    List<DistinctionDTO> distinctions;

    // Link for Distinctions for Cinemas
    List<DistinctionCinemaDTO> distinctionsCinemas;

    dalservices.commitTransaction();
  }

}
