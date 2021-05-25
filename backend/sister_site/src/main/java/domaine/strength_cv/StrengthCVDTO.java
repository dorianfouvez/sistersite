/**
 * @author Fouvez Dorian.
 */
package domaine.strength_cv;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = StrengthCVImpl.class)
public interface StrengthCVDTO {

  int getCurriculumVitae();

  void setCurriculumVitae(int curriculumVitae);

  int getStrength();

  void setStrength(int strength);

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

}
