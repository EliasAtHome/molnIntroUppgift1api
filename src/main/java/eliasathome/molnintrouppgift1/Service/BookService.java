package eliasathome.molnintrouppgift1.Service;

import eliasathome.molnintrouppgift1.Controller.BooksController;
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

    private final BooksRepo booksRepo;

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(Long id) {
        return Optional.of(booksRepo.findById(id).orElse(new Books()));
    }

    public Books saveBook(Books book) {
        return booksRepo.save(book);
    }

    public Books patchBook(Books book, Long id) {
        Optional<Books> currentBookOptional = booksRepo.findById(id);

        if (currentBookOptional.isPresent()) {
            Books currentBook = currentBookOptional.get(); //test pipeline

            // Uppdatera titel om den är olika
            if (!currentBook.getTitle().equals(book.getTitle())) {
                currentBook.setTitle(book.getTitle());
            }

            // Endast uppdatera ISBN om det är angivet
            if (book.getISBN() != null && !book.getISBN().equals(currentBook.getISBN())) {
                currentBook.setISBN(book.getISBN());
            }

            return booksRepo.save(currentBook);
        } else {
            // Hantera fallet när boken inte finns
            throw new RuntimeException("Book not found with ID: " + id);
        }
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

}
