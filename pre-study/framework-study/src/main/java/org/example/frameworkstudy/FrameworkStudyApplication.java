package org.example.frameworkstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FrameworkStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkStudyApplication.class, args);
    }

}