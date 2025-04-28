package com.example.demo.final_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.final_project.model.Character;
import com.example.demo.final_project.service.CharacterService;
import com.example.demo.final_project.service.MangaService;

import jakarta.validation.Valid;

/**
 * classe di controller per la gestione delle request sui personaggi
 * utilizza il servizio CharacterService per l'accesso ai dati
 * @return
 */
@Controller
@RequestMapping("/admin/pg")
public class CharacterController {

    @Autowired
    private CharacterService pgService;

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public String index(Model model) {
        try {
            model.addAttribute("pgs", pgService.getAllPgs());
            return "character/index";
        } catch (Exception e) {
            model.addAttribute("error", true);// flag per eventuale errore
            return "character/index"; // Reindirizza alla lista dei manga
        }
    }

    @GetMapping("/{id}")
    public String getPg(Model model, @PathVariable Integer id) {

        Optional<Character> pg = pgService.getCharacterById(id);
        if (pg.isPresent()) {
            model.addAttribute("pg", pg.get());
            return "character/show";
        }

        return "redirect:/admin/pg"; // Reindirizza alla lista dei manga

    }

    // rotte per la creazione del p

    @GetMapping("/create") 
    public String create(Model model) {
        model.addAttribute("pg", new Character());
        model.addAttribute("manga", mangaService.getAllMangas());// per una select
        return "character/create";
    }

    @PostMapping("/create") 
    public String create(Model model, @Valid @ModelAttribute("pg") Character pg, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("manga", mangaService.getAllMangas()); // se non ripasso potrebbero
            return "character/create";
        }
        pgService.upsertCharacter(pg);
        return "redirect:/admin/pg";
    }

    // rotte per la modifica del manga
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Integer id) {
        try {
            Optional<Character> pg = pgService.getCharacterById(id);
            if (pg.isPresent()) {
                model.addAttribute("pg", pg.get());
                model.addAttribute("manga", mangaService.getAllMangas());
            }
            return "character/update";
        } catch (Exception e) {
            return "redirect:/admin/pg";
        }
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @Valid @ModelAttribute("manga") Character formPg, BindingResult bindingResult,
            @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return "character/update";
        }
        pgService.upsertCharacter(formPg);
        return "redirect:/admin/pg"; // Reindirizza alla lista dei manga dopo la creazione
    }

    // rotte per la cancellazione del manga
    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id) {
        try {
            pgService.deleteCharacter(id); // Elimina il manga
        } catch (Exception e) {
            // Gestione dell'eccezione se il manga non viene trovato
            return "redirect:/admin/pg"; // Reindirizza alla lista dei manga
        }
        // Reindirizza alla lista dei manga dopo la cancellazione
        return "redirect:/admin/pg";
    }
}
