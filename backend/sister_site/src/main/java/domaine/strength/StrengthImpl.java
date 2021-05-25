/**
 * @author Fouvez Dorian.
 */
package domaine.strength;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrengthImpl implements StrengthDTO {

  private int id;
  private String label;

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
  public String toString() {
    return "StrengthImpl [id=" + id + ", label=" + label + "]";
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
    StrengthImpl other = (StrengthImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
