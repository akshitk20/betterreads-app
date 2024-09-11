package com.springboot.betterreads_app.repository.author;

import com.springboot.betterreads_app.model.author.Author;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CassandraRepository<Author, String> {

}
