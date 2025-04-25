package com.example.demo.final_project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.final_project.model.Manga;
import java.util.List;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
    // Interfaccia per l'accesso ai dati dei manga
    // Estende JpaRepository per fornire metodi CRUD predefiniti
    // Puoi aggiungere metodi personalizzati qui se necessario
    public List<Manga> findByTitleContainingIgnoreCase(String title); // Metodo per cercare manga per titolo
}
