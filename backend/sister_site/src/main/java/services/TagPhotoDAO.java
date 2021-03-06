/**
 * @author Fouvez Dorian.
 */
package services;

import java.util.List;
import domaine.tag_photo.TagPhotoDTO;

public interface TagPhotoDAO {

  TagPhotoDTO add(TagPhotoDTO tagPhoto);

  List<TagPhotoDTO> deleteAllFor(int photoId);

  List<TagPhotoDTO> findAllFor(int photoId);

  TagPhotoDTO findById(int photoId, int tagId);

  TagPhotoDTO update(TagPhotoDTO tagPhoto, int lastTagId);

  // ******************** Static's Methods ********************

  static String getAllTagPhotoAttributes() {
    return " " + getTagPhotoAbbreviation() + ".photo_id, " + getTagPhotoAbbreviation() + ".tag_id";
  }

  static String getTagPhotoAbbreviation() {
    return "tp";
  }

  static String getTagPhotoTableName() {
    return " ambre_fouvez.tags_photo " + getTagPhotoAbbreviation();
  }

  static String getTagPhotoTableNameWithoutAbbreviation() {
    return " ambre_fouvez.tags_photo";
  }

}
