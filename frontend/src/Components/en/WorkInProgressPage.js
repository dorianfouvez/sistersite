import { fixToBottomFooter } from "../../utils/render";

let workInProgressPage = `<div class="text-center">
    <h4 class="mt-2">Work In progress</h4>
    <img src="https://www.disneypixar.fr/img/ui/anim_zootopie.gif" alt="flashFromZootopie">
</div>`;

const WorkInProgressPage = () => {
    fixToBottomFooter();
    let page = document.querySelector("#page");
    return (page.innerHTML = workInProgressPage);
};

export default WorkInProgressPage;