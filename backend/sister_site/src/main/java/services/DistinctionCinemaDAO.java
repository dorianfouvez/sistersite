package services;

public interface DistinctionCinemaDAO {

  // ******************** Static's Methods ********************

  public static String getAllDistinctionCinemaAttributes() {
    return " dsci.cinema_id, dsci.distinction_id";
  }

  public static String getDistinctionCinemaTableName() {
    return " ambre_fouvez.distinctions_cinema dsci";
  }

}
