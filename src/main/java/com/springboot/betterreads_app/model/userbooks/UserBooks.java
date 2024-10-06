package com.springboot.betterreads_app.model.userbooks;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;

@Table(value = "book_by_user_and_bookid")
@Data
@NoArgsConstructor
public class UserBooks {

    @PrimaryKey
    private UserBooksPrimaryKey key;

    @Column("reading_status")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String readingStatus;

    @Column("started_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate startedDate;

    @Column("completed_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate completedDate;
    @Column("rating")
    @CassandraType(type = CassandraType.Name.INT)
    private int rating;
}
