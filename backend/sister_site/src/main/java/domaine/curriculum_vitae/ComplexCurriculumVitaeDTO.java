/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.cinema.ComplexCinemaDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ComplexShortFilmDTO;
import domaine.strength.StrengthWithOrderDTO;
import domaine.training.TrainingWithOrderDTO;
import domaine.user.ComplexUserDTO;

@JsonDeserialize(as = ComplexCurriculumVitaeImpl.class)
public interface ComplexCurriculumVitaeDTO extends BasicCurriculumVitaeDTO {

  ComplexUserDTO getUser();

  void setUser(ComplexUserDTO user);

  ProfessionDTO getProfession();

  void setProfession(ProfessionDTO profession);

  PhotoDTO getBackgroundPicture();

  void setBackgroundPicture(PhotoDTO backgroundPicture);

  List<StrengthWithOrderDTO> getStrengths();

  void setStrengths(List<StrengthWithOrderDTO> strengths);

  List<TrainingWithOrderDTO> getTrainings();

  void setTrainings(List<TrainingWithOrderDTO> trainings);

  List<ComplexShortFilmDTO> getShortFilms();

  void setShortFilms(List<ComplexShortFilmDTO> shortFilms);

  List<ComplexCinemaDTO> getCinemas();

  void setCinemas(List<ComplexCinemaDTO> cinemas);

  void fullFillCV(int id, String title, ComplexUserDTO user, ProfessionDTO profession,
      String playingAge, PhotoDTO backgroundPicture, List<StrengthWithOrderDTO> strengths,
      List<TrainingWithOrderDTO> trainings, List<ComplexShortFilmDTO> shortFilms,
      List<ComplexCinemaDTO> cinemas);

}
