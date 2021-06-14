import { fixToBottomFooter } from "../../utils/render";

const CVPage = () => {
    fixToBottomFooter();
    page.innerHTML = `<div class="text-center"><h4 class="mt-2">Artistic Curriculum Vitae</h4></div>
    <div class="loader mx-auto"></div>`;
};

export default CVPage;