package com.springboot.betterreads_app.repository.user;

import com.springboot.betterreads_app.model.user.BooksByUser;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface BooksByUserRepository extends CassandraRepository<BooksByUser, String> {
}
