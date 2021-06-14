import { fixToBottomFooter, onError } from "../../utils/render";
import { API_URL } from "../../utils/server.js";
import { getTokenSessionData } from "../../utils/session.js";

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
            } else return response.json().then((data) => console.log(data.cv));
        });
    }else{
        fetch(API_URL + "curriculumVitea/0", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        }).then((response) => {
            if (!response.ok) {
                return response.text().then((err) => onError(err));
            } else return response.json().then((data) => console.log(data.cv));
        });
    }
    
};

export default CVPage;