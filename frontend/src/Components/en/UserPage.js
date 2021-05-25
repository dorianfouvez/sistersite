import { API_URL, ALERT_BOX } from "../../utils/server.js";
import { getTokenSessionData } from "../../utils/session.js";

let page = document.querySelector("#page");

const UserPage = () => {
    page.innerHTML = `<div class="loader mx-auto"></div>`;
    let id = getTokenSessionData();
    fetch(API_URL + "users/me", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: id,
        },
    }).then((response) => {
        if (!response.ok) {
          return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onUser(data));
    });
};

const onUser = (data) => {
    let user = data.user;
    let userPage = `<div class="text-center">`;
    userPage += `<h4 class="mt-2">My profile</h4>
    <input type="hidden" id="id_user" value="${user.id}">
    <p>userName : ${user.userName}</p>
    <p>lastName : ${user.lastName}</p>
    <p>firstName : ${user.firstName}</p>
    <p>email : ${user.email}</p>
    <p>isBoss : ${user.isBoss}</p>
    <p>registrationDate : ${user.registrationDate}</p>
    <p>profilePicture : ${user.profilePicture}</p>
    <p>Adress : ${user.adress}</p>
    <p>phoneNumber : ${user.phoneNumber}</p>
    <p>facebook : ${user.facebook}</p>
    <p>instagram : ${user.instagram}</p>
    <p>twitter : ${user.twitter}</p>
    <p>youtube : ${user.youtube}</p>
    <p>hairColor : ${user.hairColor}</p>
    <p>hairSize : ${user.hairSize}</p>
    <p>eye : ${user.eye}</p>
    <p>height : ${user.height}</p>
    <p>weight : ${user.weight}</p>
    <p>firstNationality : ${user.firstNationality}</p>
    <p>secondNationality : ${user.secondNationality}</p>
    <p>shoeSize : ${user.shoeSize}</p>
    <p>jacketSize : ${user.jacketSize}</p>
    <p>pantSize : ${user.pantSize}</p>
    <p>chest : ${user.chest}</p>
    <p>braCup : ${user.braCup}</p>
    <p>waistSize : ${user.waistSize}</p>
    <p>hipSize : ${user.hipSize}</p>
    <p>neckSize : ${user.neckSize}</p>
    <p>headSize : ${user.headSize}</p>
    </div>`;

    return (page.innerHTML = userPage);
};

const onError = (err) => {
    let messageBoard = document.querySelector("#messageBoard");
    if(err.message) ALERT_BOX(messageBoard, err.message);
    else ALERT_BOX(messageBoard, err);
};

export default UserPage;