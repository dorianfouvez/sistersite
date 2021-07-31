/**
 * @author Fouvez Dorian.
 */
package services;

import domaine.tag_photo.TagPhotoDTO;

public interface TagPhotoDAO {

  TagPhotoDTO findById(int photoId, int tagId);

  TagPhotoDTO add(TagPhotoDTO tagPhoto);

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

}
