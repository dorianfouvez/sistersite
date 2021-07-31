import { user_me } from "../..";
import { fixToBottomFooter, onError } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const AddPhotoPage = () => {
    /*const user = getUserSessionData();
    if (!user || !user.isBoss) {
        RedirectUrl("/");
    } else {*/
        fixToBottomFooter();
        let addPhotoPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Add Photo(s)</center></h1></div>
        <form id="addPhotoForm" enctype="multipart/form-data">
            <input type="file" id="files" name="files" multiple><span id="loading"></span>
            <input id="savedFiles" type="file" hidden multiple>
            <div id="showImg" class="row"></div>
            <button type="submit" name="submitPhoto" class="btn btn-primary mt-2 mb-2"><i class="fas fa-save"></i></button>
        </form>
        
        <p>Une Photo néssécite un id (fait dans le backend), une picture, un nom, l'id d'un photographer, un sharer.<br>
        Il peux ensuite soit être lier par un <b>tags_photo</b> au book "Portraits", "Artistic" ou "couple".<br>
        Mais également lier à un cv ou photo d'utilisateur.</p>`;

        let page = document.querySelector("#page");
        page.innerHTML = addPhotoPage;
        document.getElementById("addPhotoForm").addEventListener("submit", onSubmit);
        document.getElementById("files").addEventListener("change", onUpload);
    //}
};

const onUpload = (e) => {
    document.getElementById("loading").innerHTML = `<div class="loader"></div>`;
    let id = getTokenSessionData();
    fetch(API_URL + "photographers/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": id,
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onFetchTags(e, data.photographers, id));
    });
}

const onFetchTags = (e, photographers, id) => {
    fetch(API_URL + "tags/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": id,
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onShowImage(e, photographers, data.tags));
    });
}

const onShowImage = (e, photographers, tags) => {
    let files = e.target.files;
    let hello = document.getElementById('savedFiles');
    if(!hello.savedFiles) hello.savedFiles = [];
    
    // Remove loading circle.
    document.getElementById('loading').innerHTML = "";

    // Add visuel and 
    for(let i = 0; i < files.length; i++){
        let reader = new FileReader();
        reader.onloadend = function() {
            let fileName = files[i].name.substr(0, files[i].name.length - 4);
            let photoToShow = `<div class="card_photo">
                <img id="imgShowed${hello.savedFiles.length}" src="` + reader.result + `" alt="` + fileName + `" />
                <div class="container">
                    <label for="name">Name: </label>
                    <h6><textarea id="name${hello.savedFiles.length}" name="name" >${fileName}</textarea></h6>
                    <label for="photographer">Photographer: </label>
                    <select id="photographer${hello.savedFiles.length}" class="form-control mb-2" name="photographer">`;
                    for (let i = 0; i < photographers.length; i++) {
                        photoToShow += `<option value="${photographers[i].id}" `;
                        if(i == 0) photoToShow += `selected`;
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
                </div>
            </div>`;

            document.getElementById('showImg').innerHTML += photoToShow;

            hello.savedFiles.push(files[i]);
        }
        reader.readAsDataURL(files[i]);
    }
};

const onSubmit = (e) => {
    e.preventDefault();
    let savedFiles = document.getElementById('savedFiles').savedFiles;
    let photoNames = [];
    let photographers = [];
    let sharers = [];
    let tags = [];
    console.log(savedFiles);
    for (let i = 0; i < savedFiles.length; i++) {
        let photoName = document.getElementById("name" + i).value;
        let photoPhotographer = document.getElementById("photographer" + i).value;
        let photoSharer = document.getElementById("sharer" + i).value;
        let photoTag = document.getElementById("tag" + i).value;
        let extention = savedFiles[i].name.substr(savedFiles[i].name.lastIndexOf("."), savedFiles[i].name.length);
        //console.log(photoName + extention);
        savedFiles[i].photoName = photoName + extention;
        savedFiles[i].photographer = photoPhotographer;
        savedFiles[i].sharer = photoSharer;
        console.log("Photo=[Name: " + photoName + ", Picture: " + savedFiles[i] + ", Photographer: " + photoPhotographer + ", Sharer: " 
        + photoSharer + ", Tag: " + photoTag + "]", savedFiles[i]);
        
        
        photoNames.push(photoName + extention);
        photographers.push(photoPhotographer);
        sharers.push(photoSharer);
        tags.push(photoTag);
    }

    // Creation of the formData with all the photos.
    const formData = new FormData();
    for(let i = 0; i < savedFiles.length; i++){
        formData.append(photoNames[i], savedFiles[i]);
    }

    let id = getTokenSessionData();
    //TODO faire un insert  des photos (avec nom), puis un put pour ajouter les photographers (id) et finir avec les tags.
    fetch(API_URL + "photos/", {
        method: "POST",
        body: formData,
        headers: {
            "Authorization": id,
            "photographers": photographers,
            "sharers": sharers,
            "tags": tags,
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => console.log(data));
    });
};

export default AddPhotoPage;