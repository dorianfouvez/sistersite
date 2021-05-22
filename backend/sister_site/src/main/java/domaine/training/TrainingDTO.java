/**
 * @author Fouvez Dorian.
 */
package domaine.training;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = TrainingImpl.class)
public interface TrainingDTO {

  int getId();

  void setId(int id);

  int getStartYear();

  void setStartYear(int startYear);

  int getEndYear();

  void setEndYear(int endYear);

  String getLabel();

  void setLabel(String label);

  String getExplanations();

  void setExplanations(String explanations);

}
