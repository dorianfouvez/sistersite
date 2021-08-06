import { setFooter } from "./utils/render.js";
import { checkTokenOnLoad, getLangFromStorage } from "./utils/session.js";
import { Router } from "./Components/Router.js";
import LanguagePage from "./Components/CommunButtonPage.js";

/* use webpack style & css loader*/
import "./stylesheets/style.css";
/* load bootstrap css (web pack asset management) */
import 'bootstrap/dist/css/bootstrap.css';
/* load bootstrap module (JS) */
import 'bootstrap';

let user_me = { itself: null, lang: null, choice_of_book: null, photo: null };

getLangFromStorage();
LanguagePage();

checkTokenOnLoad();

Router();

setFooter();

export { user_me };