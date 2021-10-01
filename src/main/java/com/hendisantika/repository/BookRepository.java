package com.hendisantika.repository;

import com.hendisantika.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-custom-ID-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/10/21
 * Time: 19.02
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
