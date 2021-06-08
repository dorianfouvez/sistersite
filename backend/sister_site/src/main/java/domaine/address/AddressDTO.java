/**
 * @author Fouvez Dorian.
 */
package domaine.address;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AddressImpl.class)
public interface AddressDTO {

  int getId();

  void setId(int id);

  String getCountry();

  void setCountry(String country);

  String getCommune();

  void setCommune(String commune);

  String getPostcode();

  void setPostcode(String postcode);

  String getStreet();

  void setStreet(String street);

  String getBuildingNumber();

  void setBuildingNumber(String buildingNumber);

  String getUnitNumber();

  void setUnitNumber(String unitNumber);

  void fullFillAddress(int id, String country, String commune, String postcode, String street,
      String buildingNumber, String unitNumber);

}
