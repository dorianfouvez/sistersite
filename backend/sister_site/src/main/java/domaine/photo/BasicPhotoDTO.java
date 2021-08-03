package domaine.photo;

import java.sql.Timestamp;

public interface BasicPhotoDTO {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  String getPicture();

  void setPicture(String picture);

  Timestamp getDate();

  void setDate(Timestamp date);

}
