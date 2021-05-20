import { RedirectUrl } from "../Router.js";
import { removeSessionData } from "../../utils/session.js";
import NavbarFr from "./Navbar.js";
import SidebarFr from "./Sidebar.js";

const Logout = () => {
  removeSessionData();
  // re-render the navbar for a non-authenticated user
  NavbarFr();
  SidebarFr();
  RedirectUrl("/");
};

export default Logout;
