

/**
 * setLayout allows to display specific information in an HTML template
 * containing those paramters as id to text elements (h4, h5, title)
 * @param {headerTitle} headerTitle
 * @param {footerText} footerText
 */
function setLayout(headerTitle, footerText) {
  document.querySelector("#footerText").innerText = footerText;
}


function setFooter(){
  let footerDiv = document.querySelector("#footerText");

  let footerText = `<a href="https://www.facebook.com/ambre.fouvez" class="fa fa-facebook"></a>
  <a href="https://www.instagram.com/ambre.fouvez/" class="fa fa-instagram"></a>
  <!--<a href="https://www.youtube.com/channel/UCEdtOcwxGX9viDs4G9AAHeg" class="fa fa-youtube"></a>
  <a href="https://www.twitter.com/barackobama" class="fa fa-twitter"></a>-->`;

  footerDiv.innerHTML = footerText;
}

// named export
export { setLayout, setFooter };