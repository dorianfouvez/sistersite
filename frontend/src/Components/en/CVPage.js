import { fixToBottomFooter, unfixToBottomFooter, onError } from "../../utils/render";
import { API_URL } from "../../utils/server.js";
import { getTokenSessionData } from "../../utils/session.js";

let page = document.querySelector("#page");

const CVPage = () => {
    fixToBottomFooter();
    page.innerHTML = `<div class="text-center"><h4 class="mt-2 pt-5">Artistic Curriculum Vitae</h4></div>
    <div class="loader mx-auto"></div>`;

    let id = getTokenSessionData();
    if(id){
        fetch(API_URL + "curriculumVitea/1", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: id,
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onError(err));
            } else return response.json().then((data) => testCV(data.cv));
        });
    }else{
        fetch(API_URL + "curriculumVitea/1", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onError(err));
            } else return response.json().then((data) => testCV(data.cv));
        });
    }
};

const testCV = (cv) => {
    document.body.style.backgroundColor = "gray";
    unfixToBottomFooter();
    console.log(cv);
    let user = cv.user;
    let cvPage = `<div class="text-center"><h4 class="mt-2 pt-5">Artistic Curriculum Vitae</h4></div>
    <div class="container">
        <div class="row">
            <div class="col" style="background-color:black;height:200px;">
                <div class="float-left mt-4 ml-5" style="background-color:rgb(0, 255, 225);height:90%;width:32%;">Photo<h5 class="mt-5">CARACTÉRISTIQUES</h5></div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col" style="background-color:white;">
                <div class="float-left ml-5 text-center size_h" style="background-color:rgb(0, 255, 225);height:100%;width:32%;">
                    <h5 class="mt-5"><strong>CARACTÉRISTIQUES</strong></h5>
                    <span class="float-left ml-3"><u>Âge de jeu</u></span><span class="float-right mr-3">${cv.playingAge} ans</span><br>
                    <span class="float-left ml-3"><u>Cheveux</u></span><span class="float-right mr-3">${user.hairColor.color}</span><br>
                    <span class="float-left ml-3"><u>Yeux</u></span><span class="float-right mr-3">${user.eye.color}</span><br>
                    <span class="float-left ml-3"><u>Taille</u></span><span class="float-right mr-3">${user.height} cm</span><br>
                    <span class="float-left ml-3"><u>Poids</u></span><span class="float-right mr-3">${user.weight} kg</span><br>
                    <div> </div>
                    
                    <h5 class="mt-5"><strong>ATOUTS</strong></h5>`;
                    cv.strengths.forEach(strength => {
                        cvPage += `<span>${strength.label}</span><br>`;
                    });
                    cvPage += `<h5 class="mt-5"><strong>CONTACT</strong></h5>
                    <p>${user.phoneNumber}</p>
                    <p>${user.address.buildingNumber} ${user.address.street}.<br>
                    ${user.address.postcode} ${user.address.commune}, ${user.address.country}.</p>`;
                    if(user.facebook) cvPage += `<p><a href="${user.facebook}" class="fa fa_logo_mini fa-facebook"></a> ${user.firstName} ${user.lastName}</p>`;
                    if(user.instagram){ cvPage += `<p>
                            <a href="${user.instagram}" class="fa fa_logo_mini fa-instagram"></a> ${user.instagram.substring(26, (user.instagram.length-1))}
                        </p>`;
                    }
                    if(window.screen.width > 450) cvPage += `<p>${user.email}</p>`;
                    else {
                        // If mobile.
                        cvPage += `<p>${user.email.replace("@", "@\n")}</p>`;
                    }
                cvPage += `</div>
                <div class="col-7 offset-5">
                    <h5 class="mt-5" style="color:rgb(0, 255, 225);">FORMATIONS</h5>`;
                    cv.trainings.forEach(training => {
                        cvPage += `<div class="float-left mr-5 mt-3">${training.startYear} - ${training.endYear}</div>
                        <div class="offset-3 mt-3">
                            <strong>${training.label}</strong>, ${training.explanations}.
                        </div>`;
                    });
                    cvPage += `<h5 class="mt-5" style="color:rgb(0, 255, 225);">COURTS - MÉTRAGES</h5>`;
                    cv.shortFilms.forEach(shortFilm => {
                        cvPage += `<div class="float-left mr-5 mt-3">${shortFilm.year}</div>
                        <div class="offset-3 mt-3">
                            <strong>${shortFilm.title},`;
                            if(shortFilm.directors.length != 0){
                                cvPage += ` réalisé par `;
                                shortFilm.directors.forEach(director => {
                                    cvPage += `${director.name} et `;
                                });
                                cvPage = cvPage.substring(0, (cvPage.length-4));
                            }
                            if(shortFilm.company){
                                cvPage += ` pour l'entreprise ${shortFilm.company.name}`;
                            }
                            cvPage += `.</strong> ${shortFilm.role.name}.
                        </div>`;
                    });
                    cvPage += `<h5 class="mt-5" style="color:rgb(0, 255, 225);">CINÉMA</h5>`;
                    cv.cinemas.forEach(cinema => {
                        cvPage += `<div class="float-left mr-5 mt-3">${cinema.year}</div>
                        <div class="offset-3 mt-3">
                            <strong>${cinema.title},`;
                            if(cinema.directors.length != 0){
                                cvPage += ` réalisé par `;
                                cinema.directors.forEach(director => {
                                    cvPage += `${director.name} et `;
                                });
                                cvPage = cvPage.substring(0, (cvPage.length-4));
                            }
                            cvPage += `.</strong>`;
                            if(cinema.distinctions.length != 0){
                                cinema.distinctions.forEach(distinction => {
                                    cvPage += ` ${distinction.name}. `;
                                });
                            }
                            cvPage += ` ${cinema.role.name}.
                        </div>`;
                    });
                    cvPage += `<div class="mb-5"></div>
                </div>
            </div>
        </div>
    </div>`;

    page.innerHTML = cvPage;
}

export default CVPage;