package services;

public interface DistinctionCinemaDAO {

  // ******************** Static's Methods ********************

  static String getAllDistinctionCinemaAttributes() {
    return " dsci.cinema_id, dsci.distinction_id";
  }

  static String getDistinctionCinemaTableName() {
    return " ambre_fouvez.distinctions_cinema dsci";
  }

}
