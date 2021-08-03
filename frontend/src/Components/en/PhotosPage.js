import { user_me } from "../..";
import { onError } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const PhotosPage = () => {
    let photosPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Photos</center></h1></div>
    <div id="photosPage"><div class="loader mx-auto"></div></div>`;
    let page = document.querySelector("#page");
    page.innerHTML = photosPage;

    let id = getTokenSessionData();
    fetch(API_URL + "photos/all", {
        method: "GET",
        headers: {
        "Content-Type": "application/json",
        "Authorization": id,
        },
    }).then((response) => {
        if (!response.ok) {
        return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onPhotos(data.photos));
    });
};

const onPhotos = (photos) => {
    console.log(photos)
    let photosPage = `<button id="addPhoto" class="btn btn-primary mt-2 mb-2 float-right">
        <i class="fas fa-plus"></i>
    </button>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Picture</th>
            <th>Name</th>
            <th>Make-up Artist</th>
            <th>Photographer</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>`;
        photos.forEach(photo => {
            photosPage += `<tr>
                <td><img id="photo" src="${photo.picture}" alt="${photo.name}" style="width:200px;"/></td>
                <td>${photo.name}</td>
                <td>${photo.makeupArtist.name}</td>
                <td>${photo.photographer.name}</td>
                <td>
                    <button type="submit" name="submitPhoto" class="btn btn-primary mt-2 mb-2" 
                    onClick="console.log('${photo.id}'); console.log('${user_me}');">
                        <i class="fas fa-pen"></i>
                    </button>
                </td>
            </tr>`;
        });
        photosPage += `
        </tbody>
    </table>`;

    let page = document.getElementById("photosPage");
    page.innerHTML = photosPage;

    document.getElementById("addPhoto").addEventListener('click', onAddPhoto);
};

const onAddPhoto = (e) => {
    e.preventDefault();
    RedirectUrl("/addPhoto");
};

export default PhotosPage;