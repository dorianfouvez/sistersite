import { user_me } from "../..";
import { onError, onSuccess } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const UpdatePhotoPage = () => {
    const user = getUserSessionData();
    if (!user) {
        RedirectUrl("/");
    } else {
        let photoPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h1>Update Photo</h1></div>
        <div id="photosPage"><div class="loader mx-auto"></div></div>`;

        let page = document.querySelector("#page");
        page.innerHTML = photoPage;

        if(!user_me.photo){
            onError("There is no photo.");
        }else{
            let id = getTokenSessionData();
            fetch(API_URL + "photos/getAddPhotoInformation", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": id,
                },
            }).then((response) => {
                if (!response.ok) {
                    return response.text().then((err) => onError(err));
                } else return response.json().then((data) => 
                onShowPhoto(user_me.photo, data.addPhotoInformation.makeupArtists, 
                    data.addPhotoInformation.photographers, data.addPhotoInformation.tags));
            });
        }
    }
};

const onShowPhoto = (photo, makeupArtists, photographers, tags) => {
    let photoPage = `<form id="updatePhoto">
        <div class="d-flex justify-content-center mb-2">
            <img id="photo" src="${photo.picture}" alt="${photo.name}" style="width:200px;"/><br>
        </div>
        
        <div class="input-group mb-2">
            <label for="name" class="input-group-text">Name: </label>
            <input id="name" name="name" class="form-control" 
            value="${photo.name.substr(0, photo.name.lastIndexOf("."))}" required>
        </div>
        
        <div class="input-group mb-2">
            <label for="makeupArtist" class="input-group-text">Make-up Artist: </label>
            <select id="makeupArtist" class="form-control" name="makeupArtist">`;
            for (let i = 0; i < makeupArtists.length; i++) {
                photoPage += `<option value="${makeupArtists[i].id}" `;
                if(makeupArtists[i].id === photo.makeupArtist.id) photoPage += `selected`;
                photoPage +=`>${makeupArtists[i].name}</option>`;
            }
            photoPage += `
            </select>
        </div>

        <div class="input-group mb-2">
            <label for="photographer" class="input-group-text">Photographer: </label>
            <select id="photographer" class="form-control" name="photographer">`;
            for (let i = 0; i < photographers.length; i++) {
                photoPage += `<option value="${photographers[i].id}" `;
                if(photographers[i].id === photo.photographer.id) photoPage += `selected`;
                photoPage +=`>${photographers[i].name}</option>`;
            }
            photoPage += `
            </select>
        </div>

        <div class="input-group mb-2">
            <label for="sharer" class="input-group-text">Sharer: </label>
            <select id="sharer" class="form-control" name="sharer">
                <option value="-1">Everyone</option>
                <option value="1" selected>Moi</option>
            </select>
        </div>

        <div class="input-group mb-2">
            <label for="tag" class="input-group-text">Tag: </label>
            <select id="tag" class="form-control" name="tag">`;
            for (let i = 0; i < tags.length; i++) {
                photoPage += `<option value="${tags[i].id}" `;
                if(tags[i].id === photo.tags[0].id) photoPage += `selected`;
                photoPage +=`>${tags[i].label}</option>`;
            }
            photoPage += `
            </select>
        </div>

        <div class="input-group mb-2">
            <label for="date" class="input-group-text">Date: </label>
            <input id="date" type="datetime-local" class="form-control" value="`;
            if(photo.date) photoPage += createTimeStamp(photo.date);
            photoPage += `" name="date" step="1">
            <button id="dateButton" type="button" onClick="document.getElementById('date').value = '';">
                <i class="material-icons">delete</i>
            </button>
        </div>

        <input id="extension" type="hidden" value="${photo.name.substr(photo.name.lastIndexOf("."), photo.name.length)}">

        <input id="lastTagId" type="hidden" value="${photo.tags[0].id}">

        <input id="photoId" type="hidden" value="${photo.id}">

        <button type="submit" name="submitPhoto" class="btn btn-primary mt-2 mb-2">
            <i class="fas fa-save"></i>
        </button>
    </form>`;

    let page = document.getElementById("photosPage");
    page.innerHTML = photoPage;
    document.getElementById("updatePhoto").addEventListener("submit", onSubmit);
};

const onSubmit = (e) => {
    e.preventDefault();
    let photoId = document.getElementById("photoId").value;
    let photoDate = document.getElementById("date").value;
    if(!photoDate) photoDate = "null";
    let makeupArtistId = document.getElementById("makeupArtist").value;
    let photoName = document.getElementById("name").value;
    let photoNameExtension = document.getElementById("extension").value;
    photoName += photoNameExtension;
    let photographerId = document.getElementById("photographer").value;
    //let photoPicture = document.getElementById("photoId").value;
    let sharerId = document.getElementById("sharer").value;

    let tagId = document.getElementById("tag").value;
    let lastTagId = document.getElementById("lastTagId").value;

    let photo = {
        "date": photoDate,
        "id": photoId,
        "makeupArtist": makeupArtistId,
        "name": photoName,
        "photographer": photographerId,
        /*"picture": photoPicture,*/
        "sharer": sharerId,
    }
    
    console.log(photo);
    
    let id = getTokenSessionData();
    fetch(API_URL + "photos", {
      method: "PUT", 
      body: JSON.stringify(photo), 
      headers: {
        "Content-Type": "application/json",
        "Authorization": id,
        "tag_photo": tagId,
        "lastTagId": lastTagId,
      },
    })
    .then((response) => {
    if (!response.ok) {
        return response.text().then((err) => onError(err));
    }
    else
        return response.json().then((data) => onUpdate(data));
    });
};

const onUpdate = (data) => {
    let messageBoard = document.querySelector("#messageBoard");
    onSuccess("Photo updated", messageBoard);
    RedirectUrl("/photos");
};

const createTimeStamp = (dateString) => {
    let Timestamp = new Date(dateString);
    let timeSplit = Timestamp.toLocaleString().split("/");
    return timeSplit[2].substr(0, 4) + "-" + timeSplit[1] + "-" + timeSplit[0] + "T" + Timestamp.toLocaleTimeString();
};

export default UpdatePhotoPage;