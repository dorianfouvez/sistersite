package services;

import java.util.List;
import domaine.director.DirectorDTO;

public interface DirectorDAO {

  List<DirectorDTO> getAllDirectorForCinema(int cinemaId);

  List<DirectorDTO> getAllDirectorForShortFilm(int shortFilmId);



  // ******************** Static's Methods ********************

  static String getAllDirectorAttributes() {
    return " dr.id, dr.name";
  }

  static String getDirectorTableName() {
    return " ambre_fouvez.directors dr";
  }

}
