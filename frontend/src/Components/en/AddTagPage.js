import { clearLoadingButton, fixToBottomFooter, onError, onSuccess, transformButtonIntoLoading } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData, getUserSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const AddTagPage = () => {
    const user = getUserSessionData();
    if (!user || !user.isBoss) {
        RedirectUrl("/");
    } else {
        fixToBottomFooter();
        let addTagPage = `<div class="mt-2 mb-3 pt-5"><h1><center></center></h1></div>
        <div class="d-flex justify-content-center mb-3">
            <div class="card">
                <div class="card-header">
                    <h3><center>Add Tag</center></h3>
                </div>
                <div class="card-body">
                    <form id="addTagForm">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-pen"></i></span>
                            </div>
                            <input id="label" type="text" name="label" class="form-control" placeholder="Label" required>
                        </div>

                        <button id="submit" type="submit" name="submitPhoto" class="btn btn-primary mt-3 float-right"><i class="fas fa-save"></i></button>
                    </form>
                </div>
                <div class="card-footer pb-4"><span id="successBoard"></span></div>
            </div>
        </div>`;

        let page = document.querySelector("#page");
        page.innerHTML = addTagPage;

        document.getElementById("addTagForm").addEventListener("submit", onSubmit);
    }
};

const onSubmit = (e) => {
    e.preventDefault();
    transformButtonIntoLoading();

    let label = document.getElementById('label').value;
    let tag = { "id": -1, "label": label };
    console.log(tag);

    let id = getTokenSessionData();
        fetch(API_URL + "tags/", {
            method: "POST",
            body: JSON.stringify(tag),
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
    onSuccess("Tag added", successBoard);
};

const clearForm = () => {
    document.getElementById('label').value = "";
};

export default AddTagPage;