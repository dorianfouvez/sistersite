/**
 * @author Fouvez Dorian.
 */
package domaine.training_cv;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = TrainingCVImpl.class)
public interface TrainingCVDTO {

  int getCurriculumVitae();

  void setCurriculumVitae(int curriculumVitae);

  int getTraining();

  void setTraining(int training);

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

}
