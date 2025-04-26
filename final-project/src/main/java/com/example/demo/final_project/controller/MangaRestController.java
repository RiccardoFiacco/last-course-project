package com.example.demo.final_project.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.final_project.model.Manga;
import com.example.demo.final_project.service.MangaService;

@RestController
@RequestMapping("/api/manga")
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


}
