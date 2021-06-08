/**
 * @author Fouvez Dorian.
 */
package domaine.nationality;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NationalityImpl implements NationalityDTO {

  private int id;
  private String nationality;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getNationality() {
    return nationality;
  }

  @Override
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public void fullFillNationality(int id, String nationality) {
    setId(id);
    setNationality(nationality);
  }

  @Override
  public String toString() {
    return "NationalityImpl [id=" + id + ", nationality=" + nationality + "]";
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
    NationalityImpl other = (NationalityImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
