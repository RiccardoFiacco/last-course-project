package com.example.demo.final_project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.final_project.model.Character;

/**
 * interfaccia per l'accesso ai dati dei personaggi
 * estende JpaRepository per le operazioni CRUD sui personaggi
 * non è necessario implementare metodi specifici, JpaRepository fornisce già le
 * implementazioni
 * 
 * @return
 */

public interface CharacterRepository extends JpaRepository<Character, Integer> {

    public Optional<Character> findCharacterById(Integer id);

}
