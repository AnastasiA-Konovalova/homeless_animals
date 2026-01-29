package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public class AnimalsServiceApp {

    @EnableJpaRepositories
    @SpringBootApplication
    public class AnimalServiceApp {
        public static void main(String[] args) {
            SpringApplication.run(AnimalServiceApp.class, args);
        }
    }
}