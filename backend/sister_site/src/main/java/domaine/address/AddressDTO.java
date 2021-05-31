/**
 * @author Fouvez Dorian.
 */
package domaine.adress;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = AdressImpl.class)
public interface AdressDTO {

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

}
