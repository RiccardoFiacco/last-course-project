import { useEffect } from "react";
import GlobalContext from "../contexts/GlobalContext";
import { useContext } from "react";
import { WithFetchHoc } from "../Hoc/WithFetchHoc";
import { Card } from "../Components/Card";
export function Home({ fetch }) {
  const { mangas, setMangas } = useContext(GlobalContext);

  useEffect(() => {
    async function fetchData() {
      //senza di async non funziona
      const data = await fetch(); //perche dentro la funzione fetch è async
      setMangas(data);
    }
    fetchData();
  }, []);

  return (
    <div className="container mx-auto mt-10 px-4">
      <div className="text-center mb-10">
        <h1 className="text-4xl font-bold text-gray-800">
          Welcome to Manga Library
        </h1>
        <p className="mt-4 text-gray-600 text-lg">
          Discover your favorite manga series and characters.
        </p>
      </div>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3 mb-5">
        {mangas ? (
          mangas.map((manga, i) => (
            <div
              key={i}
            >
              <Card manga={manga} />
            </div>
          ))
        ) : (
          <div className="col-span-full text-center">
            <p className="text-gray-500">Loading...</p>
          </div>
        )}
      </div>
    </div>
  );
}

const FinalHome = WithFetchHoc(Home, "http://localhost:8080/api/manga");
export { FinalHome };
