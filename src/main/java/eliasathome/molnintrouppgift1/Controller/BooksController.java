package eliasathome.molnintrouppgift1.Controller;


import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {

    private final BookService bookService;

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


    @Operation(summary = "Create a new book", description = "Adds a new book to the system")
    @PostMapping("")
    public ResponseEntity<Books> createNewBook(@RequestBody Books newBook) {
        Books book = bookService.saveBook(newBook);

        return ResponseEntity.ok(book);
    }

    @Operation(summary = "Update an existing book", description = "Updates an existing book's information by its ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Books> updateOneBook(@PathVariable Long id,
                                               @RequestBody Books newBook) {
        Books patchedBook = bookService.patchBook(newBook, id);

        return ResponseEntity.ok(patchedBook);
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the system by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneBook(@PathVariable long id) {
        bookService.removeBook(id);

        return ResponseEntity.ok("Removed Successfully!");
    }
}
