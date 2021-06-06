package services;

public interface AddressDAO {

  // ******************** Static's Methods ********************

  public static String getAllAddressAttributes() {
    return " a.id, a.country, a.commune, a.postcode, a.street, a.building_number, a.unit_number";
  }

  public static String getAddressTableName() {
    return " ambre_fouvez.addresses a";
  }

}
