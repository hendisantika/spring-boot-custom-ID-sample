package com.hendisantika.repository;

import com.hendisantika.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-custom-ID-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/10/21
 * Time: 19.04
 */
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
