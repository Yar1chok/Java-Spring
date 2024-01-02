package sfu.rkis6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"sfu.rkis6.controllers", "sfu.rkis6.service", "sfu.rkis6.config",
        "sfu.rkis6.repositories", "sfu.rkis6.model"})
@EnableJpaRepositories(value = "sfu.rkis6.repositories")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}