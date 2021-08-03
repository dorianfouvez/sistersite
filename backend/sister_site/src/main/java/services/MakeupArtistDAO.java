/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.makeup_artist.MakeupArtistDTO;

public interface MakeupArtistDAO {

  MakeupArtistDTO findById(int id);

  MakeupArtistDTO findByName(String name);

  MakeupArtistDTO findByInstagram(String instagram);

  List<MakeupArtistDTO> getAll();

  MakeupArtistDTO add(MakeupArtistDTO makeupArtist);



  // ******************** Static's Methods ********************

  static String getAllMakeupArtistAttributes() {
    return " " + getMakeupArtistAbbreviation() + ".id, " + getMakeupArtistAbbreviation() + ".name, "
        + getMakeupArtistAbbreviation() + ".instagram";
  }

  static String getMakeupArtistAbbreviation() {
    return "m";
  }

  static String getMakeupArtistTableName() {
    return getMakeupArtistTableNameWithoutAbbreviation() + " " + getMakeupArtistAbbreviation();
  }

  static String getMakeupArtistTableNameWithoutAbbreviation() {
    return " ambre_fouvez.makeup_artists";
  }

}
