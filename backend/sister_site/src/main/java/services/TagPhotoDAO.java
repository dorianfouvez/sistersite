package services;

public interface TagPhotoDAO {

  // ******************** Static's Methods ********************

  public static String getAllTagPhotoAttributes() {
    return " tp.photo_id, tp.tag_id";
  }

  public static String getTagPhotoTableName() {
    return " ambre_fouvez.tags_photo tp";
  }

}
