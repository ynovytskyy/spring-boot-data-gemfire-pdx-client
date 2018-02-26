package org.yny.sample.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Indexed;
import org.springframework.data.gemfire.mapping.Region;

import java.time.ZonedDateTime;

@Data
@Region("booksRegion") //NB needs to match Region bean name
@NoArgsConstructor //NB needed for PDXSerializer
public class Book {

    @Id
    private String bookId;

    @Indexed
    private String authorId;

    @Indexed
    private String publisherId;

    private String genre;
    private BookType bookType;
    private ZonedDateTime published;

    @PersistenceConstructor
    public Book(String bookId, String genre, String authorId, String publisherId,
                BookType bookType, ZonedDateTime published) {
        this.bookId = bookId;
        this.genre = genre;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.bookType = bookType;
        this.published = published;
    }

}


