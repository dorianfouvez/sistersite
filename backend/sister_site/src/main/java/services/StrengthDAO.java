package services;

public interface StrengthDAO {

  // ******************** Static's Methods ********************

  public static String getAllStrengthAttributes() {
    return " st.id, st.label";
  }

  public static String getStrengthTableName() {
    return " ambre_fouvez.strengths st";
  }

}
