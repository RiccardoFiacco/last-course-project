import { useContext } from "react";
import GlobalContext from "../contexts/GlobalContext";

function Searchbar() {
  const { search, setSearch } = useContext(GlobalContext);

  return (
    <div className="rounded-lg">
      <input
        onChange={(e) => setSearch(e.target.value)}
        className="h-10 w-full px-4 rounded-lg border-2 border-gray-300 focus:outline-none focus:ring-2 focus:ring-amber-400 focus:border-transparent transition placeholder-gray-400"
        type="text"
        value={search}
        placeholder="Cerca..."
      />
    </div>
  );
}

export default Searchbar;
