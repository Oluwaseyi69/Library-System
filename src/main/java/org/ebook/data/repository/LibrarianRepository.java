package org.ebook.data.repository;

import org.ebook.data.model.Librarian;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LibrarianRepository extends MongoRepository<Librarian, String> {
    Optional<Librarian>findBy(String username);
}
