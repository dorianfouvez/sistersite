/**
 * @author Fouvez Dorian.
 */
package domaine.strength;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = StrengthWithOrderImpl.class)
public interface StrengthWithOrderDTO extends StrengthDTO {

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

  void fullFillStrengthWithOrder(int id, String label, int orderNumber);

}
