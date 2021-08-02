/**
 * @author Fouvez Dorian.
 */
package domaine.makeup_artist;

import java.util.List;

public interface MakeupArtistUCC {

  MakeupArtistDTO findById(int id);

  List<MakeupArtistDTO> getAll();

  MakeupArtistDTO add(MakeupArtistDTO makeupArtist);

  boolean nameAlreadyExist(String name);

}
