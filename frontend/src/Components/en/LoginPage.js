/* In a template literal, the ` (backtick), \ (backslash), and $ (dollar sign) characters should be 
escaped using the escape character \ if they are to be included in their template value. 
By default, all escape sequences in a template literal are ignored.*/
import { getUserSessionData, setUserSessionData } from "../../utils/session.js";
import { RedirectUrl } from "../Router.js";
import { API_URL, ALERT_BOX } from "../../utils/server.js";
import Navbar from "./Navbar.js";
import Sidebar from "./Sidebar.js";

let remember = false;

let loginPage = `<div class="containerForm mb-3">
  <div class="mt-2 mb-2"><h1><center>Welcome Back !</center></h1></div>
  <div class="d-flex justify-content-center h-100 mt-4">
    <div class="card">
      <div class="card-header">
      <h3><center>Log in</center></h3>
      </div>
      <div class="card-body text-center">
        <form id="login">
          <div class="input-group form-group">
            <div class="input-group-prepend">
              <span class="input-group-text"><i class="fas fa-user"></i></span>
            </div>
            <input type="text" class="form-control" id="username" placeholder="Username" required>
            
          </div>
          <div class="input-group form-group">
            <div class="input-group-prepend">
              <span class="input-group-text"><i class="fas fa-key"></i></span>
            </div>
            <input type="password" class="form-control" id="password" placeholder="Password" required>
          </div>
          <div class="d-flex justify-content-center">
            <input type="checkbox" id="remember" class="mt-1 mr-1">
						<label for="remember">Remember Me</label>
          </div>
          <div class="form-group">
            <input type="submit" value="Log in" class="btn btn-lg btn-outline-primary btn-block">   
          </div>
        </form>
      </div>
      <div class="card-footer">
        <div class="d-flex justify-content-center">
          <a data-toggle="tooltip" data-placement="top" title="Call your brother" id="tooltip"><small>forgot your password ?</small></a>
        </div>
      </div>
    </div>
  </div>
</div>`;

const LoginPage = () => {
  let page = document.querySelector("#page");
  page.innerHTML = loginPage;
  const user = getUserSessionData();
  if (user) {
    // re-render the navbar for the authenticated user.
    Navbar();
    Sidebar();
    RedirectUrl("/");
  } else{
    let loginForm = document.getElementById("login");
    loginForm.addEventListener("submit", onLogin);
    document.getElementById("tooltip").addEventListener("mouseover", onOver);
  }
};

const onOver = (e) => {
  e.preventDefault();
  $('[data-toggle="tooltip"]').tooltip();   
};

const onLogin = (e) => {
  e.preventDefault();
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  let user = {
    "username": username,
    "password": password,
  };

  remember = document.getElementById("remember").checked;

  fetch(API_URL + "users/login", {
    method: "POST",
    body: JSON.stringify(user),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        return response.text().then((err) => onError(err));
      }
      else
        return response.json().then((data) => onUserLogin(data));
    })
};

const onUserLogin = (userData) => {
  const user = { ...userData, isAutenticated: true };
  setUserSessionData(user, remember);
  // re-render the navbar for the authenticated user
  Navbar();
  Sidebar();
  RedirectUrl("/");
};

const onError = (err) => {
  let messageBoard = document.querySelector("#messageBoard");
  if(err.message) ALERT_BOX(messageBoard, err.message);
  else ALERT_BOX(messageBoard, err);
};

export default LoginPage;
