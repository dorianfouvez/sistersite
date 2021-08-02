import { user_me } from "../..";
import { onError } from "../../utils/render";
import { API_URL } from "../../utils/server";
import { RedirectUrl } from "../Router";

const BookPage = () => {
    if(!user_me || !user_me.choice_of_book){
        RedirectUrl("/");
    }else{
        let bookPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Book</center></h1></div>
        <span id="loading"></span>`;

        let page = document.querySelector("#page");
        page.innerHTML = bookPage;
        fetch(API_URL + "photos/book/" + user_me.choice_of_book, {
            method: "GET",
            headers: {
            "Content-Type": "application/json",
            },
        }).then((response) => {
            if (!response.ok) {
            return response.text().then((err) => onError(err));
            } else return response.json().then((data) => onBook(data.book));
        });
    }
};

const onBook = (book) => {
    let bookPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Book</center></h1></div>
    <div class="row">`;
    book.forEach(photo => {
        bookPage += `<div class="create"><img id="photo${photo.id}" src="${photo.picture}" class="photo_book" alt="${photo.name}"></div>`;
    });
    bookPage += `
        <div class="create"><img src="../assets/Images/IMG_4095 (S).jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/IMG_8470.jpeg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/Disneyland hotel.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/118835666_3182691398505562_2598143657728591896_n.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
        <div class="create"><img src="../assets/Images/thumbnail_IMG_5980.jpg" class="photo_book" alt="Ambre Fouvez"></div>
    </div>
    
    <br><hr style="height:20px;border-width:0;color:black;background-color:black"><br>

    <div class="reset">
        <div class="pin_container">`;
        book.forEach(photo => {
            bookPage += `<div><img id="photo${photo.id}" src="${photo.picture}" alt="${photo.name}"></div>`;
        });
        bookPage += `
            <div><img src="../assets/Images/IMG_4095 (S).jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/IMG_8470.jpeg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/Disneyland hotel.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/118835666_3182691398505562_2598143657728591896_n.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
            <div><img src="../assets/Images/thumbnail_IMG_5980.jpg" alt="Ambre Fouvez"></div>
        </div>
    </div>`;

    let page = document.querySelector("#page");
    page.innerHTML = bookPage;
};

export default BookPage;