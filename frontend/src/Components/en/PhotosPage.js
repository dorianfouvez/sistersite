import { user_me } from "../..";
import { onError } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

let photosAfterFetch = [];

const PhotosPage = () => {
    const user = getUserSessionData();
    if (!user) {
        RedirectUrl("/");
    } else {
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
    }
};

const onPhotos = (photos) => {
    photosAfterFetch = photos;
    let photosPage = `<button id="addPhoto" class="btn btn-primary mt-2 mb-2 float-right">
        <i class="fas fa-plus"></i>
    </button>
    <form id="photosForm">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Picture</th>
                <th>Name</th>
                <th>Make-up Artist</th>
                <th>Photographer</th>
                <th>Tag</th>
                <th>Date</th>
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
                    <td>${photo.tags[0].label}</td>`;
                    if(!photo.date) photosPage += `<td>Undefined</td>`;
                    else photosPage += `<td>${createTimeStamp(photo.date)}</td>`;
                    photosPage += `<td>
                        <button type="submit" name="submitPhoto" class="btn btn-primary mt-2 mb-2" value="${photo.id}">
                            <i class="fas fa-pen"></i>
                        </button>
                    </td>
                </tr>`;
            });
            photosPage += `
            </tbody>
        </table>
    </form>`;

    let page = document.getElementById("photosPage");
    page.innerHTML = photosPage;

    document.getElementById("addPhoto").addEventListener('click', onAddPhoto);
    document.getElementById("photosForm").addEventListener("submit", onSubmit);
};

const onAddPhoto = (e) => {
    e.preventDefault();
    RedirectUrl("/addPhoto");
};

const onSubmit = (e) => {
    e.preventDefault();
    let photo = photosAfterFetch.find(photo => photo.id == document.activeElement.value);
    user_me.photo = photo;
    RedirectUrl("/updatePhoto");
};

const createTimeStamp = (dateString) => {
    let Timestamp = new Date(dateString);
    let timeSplit = Timestamp.toLocaleString().split("/");
    return timeSplit[2].substr(0, 4) + "-" + timeSplit[1] + "-" + timeSplit[0] + " " + Timestamp.toLocaleTimeString();
};

export default PhotosPage;