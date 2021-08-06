/**
 * @author Fouvez Dorian.
 */
package services;

public interface AddressDAO {

  // ******************** Static's Methods ********************

  static String getAllAddressAttributes() {
    return " " + getAddressAbbreviation() + ".id, " + getAddressAbbreviation() + ".country, "
        + getAddressAbbreviation() + ".commune, " + getAddressAbbreviation() + ".postcode, "
        + getAddressAbbreviation() + ".street, " + getAddressAbbreviation() + ".building_number, "
        + getAddressAbbreviation() + ".unit_number";
  }

  static String getAddressAbbreviation() {
    return "a";
  }

  static String getAddressTableName() {
    return " ambre_fouvez.addresses " + getAddressAbbreviation();
  }

  static String getUserAddressJoint() {
    return " LEFT JOIN" + AddressDAO.getAddressTableName() + " ON " + UserDAO.getUserAbbreviation()
        + ".address = " + AddressDAO.getAddressAbbreviation() + ".id";
  }

}
