/**
 * @author Fouvez Dorian.
 */
package domaine.curriculum_vitae;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CurriculumVitaeImpl.class)
public interface CurriculumVitaeDTO extends BasicCurriculumVitaeDTO {

  int getUserId();

  void setUserId(int userId);

  int getProfession();

  void setProfession(int profession);

  int getBackgroundPicture();

  void setBackgroundPicture(int backgroundPicture);

}
