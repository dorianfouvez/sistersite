/**
 * @author Fouvez Dorian.
 */
package domaine.training;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    this.startYear = startYear;
  }

  @Override
  public int getEndYear() {
    return endYear;
  }

  @Override
  public void setEndYear(int endYear) {
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
