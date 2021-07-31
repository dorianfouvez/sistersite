/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.makeup_artist.MakeupArtistDTO;

public interface MakeupArtistDAO {

  MakeupArtistDTO findById(int id);

  List<MakeupArtistDTO> getAll();



  // ******************** Static's Methods ********************

  static String getAllMakeupArtistAttributes() {
    return " " + getMakeupArtistAbbreviation() + ".id, " + getMakeupArtistAbbreviation() + ".name, "
        + getMakeupArtistAbbreviation() + ".instagram";
  }

  static String getMakeupArtistAbbreviation() {
    return "m";
  }

  static String getMakeupArtistTableName() {
    return " ambre_fouvez.makeup_artists " + getMakeupArtistAbbreviation();
  }

}
