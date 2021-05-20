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
    console.log(user);
    let userPage = `<div class="text-center">`;
    userPage += `<h4 class="mt-2">My profile</h4>
    </div>`;

    return (page.innerHTML = userPage);
};

const onError = (err) => {
    let messageBoard = document.querySelector("#messageBoard");
    if(err.message) ALERT_BOX(messageBoard, err.message);
    else ALERT_BOX(messageBoard, err);
};

export default UserPage;