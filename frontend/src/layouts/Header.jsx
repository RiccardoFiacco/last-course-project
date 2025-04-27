import { NavLink } from "react-router-dom";
import GlobalContext from "../contexts/GlobalContext";

function Header() {
  return (
    <header className="container mx-auto mt-6 bg-blue-400 rounded-2xl shadow-md">
      <div className="flex justify-between items-center px-6 py-4">
        <NavLink to="/">
          <div className="text-2xl font-bold hover:text-amber-200 transition">
            Fumetteria
          </div>
        </NavLink>
        <nav>
          <ul className="flex gap-6 items-center">
            <NavLink to="/find">
              <li className="bg-amber-300 hover:bg-amber-400 font-semibold py-2 px-4 rounded-lg shadow transition">
                Cerca
              </li>
            </NavLink>
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Header;
