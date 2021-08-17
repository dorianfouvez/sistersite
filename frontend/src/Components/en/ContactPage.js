import { clearLoadingButton, onError, onSuccess, transformButtonIntoLoading } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { getTokenSessionData } from "../../utils/session";
import { RedirectUrl } from "../Router";

const ContactPage = () => {
    let contactPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h1>Contact me</h1></div>
    <div class="loader mx-auto"></div>`;

    let page = document.getElementById("page");
    page.innerHTML = contactPage;

    fetch(API_URL + "users/contactInformation", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    }).then((response) => {
        if (!response.ok) {
            return response.text().then((err) => onError(err));
        } else return response.json().then((data) => onData(data));
    });
};

const onData = (data) => {
    if(!data){
        onError("Error get contact information");
    }

    let contactPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h1>Contact me</h1></div>
    <form id="contactForm">
        <p class="text-danger">* Champs obligatoire</p>
        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <label for="firstname" class="input-group-text">
                    <span class="text-danger">*</span>Firstname: 
                </label>
            </div>
            <input id="firstname" name="firstname" class="form-control" autocomplete="given-name" required>
            
            <div class="input-group-prepend">
                <label for="lastname" class="input-group-text">
                    <span class="text-danger">*</span>Lastname: 
                </label>
            </div>
            <input id="lastname" name="lastname" class="form-control" autocomplete="family-name" required>
        </div>

        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <label for="email" class="input-group-text">
                    <span class="text-danger">*</span>Your Email: 
                </label>
            </div>
            <input id="email" type="email" name="email" class="form-control" autocomplete="email"
            pattern="[\\p{L}\\w\.\/\\$~#&=+*-]+@[\\p{L}\\w\.]+\.[a-zA-Z]{2,4}" required>
        </div>

        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <label for="subject" class="input-group-text">
                    <span class="text-danger">*</span>Subject: 
                </label>
            </div>
            <input id="subject" name="subject" class="form-control" required>
        </div>

        <div class="input-group mb-2">
            <div class="input-group-prepend">
                <label for="message" class="input-group-text">
                    <span class="text-danger">*</span>Message: 
                </label>
            </div>
            <textarea id="message" name="message" class="form-control" rows="10" required></textarea>
        </div>

        <div class="float-right">
            <button id="submit" type="submit" name="updatePhoto" class="btn btn-primary mt-2">
                Send
            </button>
        </div>
    </form>`;
    if (data.phone) {
        contactPage += `<br><br>
        <div class="d-flex justify-content-center">
            <h4>Others ways to contact me</h4>
        </div>
        <div class="d-flex justify-content-center">
            <p>${data.phone}</p>
        </div>`;
    }

    let page = document.getElementById("page");
    page.innerHTML = contactPage;
    document.getElementById("contactForm").addEventListener("submit", onSubmit);
};

const onSubmit = (e) => {
    e.preventDefault();
    transformButtonIntoLoading();

    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let from = document.getElementById("email").value;
    let subject = document.getElementById("subject").value;
    let body = document.getElementById("message").value;

    let email = {
        "firstname": firstname,
        "lastname": lastname,
        "from": from,
        "subject": subject,
        "body": body,
    };

    // Test text in console.log
    // console.log("%cTest" + "%c tt", "font-weight: bolder; color: red;", "color: black;", "autre");

    let id = getTokenSessionData();
    if(id){
        fetch(API_URL + "emails", {
            method: "POST",
            body: JSON.stringify(email),
            headers: {
                "Content-Type": "application/json",
                "Authorization": id,
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onErrors(err));
            } else return response.json().then((data) => onUpdate(data));
        });
    }else{
        fetch(API_URL + "emails", {
            method: "POST",
            body: JSON.stringify(email),
            headers: {
                "Content-Type": "application/json",
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onErrors(err));
            } else return response.json().then((data) => onUpdate(data));
        });
    }
};

const onErrors = (err) => {
    clearLoadingButton();
    onError(err);
};

const onUpdate = (data) => {
    let messageBoard = document.querySelector("#messageBoard");
    onSuccess("Message sent !", messageBoard);
    document.getElementById("contactForm").reset();
    clearLoadingButton();
};

export default ContactPage;