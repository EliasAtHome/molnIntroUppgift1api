package eliasathome.molnintrouppgift1.Controller;


import eliasathome.molnintrouppgift1.Models.Author;
import eliasathome.molnintrouppgift1.Service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Tag(name = "Authors", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors")
    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();

        return ResponseEntity.ok(authors);
    }
    @Operation(summary = "Get an author by ID", description = "Fetches a single author by their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Author>> getOneAuthor(@PathVariable long id) {
        Optional<Author> author = authorService.getOneAuthor(id);
        return ResponseEntity.ok(author);
    }

    @Operation(summary = "Create a new author", description = "Adds a new author to the system")
    @PostMapping("")
    public ResponseEntity<Author> createNewAuthor(@RequestBody Author newAuthor) {
        Author author = authorService.saveAuthor(newAuthor);

        return ResponseEntity.ok(author);
    }

    @Operation(summary = "Update an existing author", description = "Updates an existing author's information by their ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Author> updateOneAuthor(@PathVariable Long id,
                                               @RequestBody Author newAuthor) {
        Author patchedAuthor = authorService.patchAuthor(newAuthor, id);

        return ResponseEntity.ok(patchedAuthor);
    }

    @Operation(summary = "Delete an author", description = "Removes an author from the system by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneAuthor(@PathVariable long id) {
        authorService.removeAuthor(id);

        return ResponseEntity.ok("Removed Successfully!");
    }

}
