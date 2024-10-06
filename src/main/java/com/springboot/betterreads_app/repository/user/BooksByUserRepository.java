package com.springboot.betterreads_app.repository.user;

import com.springboot.betterreads_app.model.user.BooksByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BooksByUserRepository extends CassandraRepository<BooksByUser, String> {

    Slice<BooksByUser> findAllById(String id, Pageable pageable); // collection of books by user record
}
