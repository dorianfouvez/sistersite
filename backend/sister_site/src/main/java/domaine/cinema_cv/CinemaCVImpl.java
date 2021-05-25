package domaine.cinema_cv;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaCVImpl implements CinemaCVDTO {

  private int curriculumVitae;
  private int cinema;
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
  public int getCinema() {
    return cinema;
  }

  @Override
  public void setCinema(int cinema) {
    this.cinema = cinema;
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
    return "CinemaCVImpl [curriculumVitae=" + curriculumVitae + ", cinema=" + cinema
        + ", orderNumber=" + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + curriculumVitae;
    result = prime * result + cinema;
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
    CinemaCVImpl other = (CinemaCVImpl) obj;
    if (curriculumVitae != other.curriculumVitae) {
      return false;
    }
    if (cinema != other.cinema) {
      return false;
    }
    return true;
  }

}
