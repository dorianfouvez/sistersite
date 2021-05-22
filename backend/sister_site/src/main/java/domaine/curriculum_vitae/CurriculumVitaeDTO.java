/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CurriculumVitaeImpl.class)
public interface CurriculumVitaeDTO {

  int getId();

  void setId(int id);

  String getTitle();

  void setTitle(String title);

  int getUserId();

  void setUserId(int userId);

  int getProfession();

  void setProfession(int profession);

  String getPlayingAge();

  void setPlayingAge(String playingAge);

  int getBackgroundPicture();

  void setBackgroundPicture(int backgroundPicture);

}
