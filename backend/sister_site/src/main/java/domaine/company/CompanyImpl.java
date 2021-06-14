/**
 * @author Fouvez Dorian.
 */
package domaine.company;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyImpl implements CompanyDTO {

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
  public void fullFillCompany(int id, String name) {
    setId(id);
    setName(name);
  }

  @Override
  public String toString() {
    return "CompanyImpl [id=" + id + ", name=" + name + "]";
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
    CompanyImpl other = (CompanyImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
