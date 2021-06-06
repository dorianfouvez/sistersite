package services;

public interface ProfessionDAO {

  // ******************** Static's Methods ********************

  public static String getAllProfessionAttributes() {
    return " pr.id, pr.label";
  }

  public static String getProfessionTableName() {
    return " ambre_fouvez.professions pr";
  }

}
