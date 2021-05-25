import { getUserSessionData } from "../../utils/session.js";
import { RedirectUrl } from "../Router.js";

let sideBar = document.querySelector("#sideBar");

const SidebarPage = () => {
    let sidebarPage = `<div id="mySidenav" class="sidenav">
        <a id="closeSidebar" class="closebtn">&times;</a>
        <a id="/" href="#">Home</a>
        <a id="comedienne" href="#" data-toggle="collapse" data-target="#comedienne_c" class>Actress</a>
        <div id="comedienne_c" class="collapse ml-2">
            <a id="/ArtisticCV" href="#">Artistic CV</a>
            <a id="/demotape" href="#">Demo Tape</a>
        </div>
        <a id="book" href="#" data-toggle="collapse" data-target="#book_c" class>Book</a>
        <div id="book_c" class="collapse ml-2">
            <a id="/book" href="#">Portraits</a>
            <a id="/book" href="#">Artistic</a>
            <a id="/book" href="#">Couple</a>
        </div>
        <a id="/about" href="#">About Me</a>
        <a id="/contactme" href="#">Contact</a>
        
        <div class="d-flex justify-content-center mt-3">
            <a href="https://www.facebook.com/ambre.fouvez" class="fa fa_logo fa-facebook"></a>
            <a href="https://www.instagram.com/ambre.fouvez/" class="fa fa_logo fa-instagram"></a>
        </div>`;

    // Add admin link.
    let user = getUserSessionData();
    if(!user){
        sidebarPage += `<a id="/login" class="nav-item nav-link" href="#" data-uri="/login">Login</a>`;
    }else {
        sidebarPage += `<a id="/myprofile" class="nav-item nav-link" href="#" data-uri="/myprofile">${user.userName}</a>
        <a id="/logout" class="nav-item nav-link" href="#" data-uri="/logout">Logout</a>`;
    }
    if (user && user.isBoss) {
        sidebarPage += `<a id="/Boss" class="nav-item nav-link" href="#" data-uri="/myprofile">Boss</a>
        <a id="/Boss2" class="nav-item nav-link" href="#" data-uri="/logout">Boss 2</a>`;
    }

    sidebarPage += `</div>`;

    // Add open button.
    document.getElementById("openSidebar").innerHTML = `<span id="openSidebar" style="font-size:30px;cursor:pointer">&#9776;</span>`;

    sideBar.innerHTML = sidebarPage;

    // SideBar open and close.
    document.getElementById("openSidebar").addEventListener("click", openSidebar);
    document.getElementById("closeSidebar").addEventListener("click", closeSidebar);

    // Collapse.
    document.getElementById("comedienne").addEventListener("click", closeCollapse);
    document.getElementById("book").addEventListener("click", closeCollapse);

    // Lisen all the navigation link.
    document.getElementById("/").addEventListener("click", onNavigate);
    document.getElementById("/about").addEventListener("click", onNavigate);
    document.getElementById("/book").addEventListener("click", onNavigate);
    document.getElementById("/contactme").addEventListener("click", onNavigate);
    document.getElementById("/ArtisticCV").addEventListener("click", onNavigate);
    document.getElementById("/demotape").addEventListener("click", onNavigate);
    if (user && user.isBoss) {
        document.getElementById("/logout").addEventListener("click", onNavigate);
        document.getElementById("/myprofile").addEventListener("click", onNavigate);
    }else document.getElementById("/login").addEventListener("click", onNavigate);
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

const closeCollapse = (e) => {
    e.preventDefault();
    if(document.getElementById(document.activeElement.id + "_c").classList.contains("show")) {
        setTimeout(() => {  
            document.getElementById(document.activeElement.id + "_c").className = `collapse ml-2`; 
        }, 500);
    }
};

const onNavigate = (e) => {
    e.preventDefault();
    closeSidebar();
    RedirectUrl(document.activeElement.id);
};

export default SidebarPage;