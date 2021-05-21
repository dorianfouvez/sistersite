import { RedirectUrl } from "./Router.js";
import { setLangSessionData } from "../utils/session";

let routes = {
    "en": [ "/", "/about", "/book", "/contactme", "/ArtisticCV", "/demotape", "/errornavigation", "/login", "/logout", "/myprofile" ],
    "fr": [ "/", "/apropos", "/books", "/contact", "/cv", "/bandedemo", "/erreurnavigation", "/connection", "/deconnection", "/moncompte" ],
};

const LanguagePage = () => {
    let languagePage = `<div class="float-left">
        <a id="home" class="navbar-brand" href="#" data-uri="/">
            <img src="assets/Images/logoAE_v2.png" class="logo_size" alt="Logo">
        </a>
    </div>
    <div class="float-right">
        <a id="fr" href="fr"><img class="lang_flag" src="assets/Images/french-flag.jpg" alt="fr"></a>
        <a id="en" href="en"><img class="lang_flag" src="assets/Images/british-flag.jpg" alt="en"></a>
        <span id="openSidebar" class="mt-3"></span>
    </div>`;

    let page = document.getElementById("langChoose");
    page.innerHTML = languagePage;

    document.getElementById("fr").addEventListener("click", changeLang);
    document.getElementById("en").addEventListener("click", changeLang);
    document.getElementById("home").addEventListener("click", onNavigate);
};

const changeLang = (e) => {
    e.preventDefault();
    let lang = document.activeElement.id;
    console.log(lang);
    setLangSessionData(lang);
    
    let path = null;
    let index = -1;
    for (let i = 0; i < routes.en.length; i++) {
        if(routes.en[i] === window.location.pathname){
            index = i;
        }
    }

    if(index > -1){
        path = routes.fr[index];
    }else{
        for (let i = 0; i < routes.fr.length; i++) {
            if(routes.fr[i] === window.location.pathname){
                index = i;
            }
        }

        if(index > -1){
            path = routes.en[index];
        }else{
            path = "/";
        }
    } 

    RedirectUrl(path);
};

const onNavigate = (e) => {
    e.preventDefault();
    RedirectUrl("/");
};

export default LanguagePage;