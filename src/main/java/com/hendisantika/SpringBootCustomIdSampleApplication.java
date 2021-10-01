package com.hendisantika;

import com.hendisantika.entity.Book;
import com.hendisantika.entity.Book2;
import com.hendisantika.entity.Publisher;
import com.hendisantika.entity.Publisher2;
import com.hendisantika.entity.User;
import com.hendisantika.repository.Book2Repository;
import com.hendisantika.repository.BookRepository;
import com.hendisantika.repository.UserRepository;
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

    @Autowired
    private Book2Repository book2Repository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomIdSampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Create a couple of Book and Publisher
        bookRepository.save(new Book("Book 1", LocalDate.now(), new Publisher("Publisher A"), new Publisher(
                "Publisher B")));

        book2Repository.save(new Book2("Book 1", LocalDate.now(), new Publisher2("Publisher A"), new Publisher2(
                "Publisher B")));

        userRepository.save(new User("Uzumaki Naruto"));
    }
}
