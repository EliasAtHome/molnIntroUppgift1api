package eliasathome.molnintrouppgift1.Service;


import eliasathome.molnintrouppgift1.Models.Author;
import eliasathome.molnintrouppgift1.Repo.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;


    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Optional<Author> getOneAuthor(Long id) {
        return authorRepo.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    public Author patchAuthor(Author newAuthor, Long id) {
        return authorRepo.findById(id)
                .map(author -> {
                    if (newAuthor.getName() != null) {
                        author.setName(newAuthor.getName());
                    }
                    if (newAuthor.getAge() != null) {
                        author.setAge(newAuthor.getAge());
                    }

                    return authorRepo.save(author);
                })
                .orElseGet(() -> {
                    newAuthor.setId(id);
                    return authorRepo.save(newAuthor);
                });
    }

    public void removeAuthor(Long id) {
        authorRepo.deleteById(id);
    }
}



