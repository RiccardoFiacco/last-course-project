import { NavLink } from "react-router-dom";
export function Card({ manga }) {
  const { id, title, description } = manga;
  return (
    <NavLink to={`/manga/${id}`}>
      <div
        key={id}
        className="bg-white hover:bg-blue-100 transition transform hover:scale-105 shadow-lg rounded-2xl p-6 mt-6 flex justify-between items-start gap-4 h-[200px]"
      >
        <div>
          <h2 className="text-2xl font-bold text-gray-800">{title}</h2>
          <p className="mt-3 text-gray-600">{description}</p>
        </div>
      </div>
    </NavLink>
  );
}
