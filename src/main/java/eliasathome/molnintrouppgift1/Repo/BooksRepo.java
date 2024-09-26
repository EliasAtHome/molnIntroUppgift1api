package eliasathome.molnintrouppgift1.Repo;

import eliasathome.molnintrouppgift1.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {
}
