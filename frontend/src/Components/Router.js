// Global Import.
import { SetFrenchNavigationBars, SetEnglishNavigationBars, setBodyWhite } from "../utils/render.js";
import { checkTokenOnLoad, getLangSessionData, setLangSessionData } from "../utils/session.js";
import HomePage from "./HomePage.js";

// Import for English routes.
import AboutPage from "./en/AboutPage.js";
import AddMakeupArtistPage from "./en/AddMakeupArtistPage.js";
import AddPhotoPage from "./en/AddPhotoPage.js";
import AddPhotographerPage from "./en/AddPhotographer.js";
import AddTagPage from "./en/AddTagPage.js";
import BookPage from "./en/BookPage.js";
import ContactPage from "./en/ContactPage.js";
import CVPage from "./en/CVPage.js";
import DemoTapePage from "./en/DemoTapePage.js";
import ErrorNavigationPage from "./en/ErrorNavigationPage.js";
import LoginPage from "./en/LoginPage.js";
import LogoutComponent from "./en/LogoutComponent.js";
import NavbarEn from "./en/Navbar.js";
import PhotosPage from "./en/PhotosPage.js";
import SidebarEn from "./en/Sidebar.js";
import UpdatePhotoPage from "./en/UpdatePhotoPage.js";
import UserPage from "./en/UserPage.js";
import WorkInProgressPage from "./en/WorkInProgressPage.js";

// Import for French routes.
import APropos from "./fr/AboutPage.js";
import BandeDemo from "./fr/DemoTapePage.js";
import BookPageFR from "./fr/BookPage.js";
import Connection from "./fr/LoginPage.js";
import ContactPageFR from "./fr/ContactPage.js";
import CVPageFR from "./fr/CVPage.js";
import Deconnection from "./fr/LogoutComponent.js";
import ErreurNavigationPage from "./fr/ErrorNavigationPage.js";
import NavbarFr from "./fr/Navbar.js";
import SidebarFr from "./fr/Sidebar.js";
import TravauxEnCours from "./fr/WorkInProgressPage.js";
import Utilisateur from "./fr/UserPage.js";
import { unfixToBottomFooter } from "../utils/render.js";


const routesEn = {
  "/": HomePage,
  "/about": WorkInProgressPage,
  "/addMakeupArtist": AddMakeupArtistPage,
  "/addPhoto": AddPhotoPage,
  "/addPhotographer": AddPhotographerPage,
  "/addTag": AddTagPage,
  "/ArtisticCV": CVPage,
  "/book": BookPage,
  "/contactme": WorkInProgressPage,
  "/demotape": WorkInProgressPage,
  "/errornavigation": ErrorNavigationPage,
  "/login": LoginPage,
  "/logout": LogoutComponent,
  "/myprofile": UserPage,
  "/photos": PhotosPage,
  "/updatePhoto": UpdatePhotoPage,
};

const routesFr = {
  "/": HomePage,
  "/ajoutMaquilleuse": TravauxEnCours,
  "/ajoutPhoto": TravauxEnCours,
  "/ajoutPhotographe": TravauxEnCours,
  "/ajoutTag": TravauxEnCours,
  "/apropos": TravauxEnCours,
  "/bandedemo": TravauxEnCours,
  "/books": TravauxEnCours,
  "/connection": Connection,
  "/contact": TravauxEnCours,
  "/cv": TravauxEnCours,
  "/deconnection": Deconnection,
  "/erreurnavigation": ErreurNavigationPage,
  "/modifierPhoto": TravauxEnCours,
  "/moncompte": TravauxEnCours,
  "/photos": TravauxEnCours,
};

let navBar = document.querySelector("#navBar");
let sideBar = document.querySelector("#sideBar");
let componentToRender;

// dictionnary of routes
const Router = () => {
  /* manage to route the right component when the page is loaded */
  window.addEventListener("load", (e) => {
    //await checkTokenOnLoad();
    let lang = getLangSessionData();
    if(window.location.pathname === "/" && lang === "en") { 
      componentToRender = routesEn[window.location.pathname];
      SetEnglishNavigationBars();
    }else if(window.location.pathname === "/") {
      componentToRender = routesFr[window.location.pathname];
      SetFrenchNavigationBars();
    }else{
      componentToRender = routesEn[window.location.pathname];
      if(componentToRender) {
      if(lang !== "en") setLangSessionData("en");
      SetEnglishNavigationBars();
      }else {
        componentToRender = routesFr[window.location.pathname];
        if(componentToRender) {
          if(lang !== "fr") setLangSessionData("fr");
          SetFrenchNavigationBars();
        }
      }
    }
    
    if (!componentToRender){
      if(lang === "en"){
        SetEnglishNavigationBars();
        ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
      }else {
        SetFrenchNavigationBars();
        ErrorNavigationPage(new Error("Le chemin <strong>" + window.location.pathname + "</strong> n'existe pas."));
      }
    }else{
      componentToRender();
    }
  });

  /* manage click on the navBar*/
  const onNavigate = (e) => {
    let uri;
    if (e.target.tagName === "A") {
      e.preventDefault();
      // To get a data attribute through the dataset object, get the property by the part of the attribute name after data- (note that dashes are converted to camelCase).
      uri = e.target.dataset.uri;
    }
    if (uri) {
      unfixToBottomFooter();
      setBodyWhite();
      // use Web History API to add current page URL to the user's navigation history & set right URL in the browser (instead of "#")
      window.history.pushState({}, uri, window.location.origin + uri);
      // render the requested component
      // for the components that include JS, we want to assure that the JS included is not runned when the JS file is charged by the browser
      // therefore, those components have to be either a function or a class
      let lang = getLangSessionData();
      if(uri === "/" && lang === "en") { 
        componentToRender = routesEn[uri];
        SetEnglishNavigationBars();
      }else if(uri === "/") {
        componentToRender = routesFr[uri];
        SetFrenchNavigationBars();
      }else{
        componentToRender = routesEn[uri];
        if(componentToRender) {
        if(lang !== "en") setLangSessionData("en");
          SetEnglishNavigationBars();
        }else {
          componentToRender = routesFr[uri];
          if(componentToRender) {
            if(lang !== "fr") setLangSessionData("fr");
            SetFrenchNavigationBars();
          }
        }
      }
      if (componentToRender) {
        componentToRender();
      } else {
        if(lang === "en"){
          SetEnglishNavigationBars();
          ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
        }else {
          SetFrenchNavigationBars();
          ErrorNavigationPage(new Error("Le chemin <strong>" + window.location.pathname + "</strong> n'existe pas."));
        }
      }
    }
  };

  navBar.addEventListener("click", onNavigate);
  sideBar.addEventListener("click", onNavigate);

  // Display the right component when the user use the browsing history
  window.addEventListener("popstate", () => {
    unfixToBottomFooter();
    setBodyWhite();
    let lang = getLangSessionData();
    if(window.location.pathname === "/" && lang === "en") { 
      componentToRender = routesEn[window.location.pathname];
      SetEnglishNavigationBars();
    }else if(window.location.pathname === "/") {
      componentToRender = routesFr[window.location.pathname];
      SetFrenchNavigationBars();
    }else{
      componentToRender = routesEn[window.location.pathname];
      if(componentToRender) {
      if(lang !== "en") setLangSessionData("en");
        SetEnglishNavigationBars();
      }else {
        componentToRender = routesFr[window.location.pathname];
        if(componentToRender) {
          if(lang !== "fr") setLangSessionData("fr");
          SetFrenchNavigationBars();
        }
      }
    }
    componentToRender();
  });
};

const RedirectUrl = (uri, data) => {
  unfixToBottomFooter();
  setBodyWhite();
  // use Web History API to add current page URL to the user's navigation history & set right URL in the browser (instead of "#")
  window.history.pushState({}, uri, window.location.origin + uri);
  // render the requested component
  // for the components that include JS, we want to assure that the JS included is not runned when the JS file is charged by the browser
  // therefore, those components have to be either a function or a class
  let lang = getLangSessionData();
  if(uri === "/" && lang === "en") { 
    componentToRender = routesEn[uri];
    SetEnglishNavigationBars();
  }else if(uri === "/") {
    componentToRender = routesFr[uri];
    SetFrenchNavigationBars();
  }else{
    componentToRender = routesEn[uri];
    if(componentToRender) {
    if(lang !== "en") setLangSessionData("en");
      SetEnglishNavigationBars();
    }else {
      componentToRender = routesFr[uri];
      if(componentToRender) {
        if(lang !== "fr") setLangSessionData("fr");
        SetFrenchNavigationBars();
      }
    }
  }
  if (componentToRender) {
    if (!data) componentToRender();
    else componentToRender(data);
  } else {
    if(lang === "en"){
      SetEnglishNavigationBars();
      ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
    }else {
      SetFrenchNavigationBars();
      ErrorNavigationPage(new Error("Le chemin <strong>" + window.location.pathname + "</strong> n'existe pas."));
    }
  }
};

export { Router, RedirectUrl };
