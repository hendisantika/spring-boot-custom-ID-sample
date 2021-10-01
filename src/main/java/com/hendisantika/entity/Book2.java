package com.hendisantika.entity;

import com.hendisantika.util.PublisherCodePrefixedSequenceIdGenerator;
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
@EqualsAndHashCode(exclude = "publishers2")
@AllArgsConstructor
@NoArgsConstructor
public class Book2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book2_seq")
    @GenericGenerator(
            name = "book2_seq",
            strategy = "com.hendisantika.util.PublisherCodePrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = PublisherCodePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = PublisherCodePrefixedSequenceIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER,
                            value = "_"),
                    @Parameter(name = PublisherCodePrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id;

    @Version
    @Column(name = "version")
    private int version;

    @Column
    private String title;

    @Column
    private LocalDate publishingDate;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher2",
            joinColumns = @JoinColumn(name = "book2_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
    private Set<Publisher2> publishers;

    public Book2(String title, LocalDate localDate, Publisher2... publishers) {
        this.title = title;
        this.publishingDate = LocalDate.now();
        this.publishers = Stream.of(publishers).collect(Collectors.toSet());
        this.publishers.forEach(x -> x.getBooks().add(this));
    }
}
