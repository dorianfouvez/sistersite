/**
 * @author Fouvez Dorian.
 */
package domaine.training;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingImpl implements TrainingDTO {

  private int id;
  private int startYear;
  private int endYear;
  private String label;
  private String explanations;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getStartYear() {
    return startYear;
  }

  @Override
  public void setStartYear(int startYear) {
    if (startYear <= (Calendar.getInstance().get(Calendar.YEAR) - 120)) {
      throw new BusinessException("The start year is to old, you aren't born at this date normaly.",
          Status.BAD_REQUEST);
    }
    if (startYear >= (Calendar.getInstance().get(Calendar.YEAR) + 1)) {
      throw new BusinessException(
          "The start year is in the future, you haven't work on it till now.", Status.BAD_REQUEST);
    }
    this.startYear = startYear;
  }

  @Override
  public int getEndYear() {
    return endYear;
  }

  @Override
  public void setEndYear(int endYear) {
    if (startYear <= (Calendar.getInstance().get(Calendar.YEAR) - 120)) {
      throw new BusinessException("The end year is to old, you aren't born at this date normaly.",
          Status.BAD_REQUEST);
    }
    if (endYear <= (Calendar.getInstance().get(Calendar.YEAR) - 120)
        || endYear >= (Calendar.getInstance().get(Calendar.YEAR) + 1)) {
      throw new BusinessException("The end year is in the future, you haven't work on it till now.",
          Status.BAD_REQUEST);
    }
    this.endYear = endYear;
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
  public String getExplanations() {
    return explanations;
  }

  @Override
  public void setExplanations(String explanations) {
    this.explanations = explanations;
  }

  @Override
  public String toString() {
    return "TrainingImpl [id=" + id + ", startYear=" + startYear + ", endYear=" + endYear
        + ", label=" + label + ", explanations=" + explanations + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    TrainingImpl other = (TrainingImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
