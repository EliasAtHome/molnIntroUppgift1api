package eliasathome.molnintrouppgift1.Service;

import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Repo.BooksRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookService bookService;
    private BooksRepo booksRepo;

    @BeforeEach
    void setUp() {
        booksRepo = Mockito.mock(BooksRepo.class);
        bookService = new BookService(booksRepo);
    }

    @Test
    void getAllBooks() {
        // Arrange
        Books book = new Books();
        book.setTitle("Test Book");

        when(booksRepo.findAll()).thenReturn(Collections.singletonList(book));

        // Act
        var books = bookService.getAllBooks();

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    public void testPatchBook_UpdatesTitleOnly() {
        // Arrange
        Long bookId = 1L;
        Books existingBook = new Books();
        existingBook.setId(bookId);
        existingBook.setTitle("Old Title");
        existingBook.setIsbn("12345");

        Books updatedBook = new Books();
        updatedBook.setTitle("New Title");
        updatedBook.setIsbn(null); // Ingen ISBN anges, så den ska förbli oförändrad

        // Mocka repository
        when(booksRepo.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(booksRepo.save(existingBook)).thenReturn(existingBook);

        // Act
        Books result = bookService.patchBook(updatedBook, bookId);

        // Assert
        assertEquals("New Title", result.getTitle()); // Titeln ska ha uppdaterats
        assertEquals("12345", result.getIsbn());     // ISBN ska förbli oförändrat
    }

    @Test
    void removeBook() {
        // Arrange
        Long bookId = 1L;

        // Act
        bookService.removeBook(bookId);

        // Assert
        verify(booksRepo, times(1)).deleteById(bookId); // Kontrollera att deleteById anropas en gång med rätt ID
    }
}