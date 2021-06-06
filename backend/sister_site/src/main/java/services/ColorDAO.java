package services;

public interface ColorDAO {

  // ******************** Static's Methods ********************

  public static String getAllColorAttributes() {
    return " c.id, c.color";
  }

  public static String getColorTableName() {
    return " ambre_fouvez.colours c";
  }

}
