import { API_URL, ALERT_BOX } from "./server.js";
import { setUserSessionData, removeSessionData, getLangSessionData } from "./session.js"
import { SetEnglishNavigationBars, SetFrenchNavigationBars } from "./render.js";

let isLocalToken = null;

const VerifyUserToken = async (id, isLocalItem) => {
    isLocalToken = isLocalItem;
    await fetch(API_URL + "users/me", {
        method: "GET", 
        headers: {
          "Content-Type": "application/json",
          "Authorization": id,
        },
    })
    .then((response) => {
        if (!response.ok)
        throw new Error(
            "Error code : " + response.status + " : " + response.statusText
        );
        return response.json();
    })
    .then((data) => onUserFound(data))
    .catch((err) => onError(err));
};

const onUserFound = (userData) => {
    const user = { ...userData, isAutenticated: true };
    setUserSessionData(user, isLocalToken);
    let lang = getLangSessionData();
    if(lang === "en"){ SetEnglishNavigationBars(); }
    else { SetFrenchNavigationBars();}
};

const onError = (err) => {
    removeSessionData();
    let messageBoard = document.querySelector("#messageBoard");
    let errorMessage = "";
    if (err.message.includes("401")) { 
        let lang = getLangSessionData();
        if(lang && lang === "en"){
            errorMessage = 'Wrong username or password.';
        }else{
            // Default is "fr"
            errorMessage = `Nom d'utilisateur ou mot de passe incorrect.`;
        }
        ALERT_BOX(messageBoard, errorMessage);
    }else{
        errorMessage = err.message;
        ALERT_BOX(messageBoard, errorMessage);
    }
};

export default VerifyUserToken;