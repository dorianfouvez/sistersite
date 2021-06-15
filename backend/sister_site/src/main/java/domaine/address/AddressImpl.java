/**
 * @author Fouvez Dorian.
 */
package domaine.address;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressImpl implements AddressDTO {

  private int id;
  private String country;
  private String commune;
  private String postcode;
  private String street;
  private String buildingNumber;
  private String unitNumber;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String getCommune() {
    return commune;
  }

  @Override
  public void setCommune(String commune) {
    this.commune = commune;
  }

  @Override
  public String getPostcode() {
    return postcode;
  }


  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  @Override
  public String getStreet() {
    return street;
  }

  @Override
  public void setStreet(String street) {
    this.street = street;
  }

  @Override
  public String getBuildingNumber() {
    return buildingNumber;
  }

  @Override
  public void setBuildingNumber(String buildingNumber) {
    this.buildingNumber = buildingNumber;
  }

  @Override
  public String getUnitNumber() {
    return unitNumber;
  }

  @Override
  public void setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;
  }

  @Override
  public void fullFillAddress(int id, String country, String commune, String postcode,
      String street, String buildingNumber, String unitNumber) {
    setId(id);
    setCountry(country);
    setCommune(commune);
    setPostcode(postcode);
    setStreet(street);
    setBuildingNumber(buildingNumber);
    setUnitNumber(unitNumber);
  }

  @Override
  public String toString() {
    return "AdressImpl [id=" + id + ", country=" + country + ", commune=" + commune + ", postcode="
        + postcode + ", street=" + street + ", buildingNumber=" + buildingNumber + ", unitNumber="
        + unitNumber + "]";
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
    AddressImpl other = (AddressImpl) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
