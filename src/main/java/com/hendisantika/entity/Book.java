package com.hendisantika.entity;

import com.hendisantika.util.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-custom-ID-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/10/21
 * Time: 18.56
 */
@Entity
@Data
@EqualsAndHashCode(exclude = "publishers")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
            name = "book_seq",
            strategy = "com.hendisantika.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "HND_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String id;

    @Version
    @Column(name = "version")
    private int version;

    @Column
    private String title;

    @Column
    private LocalDate publishingDate;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
    private Set<Publisher> publishers;

    public Book(String title, LocalDate localDate, Publisher... publishers) {
        this.title = title;
        this.publishingDate = LocalDate.now();
        this.publishers = Stream.of(publishers).collect(Collectors.toSet());
        this.publishers.forEach(x -> x.getBooks().add(this));
    }
}
