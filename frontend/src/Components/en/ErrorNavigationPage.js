import { fixToBottomFooter } from "../../utils/render";

const ErrorNavigationPage = (err) => {
    fixToBottomFooter();
    let errorPage = `<h4 class="text-dark text-center pt-5">Error Navigation</h4>
        <div class="text-center text-danger">`;
    if (!err) errorPage += `<p>There was an error.</p>`;
    else if (!err.message) errorPage += `<p>${err}</p>`;
    else errorPage += `<p>${err.message}</p>`;
    errorPage += `<img src="https://www.disneypixar.fr/img/ui/anim_stitch.gif" alt="Stitch hawaien">
    </div>`;

    let page = document.querySelector("#page");
    return (page.innerHTML = errorPage);
};

export default ErrorNavigationPage;