package eliasathome.molnintrouppgift1.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Models.Author; // Importera Author-modellen
import eliasathome.molnintrouppgift1.Service.BookService;
import eliasathome.molnintrouppgift1.Service.AuthorService; // Importera AuthorService
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Operation(summary = "Get all books", description = "Retrieves a list of all books")
    @GetMapping("")
    public ResponseEntity<List<Books>> getAllBooks() {

        List<Books> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Get a book by ID", description = "Fetches a single book by its unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Books>> getOneBook(@PathVariable long id) {

        Optional<Books> book = bookService.getOneBook(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    public ResponseEntity<Books> createNewBook(@RequestBody Map<String, Object> request) {
        String title = (String) request.get("title");
        String authorName = (String) request.get("authorName");
        String isbn = (String) request.get("isbn");

        Books newBook = new Books();
        newBook.setTitle(title);
        newBook.setIsbn(isbn);

        Author author = authorService.findByName(authorName);
        if (author == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        newBook.setAuthor(author);
        Books book = bookService.saveBook(newBook);

        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Update an existing book", description = "Updates an existing book's information by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateOneBook(@PathVariable Long id,
                                               @RequestBody Map<String, Object> request) {

        Optional<Books> optionalBook = bookService.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Books bookToUpdate = optionalBook.get();
        if (request.containsKey("title")) {
            bookToUpdate.setTitle((String) request.get("title"));
        }
        if (request.containsKey("isbn")) {
            bookToUpdate.setIsbn((String) request.get("isbn"));
        }
        if (request.containsKey("authorName")) {
            String authorName = (String) request.get("authorName");
            Author author = authorService.findByName(authorName);
            if (author != null) {
                bookToUpdate.setAuthor(author);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        Books updatedBook = bookService.saveBook(bookToUpdate);
        return ResponseEntity.ok(updatedBook);
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the system by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneBook(@PathVariable long id) {

        bookService.removeBook(id);
        return ResponseEntity.ok("Removed Successfully!");
    }
}