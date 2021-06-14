package services;

import java.util.List;
import domaine.distinction.DistinctionDTO;

public interface DistinctionDAO {

  List<DistinctionDTO> getAllDistinctionForCinema(int cinemaId);



  // ******************** Static's Methods ********************

  static String getAllDistinctionAttributes() {
    return " ds.id, ds.name";
  }

  static String getDistinctionTableName() {
    return " ambre_fouvez.distinctions ds";
  }

}
