/**
 * @author Fouvez Dorian.
 */
package domaine.short_film;

import java.util.Calendar;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import domaine.company.CompanyDTO;
import domaine.director.DirectorDTO;
import domaine.role.RoleDTO;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexShortFilmImpl implements ComplexShortFilmDTO {

  private int id;
  private String title;
  private RoleDTO role;
  private int year;
  private int orderNumber;
  private CompanyDTO company;
  private DirectorDTO director;

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
  public RoleDTO getRole() {
    return role;
  }

  @Override
  public void setRole(RoleDTO role) {
    this.role = role;
  }

  @Override
  public int getYear() {
    return year;
  }

  @Override
  public void setYear(int year) {
    if (year <= (Calendar.getInstance().get(Calendar.YEAR) - 120)) {
      throw new BusinessException("The year is to old, you aren't born at this date normaly.",
          Status.BAD_REQUEST);
    }
    if (year >= (Calendar.getInstance().get(Calendar.YEAR) + 1)) {
      throw new BusinessException("The year is in the future, you haven't work on it till now.",
          Status.BAD_REQUEST);
    }
    this.year = year;
  }

  @Override
  public int getOrderNumber() {
    return orderNumber;
  }

  @Override
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  @Override
  public CompanyDTO getCompany() {
    return company;
  }

  @Override
  public void setCompany(CompanyDTO company) {
    this.company = company;
  }

  @Override
  public DirectorDTO getDirector() {
    return director;
  }

  @Override
  public void setDirector(DirectorDTO director) {
    this.director = director;
  }

  @Override
  public String toString() {
    return "ShortFilmImpl [id=" + id + ", title=" + title + ", role=" + role.toString() + ", year="
        + year + ", orderNumber=" + orderNumber + ", company=" + company.toString() + ", director="
        + director.toString() + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + orderNumber;
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
    ComplexShortFilmImpl other = (ComplexShortFilmImpl) obj;
    if (id != other.id) {
      return false;
    }
    if (orderNumber != other.orderNumber) {
      return false;
    }
    return true;
  }

}
