/**
 * @author Fouvez Dorian.
 */
package domaine.distinction_cinema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DistinctionCinemaImpl.class)
public interface DistinctionCinemaDTO {

  int getCinema();

  void setCinema(int cinema);

  int getDistinction();

  void setDistinction(int distinction);

}
