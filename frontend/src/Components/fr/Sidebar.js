import { getUserSessionData } from "../../utils/session.js";

let sideBar = document.querySelector("#sideBar");

const SidebarPage = () => {
    let sidebarPage = `<div id="mySidenav" class="sidenav">
        <a id="closeSidebar" class="closebtn">&times;</a>
        <a href="#">Accueil</a>
        <a id="comedienne" href="#" value="comedienne" class>Comédienne</a>
        <div id="comedienne_c" class="collapse ml-2">
            <a href="#">CV Artistique</a>
            <a href="#">Bande démo</a>
        </div>
        <a id="books" href="#" data-toggle="collapse" data-target="#books_c" class>Book</a>
        <div id="books_c" class="collapse ml-2">
            <a href="#">Portraits</a>
            <a href="#">Artistique</a>
            <a href="#">Couple</a>
        </div>
        <a href="#">A Propos</a>
        <a href="#">Contactez</a>
        
        <div class="d-flex justify-content-center mt-3">
            <a href="https://www.facebook.com/ambre.fouvez" class="fa fa-facebook"></a>
            <a href="https://www.instagram.com/ambre.fouvez/" class="fa fa-instagram"></a>
        </div>`;

    // Add admin link.
    let user = getUserSessionData();
    if (user && user.isBoss) {
        sidebarPage += `<a class="nav-item nav-link" href="#" data-uri="/moncompte">${user.userName}</a>
        <a class="nav-item nav-link" href="#" data-uri="/deconnection">Se déconnecter</a>`;
    }else{
        sidebarPage += `<a class="nav-item nav-link" href="#" data-uri="/connection">Se connecter</a>`;
    }

    sidebarPage += `</div>
    <span id="openSidebar" class="float-right mr-3" style="font-size:30px;cursor:pointer">&#9776;</span>`;

    sideBar.innerHTML = sidebarPage;

    document.getElementById("openSidebar").addEventListener("click", openSidebar);
    document.getElementById("closeSidebar").addEventListener("click", closeSidebar);
    document.getElementById("comedienne").addEventListener("click", showCollapse);
    document.getElementById("book").addEventListener("click", test);
};

const openSidebar = () => {
    document.getElementById("mySidenav").style.width = "50%";
    document.getElementById("page").style.opacity = "0.4";
    document.getElementById("overlay").style.width = "100%";
};

const closeSidebar = () => {
    document.getElementById("mySidenav").style.width = "0px";
    document.getElementById("page").style.opacity = "1";
    document.getElementById("overlay").style.width = "0%";
};

const showCollapse = (e) => {
    e.preventDefault();
    if(document.getElementById(document.activeElement.id + "_c").classList.contains("show")) {
        document.getElementById(document.activeElement.id + "_c").className = `collapse ml-2`;
    }else document.getElementById(document.activeElement.id + "_c").className += ` show`;
};

const test = (e) => {
    e.preventDefault();
    if(document.getElementById(document.activeElement.id + "_c").classList.contains("show")) {
        setTimeout(() => {  
            document.getElementById(document.activeElement.id + "_c").className = `collapse ml-2`; 
        }, 500);
    }
};

export default SidebarPage;