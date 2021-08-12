import { user_me } from "../..";
import { RedirectUrl } from "../Router";

const UpdateUserPage = (data) => {
    if(!data || !data.user){
        RedirectUrl("/myprofile");
    }
    console.log(data, user_me.itself);
    let user = data.user;
    let photo = user.profilePicture;
    let address = user.address;
    let hairColor = user.hairColor;
    let hairSize = user.hairSize;
    let eye = user.eye;
    let firstNationality = user.firstNationality;
    let secondNationality = user.secondNationality;

    // Fetch list of addresses, colors, sizes, photos, nationality.

    let updateUserPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5"><h4>Update My profile</h4></div>
    <form id="updateUserForm">
        <input id="" type="hidden" value="" required>
        <div class="d-flex justify-content-center photo">
            <img id="profilePicture" src="${photo.picture}" class="photo" alt="${photo.name}">
        </div>
        <input id="" type="hidden" value="" required>
        <input id="" type="hidden" value="" required>
        <input id="" type="hidden" value="" required>
    </form>`;

    let page = document.querySelector("#page");
    page.innerHTML = updateUserPage;

    document.getElementById("updateUserForm").addEventListener("submit", onSubmit);
};

const onSubmit = (e) => {
    e.preventDefault();
};

const onUpdate = (e) => {

};

export default UpdateUserPage;