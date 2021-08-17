import { user_me } from "../../index.js";
import { API_URL } from "../../utils/server.js";
import { getUserSessionData } from "../../utils/session.js";
import { RedirectUrl } from "../Router.js";

let sideBar = document.querySelector("#sideBar");

const SidebarPage = () => {
    /*fetch(API_URL + "tags/all", {
        method: "GET",
        headers: {
        "Content-Type": "application/json",
        },
    }).then((response) => {
        if (!response.ok) {
        return response.text().then((err) => onError(err));
        } else return response.json().then((data) => console.log(data));
    });*/
    let sidebarPage = `<div id="mySidenav" class="sidenav">
        <a id="closeSidebar" class="closebtn">&times;</a>
        <a id="/" href="/"><i class='fas fa-home'></i> Home</a>
        <a id="comedienne" href="" data-toggle="collapse" data-target="#comedienne_c" class><i class='fas fa-film'></i> Actress</a>
        <div id="comedienne_c" class="collapse ml-2">
            <a id="/ArtisticCV" href="/ArtisticCV">Artistic CV</a>
            <a id="/demotape" href="/demotape" class="isDisabled">Demo Tape</a>
        </div>
        <a id="book" href="" data-toggle="collapse" data-target="#book_c" class><i class='fas fa-book'></i> Book</a>
        <div id="book_c" class="collapse ml-2">
            <a id="/bookP" name="1" href="/book">Portraits</a>
            <a id="/bookA" name="2" href="/book">Artistic</a>
            <a id="/bookC" name="3" href="/book">Couple</a>
        </div>
        <a id="/about" href="/about"><i class='fas fa-address-book'></i> About Me</a>
        <a id="/contactme" href="/contactme"><i class='fas fa-envelope'></i> Contact</a>
        
        <div class="d-flex justify-content-center mt-3">
            <a href="https://www.facebook.com/ambre.fouvez" class="fa fa_logo fa-facebook"></a>
            <a href="https://www.instagram.com/ambre.fouvez/" class="fa fa_logo fa-instagram"></a>
        </div>`;

    // Add admin link.
    let user = getUserSessionData();
    if(!user){
        sidebarPage += `<a id="/login" class="login_icon" href="/login" data-uri="/login"><img src="assets/Images/stage_theater.png" alt="stage_theater"></a>`;
    }else {
        sidebarPage += `<a id="/myprofile" class="nav-item nav-link" href="/myprofile" data-uri="/myprofile">${user.userName}</a>
        <a id="/logout" class="nav-item nav-link" href="/logout" data-uri="/logout"><i class='fas fa-sign-out-alt'></i> Logout</a>
        <a id="/addMakeupArtist" class="nav-item nav-link" href="/addMakeupArtist" data-uri="/addMakeupArtist">Add a make-up artist</a>
        <a id="/addPhotographer" class="nav-item nav-link" href="/addPhotographer" data-uri="/addPhotographer">Add a photographer</a>
        <a id="/addTag" class="nav-item nav-link" href="/addTag" data-uri="/addTag">Add a tag</a>
        <a id="/photos" class="nav-item nav-link" href="/photos" data-uri="/photos">Photos</a>`;
    }
    if (user && user.isBoss) {
        sidebarPage += `<a id="/Boss" class="nav-item nav-link" href="/Boss" data-uri="/myprofile">Boss</a>
        <a id="/Boss2" class="nav-item nav-link" href="/Boss2" data-uri="/logout">Boss 2</a>`;
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
    document.getElementById("/bookP").addEventListener("click", onChooseBook);
    document.getElementById("/bookA").addEventListener("click", onChooseBook);
    document.getElementById("/bookC").addEventListener("click", onChooseBook);
    document.getElementById("/contactme").addEventListener("click", onNavigate);
    document.getElementById("/ArtisticCV").addEventListener("click", onNavigate);
    //document.getElementById("/demotape").addEventListener("click", onNavigate);
    if (user && user.isBoss) {
        document.getElementById("/addMakeupArtist").addEventListener("click", onNavigate);
        document.getElementById("/addPhotographer").addEventListener("click", onNavigate);
        document.getElementById("/addTag").addEventListener("click", onNavigate);
        document.getElementById("/logout").addEventListener("click", onNavigate);
        document.getElementById("/myprofile").addEventListener("click", onNavigate);
        document.getElementById("/photos").addEventListener("click", onNavigate);
    }else document.getElementById("/login").addEventListener("click", onNavigate);
};

const openSidebar = () => {
    document.getElementById("mySidenav").style.width = "50%";
    document.getElementById("page").style.opacity = "0.4";
    document.getElementById("overlay").style.width = "100%";
    document.getElementById("footerText").style.zIndex = "-1";
};

const closeSidebar = () => {
    document.getElementById("mySidenav").style.width = "0px";
    document.getElementById("page").style.opacity = "1";
    document.getElementById("overlay").style.width = "0%";
    setTimeout(() => {  
        document.getElementById("footerText").style.zIndex = "1";
    }, 500);
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

const onChooseBook = (e) => {
    e.preventDefault();

    // Set choice_of_book.
    /*if (document.activeElement.id === "/bookP") {
        user_me.choice_of_book = document.activeElement.name;
    } else if(document.activeElement.id === "/bookA"){
        user_me.choice_of_book = document.activeElement.name;
    } else if(document.activeElement.id === "/bookC") {
        user_me.choice_of_book = document.activeElement.name;
    }*/
    user_me.choice_of_book = document.activeElement.name;

    closeSidebar();
    RedirectUrl(document.activeElement.id.substr(0, document.activeElement.id.length - 1));
};

export default SidebarPage;