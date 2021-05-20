import { API_URL, ALERT_BOX } from "../utils/server.js";
import { RedirectUrl } from "./Router.js";
import { user_me } from "../index.js";
import { getUserSessionData, setLangSessionData } from "../utils/session";

let page = document.querySelector("#page");

const HomePage = () => { 

  let homePage = `<div class="background_img mb-2">
    <div class="disapered_text">
      <h1>Ambre Fouvez</h1>
    </div>
  </div><!--<img class="d-block" src="assets/Images/Bureau_1.png" alt="First slide" >-->`;  
  page.innerHTML = homePage;
};

const onError = (err) => {
  let messageBoard = document.querySelector("#messageBoard");
  if(err.message) ALERT_BOX(messageBoard, err.message);
  else ALERT_BOX(messageBoard, err);
};

export default HomePage;
