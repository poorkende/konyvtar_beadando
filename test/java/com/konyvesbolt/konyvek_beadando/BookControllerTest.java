package com.konyvesbolt.konyvek_beadando;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konyvesbolt.konyvek_beadando.control.BookController;
import com.konyvesbolt.konyvek_beadando.model.BookEntity;
import com.konyvesbolt.konyvek_beadando.repository.BookRepository;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    // testAddBook kihagyasa
    // @Test
    // public void testAddBook() throws Exception {
    //     // Given
    //     BookEntity book = new BookEntity();
    //     book.setTitles("Test Title");
    //     book.setAuthor("Test Author");
    //     book.setReleased(2022);
    //     book.setGenre("Test Genre");
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String json = objectMapper.writeValueAsString(book);
    //
    //     // When
    //     mockMvc.perform(MockMvcRequestBuilders.post("/api/books/add")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(json))
    //             .andExpect(MockMvcResultMatchers.status().isOk());
    //
    //     // Then
    //     verify(bookRepository).save(book);
    // }

    @Test
    public void testDeleteBook() throws Exception {
        // Given
        Long id = 1L;

        // When
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        verify(bookRepository).deleteById(id);
    }

    @Test
    public void testFindBookByTitle() throws Exception {
        // Given
        String title = "Test Title";
        BookEntity book = new BookEntity();
        book.setTitles(title);
        when(bookRepository.findByTitlesOrId(title, null)).thenReturn(Optional.of(book));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/title/{title}", title))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titles").value(title));

        // Then
        verify(bookRepository).findByTitlesOrId(title, null);
    }

    @Test
    public void testFindBooksByGenre() throws Exception {
        // Given
        String genre = "Test Genre";
        BookEntity book1 = new BookEntity();
        book1.setTitles("Test Title 1");
        book1.setGenre(genre);
        BookEntity book2 = new BookEntity();
        book2.setTitles("Test Title 2");
        book2.setGenre(genre);
        when(bookRepository.findByGenre(genre)).thenReturn(List.of(book1, book2));

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/genre/{genre}", genre))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(genre))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(genre));

        // Then
        verify(bookRepository).findByGenre(genre);
    }
}
