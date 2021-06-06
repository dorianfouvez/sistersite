package services;

public interface TrainingDAO {

  // ******************** Static's Methods ********************

  public static String getAllTrainingAttributes() {
    return " tr.id, tr.start_year, tr.end_year, tr.label, tr.explanations";
  }

  public static String getTrainingTableName() {
    return " ambre_fouvez.trainings tr";
  }

}
