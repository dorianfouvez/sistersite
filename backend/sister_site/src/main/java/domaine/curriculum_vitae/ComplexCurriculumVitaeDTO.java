/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domaine.cinema.CinemaDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ShortFilmDTO;
import domaine.strength.StrengthDTO;
import domaine.training.TrainingDTO;
import domaine.user.ComplexUserDTO;

@JsonDeserialize(as = ComplexCurriculumVitaeImpl.class)
public interface ComplexCurriculumVitaeDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  ComplexUserDTO getUser();

  void setUser(ComplexUserDTO user);

  ProfessionDTO getProfession();

  void setProfession(ProfessionDTO profession);

  String getPlayingAge();

  void setPlayingAge(String playingAge);

  PhotoDTO getBackgroundPicture();

  void setBackgroundPicture(PhotoDTO backgroundPicture);

  List<StrengthDTO> getStrengths();

  void setStrengths(List<StrengthDTO> strengths);

  List<TrainingDTO> getTrainings();

  void setTrainings(List<TrainingDTO> trainings);

  List<ShortFilmDTO> getShortFilms();

  void setShortFilms(List<ShortFilmDTO> shortFilms);

  List<CinemaDTO> getCinemas();

  void setCinemas(List<CinemaDTO> cinemas);

  void fullFillCV(int id, String title, ComplexUserDTO user, ProfessionDTO profession,
      String playingAge, PhotoDTO backgroundPicture, List<StrengthDTO> strengths,
      List<TrainingDTO> trainings, List<ShortFilmDTO> shortFilms, List<CinemaDTO> cinemas);

}
