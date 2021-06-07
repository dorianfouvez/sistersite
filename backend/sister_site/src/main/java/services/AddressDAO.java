package services;

public interface AddressDAO {

  // ******************** Static's Methods ********************

  static String getAllAddressAttributes() {
    return " a.id, a.country, a.commune, a.postcode, a.street, a.building_number, a.unit_number";
  }

  static String getAddressTableName() {
    return " ambre_fouvez.addresses a";
  }

}
