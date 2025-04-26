package com.example.demo.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.final_project.service.CharacterService;
import com.example.demo.final_project.service.MangaService;
import com.example.demo.final_project.model.Manga;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @Autowired
    private CharacterService pgService;

    @GetMapping
    public String index(@RequestParam(value = "nome", required = false) String title, Model model) {
        if (title != null) {
            model.addAttribute("manga", mangaService.searchMangaByTitle(title));
        } else {
            model.addAttribute("manga", mangaService.getAllMangas());
        }
        return "manga/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        try {
            Manga manga = mangaService.getMangaById(id);
            model.addAttribute("manga", manga);
            model.addAttribute("pg", manga.getCharacters());
            return "manga/show";
        } catch (Exception e) {
            // Gestione dell'eccezione se il manga non viene trovato
            return "redirect:/manga"; // Reindirizza alla lista dei manga
        }

    }

    // rotte per la creazione del manga

    @GetMapping("/create") // prendo la pagina di creazione
    public String create(Model model) {
        model.addAttribute("manga", new Manga());
        return "manga/create";
    }

    @PostMapping("/create") // creo il manga
    public String create(Model model, @Valid @ModelAttribute("manga") Manga formManga, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "manga/create";
        }
        mangaService.createManga(formManga);
        return "redirect:/manga"; // Reindirizza alla lista dei manga dopo la creazione
    }

    // rotte per la modifica del manga
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Integer id) {
        try {
            Manga manga = mangaService.getMangaById(id);
            model.addAttribute("manga", manga);
            return "manga/update";
        } catch (Exception e) {
            return "redirect:/manga";
        }
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("manga") Manga formManga, BindingResult bindingResult,@PathVariable Integer id) {
      
        if (bindingResult.hasErrors()) {
            return "manga/update";
        }
        mangaService.updateManga(id, formManga);
        return "redirect:/manga"; // Reindirizza alla lista dei manga
    }

    // rotte per la cancellazione del manga
    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id) {
        try {
            mangaService.deleteManga(id); // Elimina il manga
        } catch (Exception e) {
            // Gestione dell'eccezione se il manga non viene trovato
            return "redirect:/manga"; // Reindirizza alla lista dei manga
        }
        // Reindirizza alla lista dei manga dopo la cancellazione
        return "redirect:/manga";
    }

}
