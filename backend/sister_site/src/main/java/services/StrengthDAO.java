package services;

public interface StrengthDAO {

  // ******************** Static's Methods ********************

  static String getAllStrengthAttributes() {
    return " st.id, st.label";
  }

  static String getStrengthTableName() {
    return " ambre_fouvez.strengths st";
  }

}
