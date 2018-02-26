package org.yny.sample.gemfire.repositories;

import org.springframework.data.repository.CrudRepository;
import org.yny.sample.model.Book;

public interface BookRepository extends CrudRepository<Book, String> {
}
