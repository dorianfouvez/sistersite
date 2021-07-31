import { user_me } from "../..";
import { RedirectUrl } from "../Router";

const BookPage = () => {
    console.log();
    if(!user_me || !user_me.choice_of_book){
        RedirectUrl("/");
    }else{
        console.log(user_me.choice_of_book);
        // TODO Fetch par rapport au choice_of_book.

        let bookPage = `<div class="mt-2 mb-3 pt-5"><h1><center>Book</center></h1></div>
        <div class="row">
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
            <div class="pin_container">
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
    }
};

export default BookPage;