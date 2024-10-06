package com.springboot.betterreads_app.repository.userbooks;

import com.springboot.betterreads_app.model.userbooks.UserBooks;
import com.springboot.betterreads_app.model.userbooks.UserBooksPrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserBooksRepository extends CassandraRepository<UserBooks, UserBooksPrimaryKey> {
}
