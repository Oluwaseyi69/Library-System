package org.ebook.data.repository;

import org.ebook.data.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findBookByTitleAndAuthor(String title, String author);

}
