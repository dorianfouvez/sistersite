/**
 * @author Fouvez Dorian.
 */
package domaine.cinema;

import java.util.Calendar;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import api.utils.BusinessException;
import domaine.director.DirectorDTO;
import domaine.distinction.DistinctionDTO;
import domaine.role.RoleDTO;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComplexCinemaImpl implements ComplexCinemaDTO {

  private int id;
  private String title;
  private RoleDTO role;
  private int year;
  private int orderNumber;
  private List<DistinctionDTO> distinctions;
  private List<DirectorDTO> directors;

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
  public List<DistinctionDTO> getDistinctions() {
    return distinctions;
  }

  @Override
  public void setDistinctions(List<DistinctionDTO> distinctions) {
    this.distinctions = distinctions;
  }

  @Override
  public List<DirectorDTO> getDirectors() {
    return directors;
  }

  @Override
  public void setDirectors(List<DirectorDTO> directors) {
    this.directors = directors;
  }

  @Override
  public void fullFillComplexCinema(int id, String title, RoleDTO role, int year, int orderNumber,
      List<DistinctionDTO> distinctions, List<DirectorDTO> directors) {
    setId(id);
    setTitle(title);
    setRole(role);
    setYear(year);
    setOrderNumber(orderNumber);
    setDistinctions(distinctions);
    setDirectors(directors);
  }

  @Override
  public String toString() {
    String distinctionList = "{";
    for (DistinctionDTO distinctionDTO : distinctions) {
      distinctionList += distinctionDTO.toString() + " ";
    }
    distinctionList = "}";

    String directorList = "{";
    for (DirectorDTO directorDTO : directors) {
      directorList += directorDTO.toString() + " ";
    }
    directorList = "}";

    return "ShortFilmImpl [id=" + id + ", title=" + title + ", role=" + role.toString() + ", year="
        + year + ", orderNumber=" + orderNumber + ", distinctions=" + distinctionList
        + ", director=" + directorList + "]";
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
    ComplexCinemaImpl other = (ComplexCinemaImpl) obj;
    if (id != other.id) {
      return false;
    }
    if (orderNumber != other.orderNumber) {
      return false;
    }
    return true;
  }

}
