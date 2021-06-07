package services;

public interface SizeDAO {

  // ******************** Static's Methods ********************

  static String getAllSizeAttributes() {
    return " s.id, s.size";
  }

  static String getSizeTableName() {
    return " ambre_fouvez.sizes s";
  }

}
