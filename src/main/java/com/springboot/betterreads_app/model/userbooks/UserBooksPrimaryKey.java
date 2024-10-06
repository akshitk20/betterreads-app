package com.springboot.betterreads_app.model.userbooks;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
@Data
@NoArgsConstructor
public class UserBooksPrimaryKey {
    @Id
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @Id
    @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String bookId;
}
