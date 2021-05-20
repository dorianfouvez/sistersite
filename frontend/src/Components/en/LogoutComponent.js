import { RedirectUrl } from "../Router.js";
import {removeSessionData} from "../../utils/session.js";
import NavbarEn from "./Navbar.js";
import SidebarEn from "./Sidebar.js";

const Logout = () => {
  removeSessionData();
  // re-render the navbar for a non-authenticated user
  NavbarEn();
  SidebarEn();
  RedirectUrl("/");
};

export default Logout;
