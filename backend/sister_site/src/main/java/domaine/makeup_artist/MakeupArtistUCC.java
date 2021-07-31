/**
 * @author Fouvez Dorian.
 */
package domaine.makeup_artist;

import java.util.List;

public interface MakeupArtistUCC {

  MakeupArtistDTO findById(int id);

  List<MakeupArtistDTO> getAll();

}
