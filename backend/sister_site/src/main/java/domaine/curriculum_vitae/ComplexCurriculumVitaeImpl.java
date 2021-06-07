package domaine.curriculum_vitae;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import domaine.cinema.CinemaDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ShortFilmDTO;
import domaine.strength.StrengthDTO;
import domaine.training.TrainingDTO;
import domaine.user.UserDTO;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexCurriculumVitaeImpl implements ComplexCurriculumVitaeDTO {

  private int id;
  private String title;
  private UserDTO user;
  private ProfessionDTO profession;
  private String playingAge;
  private PhotoDTO backgroundPicture;
  private List<StrengthDTO> strengths;
  private List<TrainingDTO> trainings;
  private List<ShortFilmDTO> shortFilms;
  private List<CinemaDTO> cinemas;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public UserDTO getUser() {
    return user;
  }

  @Override
  public void setUser(UserDTO user) {
    this.user = user;
  }

  @Override
  public ProfessionDTO getProfession() {
    return profession;
  }

  @Override
  public void setProfession(ProfessionDTO profession) {
    this.profession = profession;
  }

  @Override
  public String getPlayingAge() {
    return playingAge;
  }

  @Override
  public void setPlayingAge(String playingAge) {
    String regex = "^[0-9]{2,3} - [0-9]{2,3}$";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(playingAge);
    if (!matcher.find()) {
      throw new BusinessException(
          "The facebook link is not matching a the pattern of facebook link.", Status.BAD_REQUEST);
    }
    this.playingAge = playingAge;
  }

  @Override
  public PhotoDTO getBackgroundPicture() {
    return backgroundPicture;
  }

  @Override
  public void setBackgroundPicture(PhotoDTO backgroundPicture) {
    this.backgroundPicture = backgroundPicture;
  }

  @Override
  public List<StrengthDTO> getStrengths() {
    return strengths;
  }

  @Override
  public void setStrengths(List<StrengthDTO> strengths) {
    this.strengths = strengths;
  }

  @Override
  public List<TrainingDTO> getTrainings() {
    return trainings;
  }

  @Override
  public void setTrainings(List<TrainingDTO> trainings) {
    this.trainings = trainings;
  }

  @Override
  public List<ShortFilmDTO> getShortFilms() {
    return shortFilms;
  }

  @Override
  public void setShortFilms(List<ShortFilmDTO> shortFilms) {
    this.shortFilms = shortFilms;
  }

  @Override
  public List<CinemaDTO> getCinemas() {
    return cinemas;
  }

  @Override
  public void setCinemas(List<CinemaDTO> cinemas) {
    this.cinemas = cinemas;
  }

  @Override
  public String toString() {
    String strengthsToString = "(";
    for (StrengthDTO strength : strengths) {
      strengthsToString += strength.toString();
    }
    strengthsToString += ")";

    String trainingsToString = "(";
    for (TrainingDTO training : trainings) {
      trainingsToString += training.toString();
    }
    trainingsToString += ")";

    String shortFilmsToString = "(";
    for (ShortFilmDTO shortFilm : shortFilms) {
      shortFilmsToString += shortFilm.toString();
    }
    shortFilmsToString += ")";

    String cinemasToString = "(";
    for (CinemaDTO cinema : cinemas) {
      cinemasToString += cinema.toString();
    }
    cinemasToString += ")";

    return "ComplexCurriculumVitaeImpl [id=" + id + ", title=" + title + ", user=" + user.toString()
        + ", profession=" + profession.toString() + ", playingAge=" + playingAge
        + ", backgroundPicture=" + backgroundPicture.toString() + ", strengths=" + strengthsToString
        + ", trainings=" + trainingsToString + ", shortFilms=" + shortFilmsToString + ", cinemas="
        + cinemasToString + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ComplexCurriculumVitaeImpl other = (ComplexCurriculumVitaeImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
