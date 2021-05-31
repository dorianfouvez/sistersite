// Global Import.
import { getLangSessionData, setLangSessionData } from "../utils/session.js";
import HomePage from "./HomePage.js";

// Import for English routes.
import AboutPage from "./en/AboutPage.js";
import BookPage from "./en/BookPage.js";
import ContactPage from "./en/ContactPage.js";
import CVPage from "./en/CVPage.js";
import DemoTapePage from "./en/DemoTapePage.js";
import ErrorNavigationPage from "./en/ErrorNavigationPage.js";
import LoginPage from "./en/LoginPage.js";
import LogoutComponent from "./en/LogoutComponent.js";
import NavbarEn from "./en/Navbar.js";
import SidebarEn from "./en/Sidebar.js";
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


const routesEn = {
  "/": HomePage,
  "/about": WorkInProgressPage,
  "/book": WorkInProgressPage,
  "/contactme": WorkInProgressPage,
  "/ArtisticCV": CVPage,
  "/demotape": WorkInProgressPage,
  "/errornavigation": ErrorNavigationPage,
  "/login": LoginPage,
  "/logout": LogoutComponent,
  "/myprofile": UserPage,
};

const routesFr = {
  "/": HomePage,
  "/apropos": TravauxEnCours,
  "/bandedemo": TravauxEnCours,
  "/books": TravauxEnCours,
  "/connection": Connection,
  "/contact": TravauxEnCours,
  "/cv": TravauxEnCours,
  "/deconnection": Deconnection,
  "/erreurnavigation": ErreurNavigationPage,
  "/moncompte": TravauxEnCours,
};

let navBar = document.querySelector("#navBar");
let componentToRender;

// dictionnary of routes
const Router = () => {
  /* manage to route the right component when the page is loaded */
  window.addEventListener("load", (e) => {
    let lang = getLangSessionData();
    if(window.location.pathname === "/" && lang === "en") { 
      componentToRender = routesEn[window.location.pathname];
      NavbarEn();
      SidebarEn();
    }else if(window.location.pathname === "/") {
      componentToRender = routesFr[window.location.pathname];
      NavbarFr();
      SidebarFr();
    }else{
      componentToRender = routesEn[window.location.pathname];
      if(componentToRender) {
      if(lang !== "en") setLangSessionData("en");
        NavbarEn();
        SidebarEn();
      }else {
        componentToRender = routesFr[window.location.pathname];
        if(componentToRender) {
          if(lang !== "fr") setLangSessionData("fr");
          NavbarFr();
          SidebarFr();
        }
      }
    }
    
    if (!componentToRender){
      if(lang === "en"){
        NavbarEn();
        SidebarEn();
        ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
      }else {
        NavbarFr();
        SidebarFr();
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
      // use Web History API to add current page URL to the user's navigation history & set right URL in the browser (instead of "#")
      window.history.pushState({}, uri, window.location.origin + uri);
      // render the requested component
      // for the components that include JS, we want to assure that the JS included is not runned when the JS file is charged by the browser
      // therefore, those components have to be either a function or a class
      let lang = getLangSessionData();
      if(uri === "/" && lang === "en") { 
        componentToRender = routesEn[uri];
        NavbarEn();
        SidebarEn();
      }else if(uri === "/") {
        componentToRender = routesFr[uri];
        NavbarFr();
        SidebarFr();
      }else{
        componentToRender = routesEn[uri];
        if(componentToRender) {
        if(lang !== "en") setLangSessionData("en");
          NavbarEn();
          SidebarEn();
        }else {
          componentToRender = routesFr[uri];
          if(componentToRender) {
            if(lang !== "fr") setLangSessionData("fr");
            NavbarFr();
            SidebarFr();
          }
        }
      }
      if (componentToRender) {
        componentToRender();
      } else {
        if(lang === "en"){
          NavbarEn();
          SidebarEn();
          ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
        }else {
          NavbarFr();
          SidebarFr();
          ErrorNavigationPage(new Error("Le chemin <strong>" + window.location.pathname + "</strong> n'existe pas."));
        }
      }
    }
  };

  navBar.addEventListener("click", onNavigate);

  // Display the right component when the user use the browsing history
  window.addEventListener("popstate", () => {
    let lang = getLangSessionData();
    if(window.location.pathname === "/" && lang === "en") { 
      componentToRender = routesEn[window.location.pathname];
      NavbarEn();
      SidebarEn();
    }else if(window.location.pathname === "/") {
      componentToRender = routesFr[window.location.pathname];
      NavbarFr();
      SidebarFr();
    }else{
      componentToRender = routesEn[window.location.pathname];
      if(componentToRender) {
      if(lang !== "en") setLangSessionData("en");
        NavbarEn();
        SidebarEn();
      }else {
        componentToRender = routesFr[window.location.pathname];
        if(componentToRender) {
          if(lang !== "fr") setLangSessionData("fr");
          NavbarFr();
          SidebarFr();
        }
      }
    }
    componentToRender();
  });
};

const RedirectUrl = (uri, data) => {
  // use Web History API to add current page URL to the user's navigation history & set right URL in the browser (instead of "#")
  window.history.pushState({}, uri, window.location.origin + uri);
  // render the requested component
  // for the components that include JS, we want to assure that the JS included is not runned when the JS file is charged by the browser
  // therefore, those components have to be either a function or a class
  let lang = getLangSessionData();
  if(uri === "/" && lang === "en") { 
    componentToRender = routesEn[uri];
    NavbarEn();
    SidebarEn();
  }else if(uri === "/") {
    componentToRender = routesFr[uri];
    NavbarFr();
    SidebarFr();
  }else{
    componentToRender = routesEn[uri];
    if(componentToRender) {
    if(lang !== "en") setLangSessionData("en");
      NavbarEn();
      SidebarEn();
    }else {
      componentToRender = routesFr[uri];
      if(componentToRender) {
        if(lang !== "fr") setLangSessionData("fr");
        NavbarFr();
        SidebarFr();
      }
    }
  }
  if (componentToRender) {
    if (!data) componentToRender();
    else componentToRender(data);
  } else {
    if(lang === "en"){
      NavbarEn();
      SidebarEn();
      ErrorNavigationPage(new Error("The <strong>" + window.location.pathname + "</strong> ressource does not exist."));
    }else {
      NavbarFr();
      SidebarFr();
      ErrorNavigationPage(new Error("Le chemin <strong>" + window.location.pathname + "</strong> n'existe pas."));
    }
  }
};

export { Router, RedirectUrl };
