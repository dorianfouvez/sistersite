package domaine.strength_cv;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrengthCVImpl implements StrengthCVDTO {

  private int curriculumVitae;
  private int strength;
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
  public int getStrength() {
    return strength;
  }

  @Override
  public void setStrength(int strength) {
    this.strength = strength;
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
    return "StrengthCVImpl [curriculumVitae=" + curriculumVitae + ", strength=" + strength
        + ", orderNumber=" + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + curriculumVitae;
    result = prime * result + strength;
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
    StrengthCVImpl other = (StrengthCVImpl) obj;
    if (curriculumVitae != other.curriculumVitae) {
      return false;
    }
    if (strength != other.strength) {
      return false;
    }
    return true;
  }

}
