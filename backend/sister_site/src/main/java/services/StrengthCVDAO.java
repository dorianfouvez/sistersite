/**
 * @author Fouvez Dorian.
 */
package services;

public interface StrengthCVDAO {

  // ******************** Static's Methods ********************

  static String getAllStrengthCVAttributes() {
    return " stcv.curriculum_vitae, stcv.strength, stcv.order_number";
  }

  static String getStrengthCVTableName() {
    return " ambre_fouvez.strengths_curriculum_vitae stcv";
  }

}
