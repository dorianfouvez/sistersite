const ErrorNavigationPage = (err) => {
    let errorPage = `<div class="text-center text-danger">`;
    if (!err) errorPage += `<p>Il y a eu une erreur.</p>`;
    else if (!err.message) errorPage += `<p>${err}</p>`;
    else errorPage += `<p>${err.message}</p>`;
    errorPage += `<img src="https://www.disneypixar.fr/img/ui/anim_stitch.gif" alt="Stitch hawaien">
    </div>`;

    let page = document.querySelector("#page");
    return (page.innerHTML = errorPage);
};

export default ErrorNavigationPage;