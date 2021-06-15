/**
 * @author Fouvez Dorian.
 */
package services;

public interface TrainingCVDAO {

  // ******************** Static's Methods ********************

  static String getAllTrainingCVAttributes() {
    return " trcv.curriculum_vitae, trcv.training, trcv.order_number";
  }

  static String getTrainingCVTableName() {
    return " ambre_fouvez.trainings_curriculum_vitae trcv";
  }

}
