/**
 * @author Fouvez Dorian.
 */
package domaine.training_cv;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingCVImpl implements TrainingCVDTO {

  private int curriculumVitae;
  private int training;
  private int orderNumber;

  @Override
  public int getCurriculumVitae() {
    return curriculumVitae;
  }

  @Override
  public void setCurriculumVitae(int curriculumVitae) {
    this.curriculumVitae = curriculumVitae;
  }

  @Override
  public int getTraining() {
    return training;
  }

  @Override
  public void setTraining(int training) {
    this.training = training;
  }

  @Override
  public int getOrderNumber() {
    return orderNumber;
  }

  @Override
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public String toString() {
    return "TrainingCVImpl [curriculumVitae=" + curriculumVitae + ", training=" + training
        + ", orderNumber=" + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + curriculumVitae;
    result = prime * result + training;
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
    TrainingCVImpl other = (TrainingCVImpl) obj;
    if (curriculumVitae != other.curriculumVitae) {
      return false;
    }
    if (training != other.training) {
      return false;
    }
    return true;
  }

}
