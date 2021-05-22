/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurriculumVitaeImpl implements CurriculumVitaeDTO {

  private int id;
  private String title;
  private int userId;
  private int profession;
  private String playingAge;
  private int backgroundPicture;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getProfession() {
    return profession;
  }

  public void setProfession(int profession) {
    this.profession = profession;
  }

  public String getPlayingAge() {
    return playingAge;
  }

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

  public int getBackgroundPicture() {
    return backgroundPicture;
  }

  public void setBackgroundPicture(int backgroundPicture) {
    this.backgroundPicture = backgroundPicture;
  }

  @Override
  public String toString() {
    return "CurriculumVitaeImpl [id=" + id + ", title=" + title + ", userId=" + userId
        + ", profession=" + profession + ", playingAge=" + playingAge + ", backgroundPicture="
        + backgroundPicture + "]";
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
    CurriculumVitaeImpl other = (CurriculumVitaeImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
