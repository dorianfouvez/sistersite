import { ALERT_BOX } from "./server.js";

import NavbarEn from "../Components/en/Navbar.js";
import SidebarEn from "../Components/en/Sidebar.js";

import NavbarFr from "../Components/fr/Navbar.js";
import SidebarFr from "../Components/fr/Sidebar.js";

/**
 * setLayout allows to display specific information in an HTML template
 * containing those paramters as id to text elements (h4, h5, title)
 * @param {headerTitle} headerTitle
 * @param {footerText} footerText
 */
function setLayout(headerTitle, footerText) {
  document.querySelector("#footerText").innerText = footerText;
}


function setFooter(){
  let footerDiv = document.querySelector("#footerText");

  footerDiv.className = "navbar bg-dark navbar-dark justify-content-center text-white";

  let footerText = `<a href="https://www.facebook.com/ambre.fouvez" class="fa fa_logo fa-facebook"></a>
  <a href="https://www.instagram.com/ambre.fouvez/" class="fa fa_logo fa-instagram"></a>
  <!--<a href="https://www.youtube.com/channel/UCEdtOcwxGX9viDs4G9AAHeg" class="fa fa_logo fa-youtube"></a>
  <a href="https://www.twitter.com/barackobama" class="fa fa_logo fa-twitter"></a>-->`;

  footerDiv.innerHTML = footerText;
}

function unfixToBottomFooter(){
  let footerDiv = document.querySelector("#footerText");

  footerDiv.className = "navbar bg-dark navbar-dark justify-content-center text-white";
}

function fixToBottomFooter(){
  let footerDiv = document.querySelector("#footerText");

  footerDiv.className = "navbar bg-dark navbar-dark justify-content-center fixed-bottom text-white";
}

function SetFrenchNavigationBars(){
  //NavbarFr();
  SidebarFr();
}

function SetEnglishNavigationBars(){
  //NavbarEn();
  SidebarEn();
}

function setBodyWhite(){
  document.body.style.backgroundColor = "white";
}

const onError = (err) => {
  let messageBoard = document.querySelector("#messageBoard");
  if(err.message) ALERT_BOX(messageBoard, err.message);
  else ALERT_BOX(messageBoard, err);
};

// named export
export { setLayout, setFooter, unfixToBottomFooter, fixToBottomFooter, SetFrenchNavigationBars, SetEnglishNavigationBars, setBodyWhite, onError };