package services;

import java.util.List;
import domaine.cinema.ComplexCinemaDTO;

public interface CinemaDAO {

  List<ComplexCinemaDTO> getAllComplexCinemaForCV(int cvId);



  // ******************** Static's Methods ********************

  static String getAllCinemaAttributes() {
    return " ci.id, ci.title, ci.role, ci.year";
  }

  static String getCinemaTableName() {
    return " ambre_fouvez.cinemas ci";
  }

}
