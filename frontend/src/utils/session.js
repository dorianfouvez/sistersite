import VerifyUserToken from "./verifyUserToken.js";
import { user_me } from "../index.js";

const STORE_NAME = "user";
const STORE_LANG_NAME = "lang";

const getTokenSessionData = () => {
  let retrievedToken = localStorage.getItem(STORE_NAME);
  if (!retrievedToken) {
    retrievedToken = sessionStorage.getItem(STORE_NAME);
  }
  return retrievedToken;
};

const getUserSessionData = () => {
  return user_me.itself;
};

const setUserSessionData = (user, isRemember) => {
  const storageValue = JSON.stringify(user.token);
  if(isRemember) localStorage.setItem(STORE_NAME, storageValue);
  else sessionStorage.setItem(STORE_NAME, storageValue);

  user_me.itself = user.user;
};

const removeSessionData = () => {
  localStorage.removeItem(STORE_NAME);
  sessionStorage.removeItem(STORE_NAME);

  user_me.itself = null;
};

const checkTokenOnLoad = async () => {
  let retrievedToken = localStorage.getItem(STORE_NAME);
  let isLocalToken = true;
  if(!retrievedToken){
    retrievedToken = sessionStorage.getItem(STORE_NAME);
    isLocalToken = false;
  }
  if(!retrievedToken) return;

  await VerifyUserToken(retrievedToken, isLocalToken);
};

const getLangSessionData = () => {
  return user_me.lang;
};

const getLangFromStorage = () => {
  let retrievedLang = localStorage.getItem(STORE_LANG_NAME);
  if(!retrievedLang){
    retrievedLang = "fr";
    setLangSessionData(retrievedLang);
  }else{
    retrievedLang = retrievedLang.substr(1, retrievedLang.length-2);
    user_me.lang = retrievedLang;

    document.querySelector("html").lang = retrievedLang;
  }
}

const setLangSessionData = (lang) => {
  const storageValue = JSON.stringify(lang);
  localStorage.setItem(STORE_LANG_NAME, storageValue);

  user_me.lang = lang;

  document.querySelector("html").lang = lang;
};


export { getTokenSessionData, getUserSessionData, setUserSessionData, removeSessionData, checkTokenOnLoad, getLangSessionData, getLangFromStorage, setLangSessionData };
