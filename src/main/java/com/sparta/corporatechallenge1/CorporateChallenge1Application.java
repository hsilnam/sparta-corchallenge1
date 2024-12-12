package com.sparta.corporatechallenge1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CorporateChallenge1Application {

    public static void main(String[] args) {
        SpringApplication.run(CorporateChallenge1Application.class, args);
    }

}
