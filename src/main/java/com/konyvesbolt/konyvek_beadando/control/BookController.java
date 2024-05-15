package com.konyvesbolt.konyvek_beadando.control;

import com.konyvesbolt.konyvek_beadando.model.BookEntity;
import com.konyvesbolt.konyvek_beadando.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/add")
    public String addBook(@RequestBody BookEntity book) {
        bookRepository.save(book);
        return "Book added successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully!";
    }

    @GetMapping("/title/{title}")
    public Optional<BookEntity> findBookByTitle(@PathVariable String title) {
        return bookRepository.findByTitlesOrId(title, null);
    }

    @GetMapping("/genre/{genre}")
    public List<BookEntity> findBooksByGenre(@PathVariable String genre) {
        return bookRepository.findByGenre(genre);
    }
}
