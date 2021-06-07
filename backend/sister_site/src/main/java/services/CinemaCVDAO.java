package services;

public interface CinemaCVDAO {

  // ******************** Static's Methods ********************

  static String getAllCinemaCVAttributes() {
    return " cicv.curriculum_vitae, cicv.cinema, cicv.order_number";
  }

  static String getCinemaCVTableName() {
    return " ambre_fouvez.cinemas_curriculum_vitae cicv";
  }

}
