import { user_me } from "../../index.js";
import { API_URL, ALERT_BOX } from "../../utils/server.js";
import { getTokenSessionData, getUserSessionData } from "../../utils/session.js";
import { RedirectUrl } from "../Router.js";
import UpdateUserPage from "./UpdateUserPage.js";

let page = document.querySelector("#page");
let me = null;

const UserPage = () => {
    const user = getUserSessionData();
    if (!user) {
        RedirectUrl("/");
    } else {
        page.innerHTML = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h4>My profile</h4></div>
        <div class="loader mx-auto"></div>`;
        let id = getTokenSessionData();
        fetch(API_URL + "users/complex", {
            method: "GET",
            headers: {
            "Content-Type": "application/json",
            "Authorization": id,
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onError(err));
            } else return response.json().then((data) => onUser(data));
        });
    }
};

const onUser = (data) => {
    if(!data || !data.user) {
        page.innerHTML = ``;
        let err = `No data received.`;
        onError(err);
    }

    me = data;
    console.log(data);

    // Need to fetch All address, All color, All size, All photo and All nationality.
    let user = data.user;
    let photo = user.profilePicture;
    let address = user.address;
    let hairColor = user.hairColor;
    let hairSize = user.hairSize;
    let eye = user.eye;
    let firstNationality = user.firstNationality;
    let secondNationality = user.secondNationality;

    let userPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h4>My profile</h4></div>
    <input type="hidden" id="id_user" value="${user.id}">
    <div class="container mt-5">
        <div class="float-right"><button id="cv" class="btn btn-primary">See my cv</button></div>
        <div class="float-right"><button id="complex" class="btn btn-primary mr-2">Complex</button></div>
        <div class="row">
            <div class="col-sm-2 bg-danger">
                <div class="card img-fluid remove_card_background">
                    <img src="${photo.picture}" class="card-img-top logo_size" alt="${photo.name}">
                    <div class="card-img-overlay">
                        <div class="float-right">
                            <a id="fr" href="#" class="remove_decoration">
                                <i class='fas fa-pen'></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-auto text-left">
                <div class="mb-1"><u><b>UserName :</b></u> ${user.userName}</div>
                <div class="mb-1"><u><b>LastName :</b></u> ${user.lastName}</div>
                <div class="mb-1"><u><b>FirstName :</b></u> ${user.firstName}</div>
                <div class="mb-1"><u><b>Email :</b></u> ${user.email}</div>`;
            if(user.isBoss) userPage += `<div class="mb-1"><strong>You are a boss !</strong></div>`;
            userPage += `</div>
        </div>
        <div class="row mt-2">
            <div><u><b>Phone number :</b></u> `;
            if(user.phoneNumber) userPage += `${user.phoneNumber}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Address :</b></u> `;
            if(address){
                userPage += `<input type="hidden" value="${address.id}">
                ${address.country}, ${address.postcode} ${address.commune}, 
                ${address.street} ${address.buildingNumber}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Facebook :</b></u> `;
            if(user.facebook){ 
                userPage += `${user.facebook} 
                <a href="${user.facebook}" class="fa fa_logo_mini fa-facebook"></a>`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Instagram :</b></u> `;
            if(user.instagram) userPage += `${user.instagram}
            <a href="${user.instagram}" class="fa fa_logo_mini fa-instagram"></a>`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Twitter :</b></u> `;
            if(user.twitter) userPage += `${user.twitter}
            <a href="${user.twitter}" class="fa fa_logo fa-twitter"></a>`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Youtube :</b></u> `;
            if(user.youtube) userPage += `${user.youtube}
            <a href="${user.youtube}" class="fa fa_logo fa-youtube"></a>`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Hair color :</b></u> `;
            if(hairColor){
                userPage += `<input type="hidden" value="${hairColor.id}">
                ${hairColor.color}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Hair size :</b></u> `;
            if(hairSize){ 
                userPage += `<input type="hidden" value="${hairSize.id}">
                ${hairSize.size}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Eye :</b></u> `;
            if(eye){
                userPage += `<input type="hidden" value="${eye.id}">
                ${eye.color}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Height :</b></u> `;
            if(user.height) userPage += `${user.height}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Weight :</b></u> `;
            if(user.weight) userPage += `${user.weight}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>First nationality :</b></u> `;
            if(firstNationality){
                userPage += `<input type="hidden" value="${firstNationality.id}">
                ${firstNationality.nationality}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Second nationality :</b></u> `;
            if(secondNationality){
                userPage += `<input type="hidden" value="${secondNationality.id}">
                ${secondNationality.nationality}`;
            } else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Shoe size :</b></u> `;
            if(user.shoeSize) userPage += `${user.shoeSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Jacket size :</b></u> `;
            if(user.jacketSize) userPage += `${user.jacketSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Pant size :</b></u> `;
            if(user.pantSize) userPage += `${user.pantSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Chest :</b></u> `;
            if(user.chest) userPage += `${user.chest}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Bra cup :</b></u> `;
            if(user.braCup) userPage += `${user.braCup}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Waist size :</b></u> `;
            if(user.waistSize) userPage += `${user.waistSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Hip size :</b></u> `;
            if(user.hipSize) userPage += `${user.hipSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row">
            <div><u><b>Neck size :</b></u> `;
            if(user.neckSize) userPage += `${user.neckSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
        <div class="row mb-3">
            <div><u><b>Head size :</b></u> `;
            if(user.headSize) userPage += `${user.headSize}`;
            else userPage += `undefined`;
            userPage += `</div>
        </div>
    </div>
    
    <!--<p>Registration date : ${user.registrationDate}</p>-->
    </div>`;

    page.innerHTML = userPage;
    document.getElementById("cv").addEventListener('click', onSeeCV);
    document.getElementById("complex").addEventListener('click', onComplex);
};

const onComplex = (e) => {
    e.preventDefault();
    user_me.itself = me;

    UpdateUserPage(me);
}

const onSeeCV = (e) => {
    e.preventDefault();
    let CVId = 1; //TODO faire un fetch?

    let id = getTokenSessionData();
    fetch(API_URL + "curriculumVitea/" + CVId, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: id,
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => console.log(data.cv));
    });
};

const onError = (err) => {
    let messageBoard = document.querySelector("#messageBoard");
    if(err.message) ALERT_BOX(messageBoard, err.message);
    else ALERT_BOX(messageBoard, err);
};

export default UserPage;