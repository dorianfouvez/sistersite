/**
 * @author Fouvez Dorian.
 */
package domaine.photo;

import domaine.DomaineFactory;
import jakarta.inject.Inject;
import services.DalServices;
import services.MakeupArtistDAO;
import services.PhotographerDAO;
import services.TagDAO;

public class AddPhotoInformationUCCImpl implements AddPhotoInformationUCC {

  @Inject
  private DalServices dalservices;

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private MakeupArtistDAO makeupArtistDAO;

  @Inject
  private PhotographerDAO photographerDAO;

  @Inject
  private TagDAO tagDAO;



  @Override
  public AddPhotoInformationDTO get() {
    AddPhotoInformationDTO addPhotoInformation = this.domaineFactory.getAddPhotoInformationDTO();

    dalservices.startTransaction();
    addPhotoInformation.setMakeupArtists(this.makeupArtistDAO.getAll());
    addPhotoInformation.setPhotographers(this.photographerDAO.getAll());
    addPhotoInformation.setTags(tagDAO.getAll());
    dalservices.commitTransaction();

    return addPhotoInformation;
  }

}
