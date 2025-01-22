package ru.solomka.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.solomka.jwt.SecureLoader;

@SpringBootApplication
public class SpringAuthenticationApplication implements SecureLoader {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthenticationApplication.class, args);
    }

}
