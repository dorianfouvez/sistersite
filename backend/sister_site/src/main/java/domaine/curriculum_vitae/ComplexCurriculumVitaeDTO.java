package domaine.curriculum_vitae;

import java.util.List;
import domaine.cinema.CinemaDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ShortFilmDTO;
import domaine.strength.StrengthDTO;
import domaine.training.TrainingDTO;
import domaine.user.UserDTO;

public interface ComplexCurriculumVitaeDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  UserDTO getUser();

  void setUser(UserDTO user);

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

}
