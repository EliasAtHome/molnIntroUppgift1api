package eliasathome.molnintrouppgift1.Controller;

import eliasathome.molnintrouppgift1.Models.Author;
import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Service.AuthorService;
import eliasathome.molnintrouppgift1.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BooksControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllBooks() {
        // Arrange
        Books book = new Books();
        book.setTitle("Test Book");
        book.setAuthor(new Author("Test Author", 30));

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
        // Arrange
        Map<String, Object> request = new HashMap<>();
        request.put("title", "New Book");
        request.put("authorName", "New Author");
        request.put("isbn", "123456789");

        Author author = new Author();
        author.setId(1L);
        author.setName("New Author");

        when(authorService.findByName("New Author")).thenReturn(author);

        Books newBook = new Books();
        newBook.setId(1L);
        newBook.setTitle("New Book");
        newBook.setIsbn("123456789");
        newBook.setAuthor(author); // Ensure the author is set
        when(bookService.saveBook(any(Books.class))).thenReturn(newBook);

        ResponseEntity<Books> response = booksController.createNewBook(request);

        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("New Book", response.getBody().getTitle(), "Book title should match the expected title");
        assertEquals(author.getId(), response.getBody().getAuthor().getId(), "Author ID should match the expected ID");
        assertEquals(author.getName(), response.getBody().getAuthor().getName(), "Author name should match the expected name");
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
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().isPresent());
        assertEquals("Test Book", response.getBody().get().getTitle());

    }
}