/**
 * @author Fouvez Dorian.
 */
package domaine.distinction;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistinctionImpl implements DistinctionDTO {

  private int id;
  private String name;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void fullFillDistinction(int id, String name) {
    setId(id);
    setName(name);
  }

  @Override
  public String toString() {
    return "DistinctionImpl [id=" + id + ", name=" + name + "]";
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
    DistinctionImpl other = (DistinctionImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
