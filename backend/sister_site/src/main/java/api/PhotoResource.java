/**
 * @author Fouvez Dorian.
 */
package api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.server.ContainerRequest;
import api.filters.Authorize;
import api.utils.PresentationException;
import api.utils.ResponseMaker;
import domaine.DomaineFactory;
import domaine.makeup_artist.MakeupArtistDTO;
import domaine.makeup_artist.MakeupArtistUCC;
import domaine.photo.AddPhotoInformationDTO;
import domaine.photo.AddPhotoInformationUCC;
import domaine.photo.PhotoDTO;
import domaine.photo.PhotoUCC;
import domaine.photographer.PhotographerDTO;
import domaine.photographer.PhotographerUCC;
import domaine.tag.TagDTO;
import domaine.tag.TagUCC;
import domaine.tag_photo.TagPhotoDTO;
import domaine.user.UserDTO;
import domaine.user.UserUCC;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import utils.Config;

@Singleton
@Path("/photos")
public class PhotoResource {

  @Inject
  private AddPhotoInformationUCC addPhotoInformationUCC;

  @Inject
  private DomaineFactory domaineFactory;

  @Inject
  private MakeupArtistUCC makeupArtistUCC;

  @Inject
  private PhotoUCC photoUCC;

  @Inject
  private PhotographerUCC photographerUCC;

  @Inject
  private TagUCC tagUCC;

  @Inject
  private UserUCC userUCC;

  /**
   * save all the photos from FormDataMultipart given and link it to the photograph and the tag. Must be Authorize.
   * 
   * @param request header with the token.
   * @param multiPart the FormDataMultipart with photo(s) inside.
   * @return Response.ok if everything is going fine.
   */
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Authorize
  public Response uploadPhotos(@Context ContainerRequest request,
      final FormDataMultiPart multiPart) {
    // Check all the make-up artists.
    List<Integer> makeupArtists = checkAndGetMakeupArtists(request);
    System.out.println("Make-up Artists: " + makeupArtists); // TODO remove

    // Check all the photographers.
    List<Integer> photographers = checkAndGetPhotographers(request);
    System.out.println("Photographers: " + photographers); // TODO remove

    // Check all the sharers.
    List<Integer> sharers = checkAndGetSharers(request);
    System.out.println("Sharers: " + sharers); // TODO remove

    // Check all the tags.
    List<Integer> tags = checkAndGetTags(request);
    System.out.println("Tags: " + tags); // TODO remove

    // Check all the dates.
    List<Timestamp> dates = checkAndGetDates(request);
    System.out.println("Dates: " + dates); // TODO remove

    if (makeupArtists.size() != photographers.size() || photographers.size() != sharers.size()
        || sharers.size() != tags.size() || tags.size() != dates.size()) {
      throw new PresentationException("Il manque des informations.", Status.BAD_REQUEST);
    }

    // Save all photo include.
    Map<String, List<FormDataBodyPart>> fields = multiPart.getFields();
    List<String> paths = new ArrayList<>();
    List<PhotoDTO> photos = new ArrayList<>();
    List<TagPhotoDTO> tagsPhotos = new ArrayList<>();
    int i = 0;
    for (String keyField : fields.keySet()) {
      List<FormDataBodyPart> values = fields.get(keyField);
      for (FormDataBodyPart formDataBodyPart : values) {
        String fileName = getFilenameOfImageFrom(formDataBodyPart);
        // System.out.println("Name : " + fileName);
        String uploadedFileLocation = Config.getProperty("PhotosPath") + fileName;
        String newName = getNameOfImageFrom(formDataBodyPart);
        if (newName != null) {
          checkName(newName, i + 1);
          uploadedFileLocation = Config.getProperty("PhotosPath") + newName;
          fileName = newName;
        } else {
          // Never Come here.
          checkName(fileName, i + 1);
        }
        // System.out.println("URL : " + System.getProperty("user.dir") + uploadedFileLocation);
        System.out.println("FileName: " + fileName + ", New Name: "
            + getNameOfImageFrom(formDataBodyPart) + "Make-up Artist: " + makeupArtists.get(i)
            + ", Photographer: " + photographers.get(i) + ", Sharer: " + sharers.get(i) + ", Tag: "
            + tags.get(i) + ", Date: " + dates.get(i)); // TODO remove

        // Save it.
        saveThePhotoInTheLocation(formDataBodyPart.getValueAs(InputStream.class),
            System.getProperty("user.dir") + uploadedFileLocation);
        photos.add(createPhotoDTOWith(uploadedFileLocation, fileName, makeupArtists.get(i),
            photographers.get(i), sharers.get(i), dates.get(i)));
        tagsPhotos.add(createFullFillTagPhoto(-1, tags.get(i)));
      }
      i++;
    }

    List<PhotoDTO> allPhotos = this.photoUCC.addPhotos(photos, tagsPhotos);
    for (PhotoDTO photoDTO : allPhotos) {
      paths.add(encodeFileToBase64Binary(photoDTO.getPicture()));
    }

    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("photos", paths);
  }

  @GET
  @Path("/addPhotoInformation")
  @Authorize
  public Response addPhotoInformation(@Context ContainerRequest request) {
    AddPhotoInformationDTO addPhotoInformation = this.addPhotoInformationUCC.get();
    return ResponseMaker.createResponseWithObjectNodeWith1PutPOJO("addPhotoInformation",
        addPhotoInformation);
  }



  // ******************** Public static's Methods ********************

  /**
   * Return a Image into a Base64 at the location given.
   * 
   * @param uploadedFileLocation the path to locate the access file.
   * @return a Base64 Image.
   */
  public static String encodeFileToBase64Binary(String uploadedFileLocation) {
    File file = new File(System.getProperty("user.dir") + uploadedFileLocation);

    String encodedfile = null;
    try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
      byte[] bytes = new byte[(int) file.length()];
      fileInputStreamReader.read(bytes);
      encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
    } catch (FileNotFoundException e) {
      throw new PresentationException("File Not Found.", e, Status.BAD_REQUEST);
    } catch (IOException e) {
      throw new PresentationException("IO Exception.", e, Status.BAD_REQUEST);
    }

    return "data:image/png;base64," + encodedfile;
  }

  /**
   * Transform the url (into Picture) from all the pictures into a Base64 Image.
   * 
   * @param photosList the list who contains all the photos to transform.
   */
  public static void transformAllURLOfThePhotosIntoBase64Image(List<PhotoDTO> photosList) {
    for (PhotoDTO photo : photosList) {
      if (photo != null && photo.getPicture().startsWith("/src")) {
        transformTheURLOfThePhotoIntoBase64Image(photo);
      }
    }
  }

  /**
   * Transform the url (into Picture) from the photo into a Base64 Image.
   * 
   * @param photo the photo who the picture need to be transform.
   */
  public static void transformTheURLOfThePhotoIntoBase64Image(PhotoDTO photo) {
    String encodstring = encodeFileToBase64Binary(photo.getPicture());
    photo.setPicture(encodstring);
  }



  // ******************** Private's Methods ********************// ******************** Private's Methods ********************

  private TagPhotoDTO createFullFillTagPhoto(int photoId, int tagId) {
    TagPhotoDTO tagPhotoDTO = this.domaineFactory.getTagPhotoDTO();

    tagPhotoDTO.setPhotoId(photoId);
    tagPhotoDTO.setTagId(tagId);

    return tagPhotoDTO;
  }

  private PhotoDTO createPhotoDTOWith(String picture, String name, int makeupArtist,
      int photographer, int sharer, Timestamp date) {
    PhotoDTO photo = domaineFactory.getPhotoDTO();

    photo.setName(name);
    photo.setPicture(picture);
    photo.setMakeupArtist(makeupArtist);
    photo.setPhotographer(photographer);
    photo.setSharer(sharer);
    photo.setDate(date);

    return photo;
  }

  private List<Timestamp> checkAndGetDates(ContainerRequest request) {
    List<Timestamp> dates = new ArrayList<Timestamp>();

    int numberDate = 1;
    for (String dateToConvert : request.getHeaderString("dates").split(",")) {
      Timestamp timestamp = null;
      if (dateToConvert != null && !dateToConvert.equals("")) {
        checkTimestampPattern("Date of the photo N°" + numberDate, dateToConvert);
        timestamp = Timestamp.valueOf(dateToConvert.replaceFirst("T", " "));
      }

      dates.add(timestamp);
      numberDate++;
    }

    return dates;
  }

  private List<Integer> checkAndGetMakeupArtists(ContainerRequest request) {
    List<Integer> makeupArtists = new ArrayList<Integer>();
    int numberMakeupArtist = 1;
    for (String integerToConvert : request.getHeaderString("makeupArtists").split(",")) {
      int makeupArtistId = Integer.valueOf(integerToConvert);

      MakeupArtistDTO makeupArtist = checkAndGetMakeupArtistId(makeupArtistId, numberMakeupArtist);

      makeupArtists.add(makeupArtist.getId());
      numberMakeupArtist++;
    }
    return makeupArtists;
  }

  private MakeupArtistDTO checkAndGetMakeupArtistId(int makeupArtistId, int numberMakeupArtist) {
    if (makeupArtistId < 0) {
      throw new PresentationException(
          "Make-up artist of the photo N°" + numberMakeupArtist + " doesn't exist",
          Status.BAD_REQUEST);
    }
    return this.makeupArtistUCC.findById(makeupArtistId);
  }

  private List<Integer> checkAndGetPhotographers(ContainerRequest request) {
    List<Integer> photographers = new ArrayList<Integer>();
    int numberPhotographer = 1;
    for (String integerToConvert : request.getHeaderString("photographers").split(",")) {
      int photographerId = Integer.valueOf(integerToConvert);

      PhotographerDTO photographer = checkAndGetPhotographerId(photographerId, numberPhotographer);

      photographers.add(photographer.getId());
      numberPhotographer++;
    }
    return photographers;
  }

  private PhotographerDTO checkAndGetPhotographerId(int photographerId, int numberPhotographer) {
    if (photographerId < 0) {
      throw new PresentationException(
          "Photographer of the photo N°" + numberPhotographer + " doesn't exist",
          Status.BAD_REQUEST);
    }
    return this.photographerUCC.findById(photographerId);
  }

  private List<Integer> checkAndGetSharers(ContainerRequest request) {
    List<Integer> sharers = new ArrayList<Integer>();
    int numberSharer = 1;
    for (String integerToConvert : request.getHeaderString("sharers").split(",")) {
      int sharerId = Integer.valueOf(integerToConvert);

      sharerId = checkAndGetSharerIdIfIsCorrect(sharerId, numberSharer, request);

      sharers.add(sharerId);
      numberSharer++;
    }
    return sharers;
  }

  /**
   * Check if the sharerId is correct or throw a exception.
   * 
   * @param sharerId the id to check.
   * @return the correct sharerId.
   */
  private int checkAndGetSharerIdIfIsCorrect(int sharerId, int photoNumber,
      ContainerRequest request) {
    if (sharerId < -1) {
      throw new PresentationException("Sharer of the photo N°" + photoNumber + " doesn't exist",
          Status.BAD_REQUEST);
    }
    if (sharerId == -1) {
      return sharerId;
    }
    if (sharerId == 1) {
      return ((UserDTO) request.getProperty("user")).getID();
    }

    // Normally doesn't go there.
    throw new PresentationException("Sharer of the photo N°" + photoNumber + " doesn't exist",
        Status.BAD_REQUEST);
  }

  private List<Integer> checkAndGetTags(ContainerRequest request) {
    List<Integer> tags = new ArrayList<Integer>();
    int numberTag = 1;
    for (String integerToConvert : request.getHeaderString("tags").split(",")) {
      int tagId = Integer.valueOf(integerToConvert);

      TagDTO tag = checkAndGetTagIdIfIsCorrect(tagId, numberTag);

      tags.add(tag.getId());
      numberTag++;
    }
    return tags;
  }

  private TagDTO checkAndGetTagIdIfIsCorrect(int tagId, int photoNumber) {
    if (tagId < 1) {
      throw new PresentationException("Tag of the photo N°" + photoNumber + " doesn't exist",
          Status.BAD_REQUEST);
    }
    return this.tagUCC.findById(tagId);
  }

  private void checkName(String name, int photoNumber) {
    if (this.photoUCC.nameAlreadyExist(name)) {
      throw new PresentationException(
          "The Name \"" + name + "\" of the photo N°" + photoNumber + " is already use.",
          Status.BAD_REQUEST);
    }
  }

  private void checkTimestampPattern(String name, String toVerify) {
    toVerify = toVerify.replaceFirst("T", " ");
    String timestampPattern = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$";
    Pattern pattern = Pattern.compile(timestampPattern, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(toVerify);
    if (!matcher.find()) {
      throw new PresentationException(name + " is not matching a Timestamp pattern.",
          Status.BAD_REQUEST);
    }
  }

  /**
   * return the filename of a image from a FormDataBodyPart.
   * 
   * @param formDataBodyPart contains the image.
   * @return the filename.
   */
  private String getFilenameOfImageFrom(FormDataBodyPart formDataBodyPart) {
    String filename = formDataBodyPart.getHeaders().get("Content-Disposition").get(0).split(";")[2];
    return filename.substring(11, filename.length() - 1);
  }

  /**
   * return the name of a image from a FormDataBodyPart.
   * 
   * @param formDataBodyPart contains the image.
   * @return the name.
   */
  private String getNameOfImageFrom(FormDataBodyPart formDataBodyPart) {
    String filename = formDataBodyPart.getHeaders().get("Content-Disposition").get(0).split(";")[1];
    return filename.substring(7, filename.length() - 1);
  }

  /**
   * Save (write) the uploaded photo to the new location.
   * 
   * @param photoToSave the photo to save.
   * @param uploadedFileLocation the new location to write the photo.
   */
  private void saveThePhotoInTheLocation(InputStream photoToSave, String uploadedFileLocation) {
    try (OutputStream out = new FileOutputStream(new File(uploadedFileLocation))) {
      int read = 0;
      byte[] bytes = new byte[1024];

      while ((read = photoToSave.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      out.flush();
      out.close(); // No necessary since close in the try-with-resource.
    } catch (IOException e) {
      throw new PresentationException("IO Exception.", e, Status.BAD_REQUEST);
    }
  }

}
