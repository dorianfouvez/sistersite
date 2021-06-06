package services;

public interface SizeDAO {

  // ******************** Static's Methods ********************

  public static String getAllSizeAttributes() {
    return " s.id, s.size";
  }

  public static String getSizeTableName() {
    return " ambre_fouvez.sizes s";
  }

}
