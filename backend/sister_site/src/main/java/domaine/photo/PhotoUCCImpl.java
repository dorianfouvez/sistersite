/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.ArrayList;
import java.util.List;
import api.utils.BusinessException;
import domaine.tag_photo.TagPhotoDTO;
import domaine.user.UserDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
import services.DalServices;
import services.PhotoDAO;
import services.TagPhotoDAO;

public class PhotoUCCImpl implements PhotoUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private PhotoDAO photoDAO;

  @Inject
  private TagPhotoDAO tagPhotoDAO;



  @Override
  public List<PhotoDTO> addPhotos(List<PhotoDTO> photos, List<TagPhotoDTO> tagsPhotos) {
    if (photos.size() != tagsPhotos.size()) {
      throw new BusinessException("There is not the same amout of photo and tags",
          Status.BAD_REQUEST);
    }

    this.dalservices.startTransaction();
    List<PhotoDTO> addedPhotos = new ArrayList<>();
    for (int i = 0; i < photos.size(); i++) {
      PhotoDTO newPhoto = this.addPhoto(photos.get(i), i + 1);
      this.addTagPhoto(tagsPhotos.get(i), newPhoto, i);
      addedPhotos.add(newPhoto);
    }
    this.dalservices.commitTransaction();
    // throw new UnsupportedOperationException("Not implemented yet!");
    return addedPhotos;
  }

  @Override
  public PhotoDTO findById(int id) {
    this.dalservices.startTransaction();
    PhotoDTO photoDTO = this.photoDAO.findById(id);
    if (photoDTO == null) {
      this.dalservices.rollbackTransaction();
      throw new BusinessException("Photo doesn't exist", Status.BAD_REQUEST);
    }
    this.dalservices.commitTransaction();
    return photoDTO;
  }

  @Override
  public List<ComplexPhotoDTO> getAllFor(int userId) {
    List<ComplexPhotoDTO> photos = new ArrayList<>();
    this.dalservices.startTransaction();
    photos = this.photoDAO.getAllFullInfosPhotoFor(userId);
    this.dalservices.commitTransaction();
    return photos;
  }

  @Override
  public List<PhotoDTO> getBook(int tagId) {
    List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
    this.dalservices.startTransaction();
    photos = this.photoDAO.getBook(tagId);
    this.dalservices.commitTransaction();
    return photos;
  }

  @Override
  public boolean nameAlreadyExist(String name) {
    this.dalservices.startTransaction();
    PhotoDTO photoDTO = this.photoDAO.findByName(name);
    this.dalservices.commitTransaction();
    if (photoDTO == null) {
      return false;
    }
    return true;
  }

  @Override
  public boolean nameAlreadyExistButNotFor(int id, String name) {
    this.dalservices.startTransaction();
    PhotoDTO photoDTO = this.photoDAO.findByNameButNotFor(id, name);
    this.dalservices.commitTransaction();
    if (photoDTO == null) {
      return false;
    }
    return true;
  }

  @Override
  public PhotoDTO update(PhotoDTO photo, TagPhotoDTO tagPhoto, int lastTagId, UserDTO user) {
    this.dalservices.startTransaction();
    if (!this.photoDAO.isOwnPhoto(user, photo.getId()) || !user.isBoss()) {
      this.dalservices.rollbackTransaction();
      throw new BusinessException("You can't update this photo", Status.BAD_REQUEST);
    }

    PhotoDTO photoDTO = this.updatePhoto(photo);
    TagPhotoDTO tagPhotoDTO = this.updateTagPhoto(tagPhoto, lastTagId);
    this.dalservices.commitTransaction();
    return photoDTO;
  }



  // ******************** Private's Methods ********************

  private PhotoDTO addPhoto(PhotoDTO photo, int indexPhoto) {
    PhotoDTO newPhoto = this.photoDAO.add(photo);
    if (newPhoto == null) {
      this.dalservices.rollbackTransaction();
      if (indexPhoto == -1) {
        throw new BusinessException("Photo doesn't add", Status.BAD_REQUEST);
      } else {
        throw new BusinessException("Photo N°" + indexPhoto + " doesn't add", Status.BAD_REQUEST);
      }
    }
    return newPhoto;
  }

  private TagPhotoDTO addTagPhoto(TagPhotoDTO tagPhoto, PhotoDTO photoToLink, int indexPhoto) {
    tagPhoto.setPhotoId(photoToLink.getId());
    TagPhotoDTO newTagPhoto = this.tagPhotoDAO.add(tagPhoto);
    if (newTagPhoto == null) {
      this.dalservices.rollbackTransaction();
      if (indexPhoto == -1) {
        throw new BusinessException("Tag link of the photo doesn't add", Status.BAD_REQUEST);
      } else {
        throw new BusinessException("Tag link of the photo N°" + indexPhoto + " doesn't add",
            Status.BAD_REQUEST);
      }
    }
    return newTagPhoto;
  }

  private PhotoDTO updatePhoto(PhotoDTO photo) {
    PhotoDTO newPhoto = photoDAO.update(photo);
    if (newPhoto == null) {
      this.dalservices.rollbackTransaction();
      throw new BusinessException("Photo doesn't update", Status.BAD_REQUEST);
    }
    return newPhoto;
  }

  private TagPhotoDTO updateTagPhoto(TagPhotoDTO tagPhoto, int lastTagId) {
    if (tagPhoto.getTagId() == lastTagId) {
      return tagPhoto;
    }

    TagPhotoDTO newTagPhoto = this.tagPhotoDAO.update(tagPhoto, lastTagId);
    if (newTagPhoto == null) {
      this.dalservices.rollbackTransaction();
      throw new BusinessException("Tag link of the photo doesn't update", Status.BAD_REQUEST);
    }
    return newTagPhoto;
  }

}
