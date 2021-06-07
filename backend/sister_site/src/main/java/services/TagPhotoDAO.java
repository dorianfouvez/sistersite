package services;

public interface TagPhotoDAO {

  // ******************** Static's Methods ********************

  static String getAllTagPhotoAttributes() {
    return " tp.photo_id, tp.tag_id";
  }

  static String getTagPhotoTableName() {
    return " ambre_fouvez.tags_photo tp";
  }

}
