const API_URL = "/api/";

const ALERT_BOX = (messageBoard, errorMessage) => {
    messageBoard.innerHTML = '<div class="d-flex justify-content-center"><div id="alertBox" class="alert alert-danger alert-dismissible mt-2 max_wight_800px"></div></div>';
    let alertBox = document.querySelector("#alertBox");
    alertBox.innerText = errorMessage;
    // Add close button of alert
    alertBox.innerHTML += '<button type="button" class="close" data-dismiss="alert">&times;</button>';
};

const SUCCESS_BOX = (messageBoard, errorMessage) => {
    messageBoard.innerHTML = `<div class="d-flex justify-content-center"><div id="successBox" class="alert alert-success alert-dismissible mt-2 max_wight_800px"><i class='fas fa-check'></i> </div></div>`;
    let successBox = document.querySelector("#successBox");
    successBox.innerHTML += errorMessage;
    // Add close button of alert
    successBox.innerHTML += '<button type="button" class="close" data-dismiss="alert">&times;</button>';
}

export { API_URL, ALERT_BOX, SUCCESS_BOX };