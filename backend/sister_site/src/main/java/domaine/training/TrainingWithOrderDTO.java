/**
 * @author Fouvez Dorian.
 */
package domaine.training;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = TrainingWithOrderImpl.class)
public interface TrainingWithOrderDTO extends TrainingDTO {

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

  void fullFillTrainingWithOrder(int id, int startYear, int endYear, String label,
      String explanations, int orderNumber);

}
