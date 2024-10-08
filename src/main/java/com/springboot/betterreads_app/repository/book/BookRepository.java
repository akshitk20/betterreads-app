package com.springboot.betterreads_app.repository.book;

import com.springboot.betterreads_app.model.book.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

}
