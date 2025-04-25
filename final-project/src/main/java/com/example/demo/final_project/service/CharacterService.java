package com.example.demo.final_project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.final_project.model.Character;
import com.example.demo.final_project.model.Manga;
import com.example.demo.final_project.repository.CharacterRepository;

@Service
public class CharacterService {
    // classe di servizio per la gestione dei personaggi
    // contiene metodi per le operazioni CRUD sui personaggi
    // utilizza il repository CharacterRepository per l'accesso ai dati
    @Autowired
    private CharacterRepository characterRepository;

    public Character createCharacter(Character character) {
        // crea un nuovo personaggio e lo restituisce
        return characterRepository.save(character);
    }

    public List<Character> getAllPgs() {
        // restituisce la lista di tutti i manga
        return characterRepository.findAll();
    }
    public void deleteCharacter(Integer id) throws Exception {
        // elimina un personaggio in base all'ID
        if (!characterRepository.existsById(id)) {
            throw new Exception();
        }
        characterRepository.deleteById(id);
    }

    public Optional<Character> getCharacterById(Integer id) throws Exception {
        // restituisce un personaggio in base all'ID
        return characterRepository.findCharacterById(id);
    }
}
