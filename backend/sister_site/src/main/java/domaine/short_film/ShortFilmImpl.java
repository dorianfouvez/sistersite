/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortFilmImpl implements ShortFilmDTO {

  private int id;
  private String title;
  private int role;
  private int year;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int getRole() {
    return role;
  }

  @Override
  public void setRole(int role) {
    this.role = role;
  }

  @Override
  public int getYear() {
    return year;
  }

  @Override
  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "ShortFilmImpl [id=" + id + ", title=" + title + ", role=" + role + ", year=" + year
        + "]";
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
    ShortFilmImpl other = (ShortFilmImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
