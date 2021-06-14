/**
 * @author Fouvez Dorian.
 */
package domaine.training;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingWithOrderImpl extends TrainingImpl implements TrainingWithOrderDTO {

  private int orderNumber;

  @Override
  public int getOrderNumber() {
    return orderNumber;
  }

  @Override
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public void fullFillTrainingWithOrder(int id, int startYear, int endYear, String label,
      String explanations, int orderNumber) {
    setId(id);
    setStartYear(startYear);
    setEndYear(endYear);
    setLabel(label);
    setExplanations(explanations);
    setOrderNumber(orderNumber);
  }

  @Override
  public String toString() {
    return "TrainingWithOrderImpl [id=" + getId() + ", startYear=" + getStartYear() + ", endYear="
        + getEndYear() + ", label=" + getLabel() + ", explanations=" + getExplanations()
        + ", oderNumber=" + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + super.getId();
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
    TrainingWithOrderImpl other = (TrainingWithOrderImpl) obj;
    if (getId() != other.getId()) {
      return false;
    }
    if (orderNumber != other.orderNumber) {
      return false;
    }
    return true;
  }

}
