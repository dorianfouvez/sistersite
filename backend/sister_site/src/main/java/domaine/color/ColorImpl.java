/**
 * @author Fouvez Dorian.
 */
package domaine.color;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorImpl implements ColorDTO {

  private int id;
  private String color;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getColor() {
    return color;
  }

  @Override
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void fullFillColor(int id, String color) {
    setId(id);
    setColor(color);
  }

  @Override
  public String toString() {
    return "ColorImpl [id=" + id + ", color=" + color + "]";
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
    ColorImpl other = (ColorImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
