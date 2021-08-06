import { clearLoadingButton, onError, onSuccess, transformButtonIntoLoading } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const AddPhotographerPage = () => {
    const user = getUserSessionData();
    if (!user || !user.isBoss) {
        RedirectUrl("/");
    } else {
        let addPhotographerPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h1></h1></div>
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
    transformButtonIntoLoading();

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
    clearLoadingButton();
    onError(err);
};

const onData = (data) => {
    clearLoadingButton();
    clearForm();
    let successBoard = document.getElementById('successBoard');
    onSuccess("Photographer added", successBoard);
};

const clearForm = () => {
    document.getElementById('name').value = "";
    document.getElementById('instagram').value = "";
};

export default AddPhotographerPage;