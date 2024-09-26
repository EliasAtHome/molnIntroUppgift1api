package eliasathome.molnintrouppgift1.Controller;

import eliasathome.molnintrouppgift1.Models.Author;
import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    private BooksController booksController;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = Mockito.mock(BookService.class);
        booksController = new BooksController(bookService);
    }

    @Test
    void getAllBooks() {
        // Arrange
        Books book = new Books();
        book.setTitle("Test Book");
        book.setAuthor(new Author("Test Author"));

        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));

        // Act
        ResponseEntity<List<Books>> response = booksController.getAllBooks();

        // Assert
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Book", response.getBody().get(0).getTitle());
    }

    @Test
    void createNewBook() {
        Books newBook = new Books();
        newBook.setTitle("New Book");
        newBook.setAuthor(new Author("New Author"));

        when(bookService.saveBook(any())).thenReturn(newBook);

        // Act
        ResponseEntity<Books> response = booksController.createNewBook(newBook);

        // Assert
        assertNotNull(response.getBody());
        assertEquals("New Book", response.getBody().getTitle());
    }

    @Test
    void getOneBook() {
        Books book = new Books();
        book.setId(1L);
        book.setTitle("Test Book");

        when(bookService.getOneBook(1L)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Optional<Books>> response = booksController.getOneBook(1L);

        // Assert
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful()); // Kontrollera att svaret är en 2xx-status
        assertTrue(response.getBody().isPresent()); // Kontrollera att kroppen innehåller en bok
        assertEquals("Test Book", response.getBody().get().getTitle()); // Kontrollera att titeln stämmer

    }
}