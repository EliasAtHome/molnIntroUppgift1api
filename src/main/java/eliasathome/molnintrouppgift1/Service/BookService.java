package eliasathome.molnintrouppgift1.Service;

import eliasathome.molnintrouppgift1.Controller.BooksController;
import eliasathome.molnintrouppgift1.Models.Author;
import eliasathome.molnintrouppgift1.Models.Books;
import eliasathome.molnintrouppgift1.Repo.BooksRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BookService {



    private final AuthorService authorService;
    private final BooksRepo booksRepo;

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(Long id) {
        return Optional.of(booksRepo.findById(id).orElse(new Books()));
    }

    public Books saveBook(Books book) {
        // Kontrollera att författaren är korrekt kopplad
        if (book.getAuthor() == null || book.getAuthor().getId() == 0) {
            throw new IllegalArgumentException("Author must be provided.");
        }
        return booksRepo.save(book);
    }

    public Books patchBook(Books newBook, Long id) {
        return booksRepo.findById(id)
                .map(book -> {
                    if (newBook.getTitle() != null) {
                        book.setTitle(newBook.getTitle());
                    }
                    // Se till att ISBN inte blir null om det inte uppdateras
                    if (newBook.getIsbn() != null) {
                        book.setIsbn(newBook.getIsbn());
                    }
                    if (newBook.getAuthor() != null && newBook.getAuthor().getName() != null) {
                        Author author = authorService.findByName(newBook.getAuthor().getName());
                        if (author != null) {
                            book.setAuthor(author);
                        }
                    }
                    return booksRepo.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return booksRepo.save(newBook);
                });
    }

    public void removeBook(Long id) {
        booksRepo.deleteById(id);
    }

    public void removeBook(Books book) {
        booksRepo.deleteById(book.getId());
    }

    public void removeAll() {
        booksRepo.deleteAll();
    }

    public Optional<Books> findById(Long id) {
        return booksRepo.findById(id); // Hämtar boken som är kopplad till ID:t
    }
}
