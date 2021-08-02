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
  <a href="https://www.twitter.com/barackobama" class="fa fa_logo fa-twitter"></a>-->
  
  <button id="role_dice" class="btn btn-outline-primary ml-2" style='font-size:24px'>Role <i class='fas fa-dice-d6'></i></button><span id="result_dice"></span>`;

  footerDiv.innerHTML = footerText;
  document.getElementById("role_dice").addEventListener("click", roleDice);
}

function roleDice(){
  const rndInt = Math.floor(Math.random() * 6) + 1;

  switch (rndInt) {
    case 1:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-one ml-2' style='font-size:24px'></i>`;
      break;
    case 2:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-two ml-2' style='font-size:24px'></i>`;
      break;
    case 3:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-three ml-2' style='font-size:24px'></i>`;
      break;
    case 4:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-four ml-2' style='font-size:24px'></i>`;
      break;
    case 5:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-five ml-2' style='font-size:24px'></i>`;
      break;
    case 6:
      document.getElementById("result_dice").innerHTML = `<i class='fas fa-dice-six ml-2' style='font-size:24px'></i>`;
      break;
  
    default:
      break;
  }
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
  // Reset possible loading circle
  let loadingCircle = document.getElementById('loading');
  if(loadingCircle){
    loadingCircle.innerHTML = "";
  }
  document.querySelector('.loader').remove();

  let messageBoard = document.querySelector("#messageBoard");
  if(err.message) ALERT_BOX(messageBoard, err.message);
  else ALERT_BOX(messageBoard, err);
};

// named export
export { setLayout, setFooter, unfixToBottomFooter, fixToBottomFooter, SetFrenchNavigationBars, SetEnglishNavigationBars, setBodyWhite, onError };