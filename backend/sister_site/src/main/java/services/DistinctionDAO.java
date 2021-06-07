package services;

public interface DistinctionDAO {

  // ******************** Static's Methods ********************

  static String getAllDistinctionAttributes() {
    return " ds.id, ds.name";
  }

  static String getDistinctionTableName() {
    return " ambre_fouvez.distinctions ds";
  }

}
