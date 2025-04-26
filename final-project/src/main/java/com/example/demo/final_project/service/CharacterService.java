package com.example.demo.final_project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.final_project.model.Character;
import com.example.demo.final_project.repository.CharacterRepository;

@Service
public class CharacterService {
    // classe di servizio per la gestione dei personaggi
    // contiene metodi per le operazioni CRUD sui personaggi
    // utilizza il repository CharacterRepository per l'accesso ai dati
    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllPgs() {
        // restituisce la lista di tutti i manga
        return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(Integer id){
        // restituisce un personaggio in base all'ID
        return characterRepository.findCharacterById(id);
    }

    public Character upsertCharacter(Character character) { // crea o aggiorna un personaggio e lo restituisce
        // se il personaggio ha un ID, lo aggiorna, altrimenti lo crea
        // crea un nuovo personaggio e lo restituisce
        return characterRepository.save(character);
    }

    public void deleteCharacter(Integer id) {
        // elimina un personaggio in base all'ID
        if (!characterRepository.existsById(id)) {
            return; // se il personaggio non esiste, non fare nulla
        }
        characterRepository.deleteById(id);
    }

}
