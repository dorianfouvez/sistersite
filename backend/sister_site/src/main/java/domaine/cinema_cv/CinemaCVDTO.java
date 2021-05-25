/**
 * @author Fouvez Dorian.
 */
package domaine.cinema_cv;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CinemaCVImpl.class)
public interface CinemaCVDTO {

  int getCurriculumVitae();

  void setCurriculumVitae(int curriculumVitae);

  int getCinema();

  void setCinema(int cinema);

  int getOrderNumber();

  void setOrderNumber(int orderNumber);

}
