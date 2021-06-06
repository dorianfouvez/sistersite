package services;

public interface StrengthCVDAO {

  // ******************** Static's Methods ********************

  public static String getAllStrengthCVAttributes() {
    return " stcv.curriculum_vitae, stcv.strength, stcv.order_number";
  }

  public static String getStrengthCVTableName() {
    return " ambre_fouvez.strengths_curriculum_vitae stcv";
  }

}
