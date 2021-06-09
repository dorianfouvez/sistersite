/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import domaine.cinema.ComplexCinemaDTO;
import domaine.photo.PhotoDTO;
import domaine.profession.ProfessionDTO;
import domaine.short_film.ComplexShortFilmDTO;
import domaine.strength.StrengthWithOrderDTO;
import domaine.training.TrainingWithOrderDTO;
import domaine.user.ComplexUserDTO;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexCurriculumVitaeImpl implements ComplexCurriculumVitaeDTO {

  private int id;
  private String title;
  private ComplexUserDTO user;
  private ProfessionDTO profession;
  private String playingAge;
  private PhotoDTO backgroundPicture;
  private List<StrengthWithOrderDTO> strengths;
  private List<TrainingWithOrderDTO> trainings;
  private List<ComplexShortFilmDTO> shortFilms;
  private List<ComplexCinemaDTO> cinemas;

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
  public ComplexUserDTO getUser() {
    return user;
  }

  @Override
  public void setUser(ComplexUserDTO user) {
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
  public List<StrengthWithOrderDTO> getStrengths() {
    return strengths;
  }

  @Override
  public void setStrengths(List<StrengthWithOrderDTO> strengths) {
    this.strengths = strengths;
  }

  @Override
  public List<TrainingWithOrderDTO> getTrainings() {
    return trainings;
  }

  @Override
  public void setTrainings(List<TrainingWithOrderDTO> trainings) {
    this.trainings = trainings;
  }

  @Override
  public List<ComplexShortFilmDTO> getShortFilms() {
    return shortFilms;
  }

  @Override
  public void setShortFilms(List<ComplexShortFilmDTO> shortFilms) {
    this.shortFilms = shortFilms;
  }

  @Override
  public List<ComplexCinemaDTO> getCinemas() {
    return cinemas;
  }

  @Override
  public void setCinemas(List<ComplexCinemaDTO> cinemas) {
    this.cinemas = cinemas;
  }

  @Override
  public void fullFillCV(int id, String title, ComplexUserDTO user, ProfessionDTO profession,
      String playingAge, PhotoDTO backgroundPicture, List<StrengthWithOrderDTO> strengths,
      List<TrainingWithOrderDTO> trainings, List<ComplexShortFilmDTO> shortFilms,
      List<ComplexCinemaDTO> cinemas) {

    setId(id);
    setTitle(title);
    setUser(user);
    setProfession(profession);
    setPlayingAge(playingAge);
    setBackgroundPicture(backgroundPicture);
    setStrengths(strengths);
    setTrainings(trainings);
    setShortFilms(shortFilms);
    setCinemas(cinemas);
  }

  @Override
  public String toString() {
    String strengthsToString = "(";
    for (StrengthWithOrderDTO strength : strengths) {
      strengthsToString += strength.toString();
    }
    strengthsToString += ")";

    String trainingsToString = "(";
    for (TrainingWithOrderDTO training : trainings) {
      trainingsToString += training.toString();
    }
    trainingsToString += ")";

    String shortFilmsToString = "(";
    for (ComplexShortFilmDTO shortFilm : shortFilms) {
      shortFilmsToString += shortFilm.toString();
    }
    shortFilmsToString += ")";

    String cinemasToString = "(";
    for (ComplexCinemaDTO cinema : cinemas) {
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
