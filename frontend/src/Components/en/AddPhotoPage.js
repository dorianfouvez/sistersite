import { user_me } from "../..";
import { fixToBottomFooter, onError, unfixToBottomFooter } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const AddPhotoPage = () => {
    const user = getUserSessionData();
    if (!user || !user.isBoss) {
        RedirectUrl("/");
    } else {
        fixToBottomFooter();
        let addPhotoPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Add Photo(s)</center></h1></div>
        <form id="addPhotoForm" enctype="multipart/form-data">
            <input type="file" id="files" name="files" multiple><span id="loading"></span>
            <input id="savedFiles" type="file" hidden multiple>
            <div id="showImg" class="row"></div>
            <button type="submit" name="submitPhoto" class="btn btn-primary mt-2 mb-2"><i class="fas fa-save"></i></button>
        </form>`;

        let page = document.querySelector("#page");
        page.innerHTML = addPhotoPage;
        document.getElementById("addPhotoForm").addEventListener("submit", onSubmit);
        document.getElementById("files").addEventListener("change", onUpload);
    }
};

const onUpload = (e) => {
    document.getElementById("loading").innerHTML = `<div class="loader"></div>`;
    let id = getTokenSessionData();
    fetch(API_URL + "photos/addPhotoInformation", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": id,
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onShowImage(e, data.addPhotoInformation.makeupArtists, data.addPhotoInformation.photographers, data.addPhotoInformation.tags));
    });
};

const onShowImage = (e, makeupArtists, photographers, tags) => {
    let files = e.target.files;
    let hello = document.getElementById('savedFiles');
    if(!hello.savedFiles) hello.savedFiles = [];
    
    // Remove loading circle.
    document.getElementById('loading').innerHTML = "";

    // Add visuel and 
    for(let i = 0; i < files.length; i++){
        let reader = new FileReader();
        reader.onloadend = function() {//document.getElementById('savedFiles').savedFiles.splice(${hello.savedFiles.length}, 1);
            let fileName = files[i].name.substr(0, files[i].name.lastIndexOf("."));
            let photoToShow = `<div id="card${hello.savedFiles.length}" class="card_photo">
                <img id="imgShowed${hello.savedFiles.length}" src="` + reader.result + `" alt="` + fileName + `" />
                <a id="remove${hello.savedFiles.length}" onClick='
                document.getElementById("savedFiles").savedFiles = document.getElementById("savedFiles").savedFiles.filter(file => 
                file.name != "${(files[i].name.replace("\"", "&quot;")).replace("\'", "&#39;")}");
                document.getElementById("card${hello.savedFiles.length}").remove();' class="closebtn">
                    &times;
                </a>
                <div class="container">
                    <label for="name">Name: </label>
                    <h6><textarea id="name${hello.savedFiles.length}" name="name" >${fileName}</textarea></h6>
                    <label for="makeupArtist">Make-up Artist: </label>
                    <select id="makeupArtist${hello.savedFiles.length}" class="form-control mb-2" name="makeupArtist">`;
                    for (let i = 0; i < makeupArtists.length; i++) {
                        photoToShow += `<option value="${makeupArtists[i].id}" `;
                        if(makeupArtists[i].id == 0) photoToShow += `selected`;
                        photoToShow +=`>${makeupArtists[i].name}</option>`;
                    }
                    photoToShow += `
                    </select>
                    <label for="photographer">Photographer: </label>
                    <select id="photographer${hello.savedFiles.length}" class="form-control mb-2" name="photographer">`;
                    for (let i = 0; i < photographers.length; i++) {
                        photoToShow += `<option value="${photographers[i].id}" `;
                        if(photographers[i].id == 0) photoToShow += `selected`;
                        photoToShow +=`>${photographers[i].name}</option>`;
                    }
                    photoToShow += `
                    </select>
                    <label for="sharer">Sharer: </label>
                    <select id="sharer${hello.savedFiles.length}" class="form-control mb-2" name="sharer">
                        <option value="-1">Everyone</option>
                        <option value="1" selected>Moi</option>
                    </select>
                    <label for="tag">Tag: </label>
                    <select id="tag${hello.savedFiles.length}" class="form-control mb-2" name="tag">`;
                    for (let i = 0; i < tags.length; i++) {
                        photoToShow += `<option value="${tags[i].id}" `;
                        if(i == 0) photoToShow += `selected`;
                        photoToShow +=`>${tags[i].label}</option>`;
                    }
                    photoToShow += `
                    </select>
                    <label for="date">Date: </label>
                    <div class="input-group">
                        <input id="date${hello.savedFiles.length}" type="datetime-local" class="form-control" value="" name="date" step="1">
                        <button id="dateButton" type="button" onClick="document.getElementById('date${hello.savedFiles.length}').value = '';"><i class="material-icons">delete</i></button>
                    </div>
                </div>
            </div>`;

            document.getElementById('showImg').innerHTML += photoToShow;
            unfixToBottomFooter();
            files[i].id = hello.savedFiles.length;

            hello.savedFiles.push(files[i]);
        }
        reader.readAsDataURL(files[i]);
    }
};

const onSubmit = (e) => {
    e.preventDefault();
    let savedFiles = document.getElementById('savedFiles').savedFiles;

    if(savedFiles && savedFiles.length != 0) {

        let photoNames = [];
        let makeupArtists = [];
        let names = [];
        let photographers = [];
        let sharers = [];
        let tags = [];
        let dates = [];
        console.log(savedFiles);
        for (let i = 0; i < savedFiles.length; i++) {
            console.log("name" + savedFiles[i].id);
            let photoName = document.getElementById("name" + savedFiles[i].id).value;
            let photoMakeupArtist = document.getElementById("makeupArtist" + savedFiles[i].id).value;
            let photoPhotographer = document.getElementById("photographer" + savedFiles[i].id).value;
            let photoSharer = document.getElementById("sharer" + savedFiles[i].id).value;
            let photoTag = document.getElementById("tag" + savedFiles[i].id).value;
            let photoDate = document.getElementById("date" + savedFiles[i].id).value;
            let extention = savedFiles[i].name.substr(savedFiles[i].name.lastIndexOf("."), savedFiles[i].name.length);
            //console.log(photoName + extention);
            savedFiles[i].photoName = photoName + extention;
            savedFiles[i].photographer = photoPhotographer;
            savedFiles[i].sharer = photoSharer;
            console.log("Photo=[Name: " + photoName + ", Picture: " + savedFiles[i] + ", Photographer: " + photoPhotographer + ", Sharer: " 
            + photoSharer + ", Tag: " + photoTag + "]", savedFiles[i]);
            
            
            photoNames.push(photoName + extention);
            makeupArtists.push(photoMakeupArtist);
            names.push(photoName + extention);
            photographers.push(photoPhotographer);
            sharers.push(photoSharer);
            tags.push(photoTag);
            if(!photoDate) photoDate = "null";
            dates.push(photoDate);
            console.log(dates);
        }

        // Creation of the formData with all the photos.
        const formData = new FormData();
        for(let i = 0; i < savedFiles.length; i++){
            formData.append(photoNames[i], savedFiles[i]);
            console.log(photoNames[i], formData.get(photoNames[i]));
        }

        let id = getTokenSessionData();
        fetch(API_URL + "photos/", {
            method: "POST",
            body: formData,
            headers: {
                "Authorization": id,
                "makeupArtists": makeupArtists,
                "names": names,
                "photographers": photographers,
                "sharers": sharers,
                "tags": tags,
                "dates": dates,
                "test": photoNames[0],
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onError(err));
            } else return response.json().then((data) => RedirectUrl("/addPhoto"));
        });
    }
};

export default AddPhotoPage;