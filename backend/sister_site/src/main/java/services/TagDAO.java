package services;

public interface TagDAO {

  // ******************** Static's Methods ********************

  public static String getAllTagAttributes() {
    return " ta.id, ta.label";
  }

  public static String getTagTableName() {
    return " ambre_fouvez.tags ta";
  }

}
