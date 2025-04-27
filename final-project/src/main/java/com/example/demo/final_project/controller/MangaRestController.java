package com.example.demo.final_project.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.final_project.model.Manga;
import com.example.demo.final_project.service.MangaService;

@RestController
@RequestMapping("/api/manga")
@CrossOrigin(origins = "http://localhost:5173")
public class MangaRestController {
    @Autowired
    private MangaService mangaService;

    @GetMapping
    public List<Manga> getAllMangas() {
        // restituisce la lista di tutti i manga
        return mangaService.getAllMangas();
    }

    @GetMapping("/search")
    public List<Manga> searchMangaByTitle(String title) {
        // restituisce una lista di manga in base al titolo
        return mangaService.searchMangaByTitle(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Integer id) throws Exception {
        // restituisce un manga in base all'ID
        try {
            if (mangaService.findMangaById(id).isEmpty()) {
                return new ResponseEntity<Manga>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Manga>(mangaService.getMangaById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Manga not found with id: " + id, e);
        }
    }
}
