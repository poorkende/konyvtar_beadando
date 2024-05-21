package com.konyvesbolt.konyvek_beadando.control;

import com.konyvesbolt.konyvek_beadando.model.BookEntity;
import com.konyvesbolt.konyvek_beadando.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    /**
     * A BookRepository interfész példánya.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Add a new book.
     *
     * @param book The book to add.
     * @return A success message.
     */
    @PostMapping("/add")
    public String addBook(@RequestBody final BookEntity book) {
        bookRepository.save(book);
        return "Book added successfully!";
    }

    /**
     * Delete a book by ID.
     *
     * @param id The ID of the book to delete.
     * @return A success message.
     */
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable final Long id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully!";
    }

    /**
     * Find a book by its title.
     *
     * @param title The title of the book to find.
     * @return An Optional containing the book if found, empty otherwise.
     */
    @GetMapping("/title/{title}")
    public Optional<BookEntity> findBookByTitle(@PathVariable final String title) {
        return bookRepository.findByTitlesOrId(title, null);
    }

    /**
     * Find books by genre.
     *
     * @param genre The genre to search for.
     * @return A list of books with the given genre.
     */
    @GetMapping("/genre/{genre}")
    public List<BookEntity> findBooksByGenre(@PathVariable final String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Find books by author.
     *
     * @param author The author to search for.
     * @return A list of books by the given author.
     */
    @GetMapping("/author/{author}")
    public List<BookEntity> findBooksByAuthor(@PathVariable final String author) {
        return bookRepository.findByAuthor(author);
    }
}
