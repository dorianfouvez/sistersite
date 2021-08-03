/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.sql.Timestamp;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PhotoImpl.class)
public interface PhotoDTO extends BasicPhotoDTO {

  int getMakeupArtist();

  void setMakeupArtist(int makeupArtist);

  int getPhotographer();

  void setPhotographer(int photographer);

  int getSharer();

  void setSharer(int sharer);

  void fullFillPhoto(int id, String name, String picture, int makeupArtist, int photographer,
      int sharer, Timestamp date);

}
