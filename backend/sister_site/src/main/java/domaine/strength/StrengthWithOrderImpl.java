/**
 * @author Fouvez Dorian.
 */
package domaine.strength;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrengthWithOrderImpl extends StrengthImpl implements StrengthWithOrderDTO {

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
  public void fullFillStrengthWithOrder(int id, String label, int orderNumber) {
    setId(id);
    setLabel(label);
    setOrderNumber(orderNumber);
  }

  @Override
  public String toString() {
    return "StrengthWithOrderImpl [id=" + getId() + ", label=" + getLabel() + ", orderNumber="
        + orderNumber + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getId();
    result = prime * result + orderNumber;
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
    StrengthWithOrderImpl other = (StrengthWithOrderImpl) obj;
    if (getId() != other.getId()) {
      return false;
    }
    if (orderNumber != other.orderNumber) {
      return false;
    }
    return true;
  }

}
