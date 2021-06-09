/**
 * @author Fouvez Dorian.
 */
package domaine.strength;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrengthWithOrderImpl implements StrengthWithOrderDTO {

  private int id;
  private String label;
  private int orderNumber;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public void setLabel(String label) {
    this.label = label;
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
  public void fullFillStrengthWithOrder(int id, String label, int orderNumber) {
    setId(id);
    setLabel(label);
    setOrderNumber(orderNumber);
  }

  @Override
  public String toString() {
    return "StrengthWithOrderImpl [id=" + id + ", label=" + label + ", orderNumber=" + orderNumber
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    if (id != other.id) {
      return false;
    }
    if (orderNumber != other.orderNumber) {
      return false;
    }
    return true;
  }

}
