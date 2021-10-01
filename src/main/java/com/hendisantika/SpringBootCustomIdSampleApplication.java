package com.hendisantika;

import com.hendisantika.entity.Book;
import com.hendisantika.entity.Publisher;
import com.hendisantika.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@Log4j2
@SpringBootApplication
public class SpringBootCustomIdSampleApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomIdSampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Create a couple of Book and Publisher
        bookRepository.save(new Book("Book 1", LocalDate.now(), new Publisher("Publisher A"), new Publisher(
                "Publisher B")));
    }
}
