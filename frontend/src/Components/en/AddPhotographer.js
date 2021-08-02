import { fixToBottomFooter, onError, onSuccess } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const AddPhotographerPage = () => {
    const user = getUserSessionData();
    if (!user || !user.isBoss) {
        RedirectUrl("/");
    } else {
        fixToBottomFooter();
        let addPhotographerPage = `<div class="mt-2 mb-3 pt-5"><h1><center></center></h1></div>
        <div class="d-flex justify-content-center mb-3">
            <div class="card">
                <div class="card-header">
                    <h3><center>Add Photographer</center></h3>
                </div>
                <div class="card-body">
                    <form id="addPhotographerForm">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <input id="name" type="text" name="name" class="form-control" placeholder="Name" required>
                        </div>
                        
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i>@</i></span>
                            </div>
                            <input id="instagram" type="text" name="instagram" class="form-control" placeholder="Instagram">
                        </div>

                        <button id="submit" type="submit" name="submitPhoto" class="btn btn-primary mt-3 float-right"><i class="fas fa-save"></i></button>
                    </form>
                </div>
                <div class="card-footer pb-4"><span id="successBoard"></span></div>
            </div>
        </div>`;

        let page = document.querySelector("#page");
        page.innerHTML = addPhotographerPage;

        document.getElementById("addPhotographerForm").addEventListener("submit", onSubmit);
    }
};

const onSubmit = (e) => {
    e.preventDefault();
    let submitButton = document.getElementById('submit');
    submitButton.innerHTML = ``;
    submitButton.className = `loader mt-3 float-right`;
    console.log(submitButton);

    let name = document.getElementById('name').value;
    let instagram = null;
    if (document.getElementById('instagram')) {
        instagram = document.getElementById('instagram').value;
        if(instagram != "" && !instagram.startsWith('@')){
            instagram = "@" + instagram;
        }
    }
    let photographer = { "id": -1, "name": name };
    if(instagram){
        photographer = { "id": -1, "name": name, "instagram": instagram };
    }
    console.log(photographer);

    let id = getTokenSessionData();
        fetch(API_URL + "photographers/", {
            method: "POST",
            body: JSON.stringify(photographer),
            headers: {
                "Content-Type": "application/json",
                "Authorization": id,
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onErrors(err));
            } else return response.json().then((data) => onData(data));
        });
};

const onErrors = (err) => {
    let submitButton = document.getElementById('submit');
    submitButton.innerHTML = `<i class="fas fa-save"></i>`;
    submitButton.className = `btn btn-primary mt-3 float-right`;
    onError(err);
}

const onData = (data) => {
    let successBoard = document.getElementById('successBoard');
    let submitButton = document.getElementById('submit');
    submitButton.innerHTML = `<i class="fas fa-save"></i>`;
    submitButton.className = `btn btn-primary mt-3 float-right`;
    onSuccess("Photographe ajouter", successBoard);
}

export default AddPhotographerPage;