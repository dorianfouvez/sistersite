package services;

public interface DistinctionDAO {

  // ******************** Static's Methods ********************

  public static String getAllDistinctionAttributes() {
    return " ds.id, ds.name";
  }

  public static String getDistinctionTableName() {
    return " ambre_fouvez.distinctions ds";
  }

}
