/**
 * @author Fouvez Dorian.
 */
package domaine.photographer;

import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.PatternChecker;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotographerImpl implements PhotographerDTO {

  private int id;
  private String name;
  private String instagram;

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
  public String getInstagram() {
    return instagram;
  }

  @Override
  public void setInstagram(String instagram) {
    if (instagram != null) {
      PatternChecker.checkInstagramPseudoPattern(instagram);
    }
    this.instagram = instagram;
  }

  @Override
  public String toString() {
    return "PhotographerImpl [id=" + id + ", name=" + name + ", instagram=" + instagram + "]";
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
    PhotographerImpl other = (PhotographerImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
