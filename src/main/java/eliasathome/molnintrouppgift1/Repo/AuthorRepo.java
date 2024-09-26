package eliasathome.molnintrouppgift1.Repo;

import eliasathome.molnintrouppgift1.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
