package eliasathome.molnintrouppgift1.Service;

import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Repo.BooksRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BooksRepo booksRepo;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
        updatedBook.setIsbn(null);


        when(booksRepo.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(booksRepo.save(existingBook)).thenReturn(existingBook);

        // Act
        Books result = bookService.patchBook(updatedBook, bookId);

        // Assert
        assertEquals("New Title", result.getTitle());
        assertEquals("12345", result.getIsbn());
    }

    @Test
    void removeBook() {

        Long bookId = 1L;

        bookService.removeBook(bookId);

        verify(booksRepo, times(1)).deleteById(bookId); // deleteById anropas en gång med rätt ID
    }
}