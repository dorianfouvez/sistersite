let workInProgressPage = `<div class="d-flex justify-content-center mt-2 mb-3 pt-5">
    <h4>Work In progress</h4>
</div>
<div class="d-flex justify-content-center">
    <img src="https://www.disneypixar.fr/img/ui/anim_zootopie.gif" alt="flashFromZootopie">
</div>`;

const WorkInProgressPage = () => {
    let page = document.querySelector("#page");
    return (page.innerHTML = workInProgressPage);
};

export default WorkInProgressPage;