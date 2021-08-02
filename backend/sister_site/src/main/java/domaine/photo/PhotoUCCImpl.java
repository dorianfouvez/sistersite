/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import java.util.ArrayList;
import java.util.List;
import api.utils.BusinessException;
import domaine.tag_photo.TagPhotoDTO;
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
  public PhotoDTO findById(int id) {
    dalservices.startTransaction();
    PhotoDTO photoDTO = photoDAO.findById(id);
    if (photoDTO == null) {
      dalservices.rollbackTransaction();
      throw new BusinessException("Photo doesn't exist", Status.BAD_REQUEST);
    }
    dalservices.commitTransaction();
    return photoDTO;
  }

  @Override
  public boolean nameAlreadyExist(String name) {
    dalservices.startTransaction();
    PhotoDTO photoDTO = photoDAO.findByName(name);
    dalservices.commitTransaction();
    if (photoDTO == null) {
      return false;
    }
    return true;
  }

  @Override
  public List<PhotoDTO> addPhotos(List<PhotoDTO> photos, List<TagPhotoDTO> tagsPhotos) {
    if (photos.size() != tagsPhotos.size()) {
      throw new BusinessException("There is not the same amout of photo and tags",
          Status.BAD_REQUEST);
    }

    dalservices.startTransaction();
    List<PhotoDTO> addedPhotos = new ArrayList<>();
    for (int i = 0; i < photos.size(); i++) {
      PhotoDTO newPhoto = addPhoto(photos.get(i), i + 1);
      addTagPhoto(tagsPhotos.get(i), newPhoto, i);
      addedPhotos.add(newPhoto);
    }
    dalservices.commitTransaction();
    // throw new UnsupportedOperationException("Not implemented yet!");
    return addedPhotos;
  }

  @Override
  public List<PhotoDTO> getBook(int tagId) {
    List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
    dalservices.startTransaction();
    photos = this.photoDAO.getBook(tagId);
    dalservices.commitTransaction();
    return photos;
  }



  // ******************** Private's Methods ********************

  private PhotoDTO addPhoto(PhotoDTO photo, int indexPhoto) {
    PhotoDTO newPhoto = this.photoDAO.add(photo);
    if (newPhoto == null) {
      dalservices.rollbackTransaction();
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
      dalservices.rollbackTransaction();
      if (indexPhoto == -1) {
        throw new BusinessException("Tag link of the photo doesn't add", Status.BAD_REQUEST);
      } else {
        throw new BusinessException("Tag link of the photo N°" + indexPhoto + " doesn't add",
            Status.BAD_REQUEST);
      }
    }
    return newTagPhoto;
  }

}
