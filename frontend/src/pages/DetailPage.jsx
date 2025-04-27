import { useEffect, useContext } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import GlobalContext from "../contexts/GlobalContext";

export function DetailPage() {
  const { manga, setManga } = useContext(GlobalContext);
  const { id } = useParams(); // Get the manga ID from the URL
  const { author, title, description, characters } = manga;
  useEffect(() => {
    async function fetchData() {
      const result = await axios.get(`http://localhost:8080/api/manga/${id}`);
      setManga(result.data);
    }
    fetchData();
  }, []);

  return (
    <div className="container mx-auto mt-10 px-4">
      <div className="mb-8">
        <h2 className="text-3xl font-bold text-gray-800">
          Titolo dell'opera: {title}
        </h2>
        <p className="mt-4 text-gray-600 text-lg">Descrizione: {description}</p>
        <p className="mt-2 text-gray-600 text-lg">Autore: {author}</p>
      </div>

      {characters && characters.length > 0 ? (
        <div className="grid gap-6 md:grid-cols-2">
          {characters.map((character, index) => (
            <div
              key={index}
              className="bg-white hover:bg-blue-100 transition transform hover:scale-105 shadow-lg rounded-2xl p-6 flex flex-col"
            >
              <h3 className="text-2xl font-semibold text-gray-800">
                {character.name}
              </h3>
              <p className="mt-3 text-gray-600">{character.description}</p>
            </div>
          ))}
        </div>
      ) : (
        <div className="text-center mt-6">
          <p className="text-gray-500">Loading...</p>
        </div>
      )}
    </div>
  );
}
