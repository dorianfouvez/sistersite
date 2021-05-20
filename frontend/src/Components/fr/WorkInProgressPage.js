let workInProgressPage = `<div class="text-center">
    <h4 class="mt-2">Page en travaux</h4>
    <img src="https://www.disneypixar.fr/img/ui/anim_zootopie.gif" alt="flashFromZootopie">
</div>`;

const WorkInProgressPage = () => {
    let page = document.querySelector("#page");
    return (page.innerHTML = workInProgressPage);
};

export default WorkInProgressPage;