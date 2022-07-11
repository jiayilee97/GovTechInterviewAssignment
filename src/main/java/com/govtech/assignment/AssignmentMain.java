package com.govtech.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.govtech.assignment"})
@EnableJpaRepositories("com.govtech.assignment")
@EntityScan("com.govtech.assignment")
@ComponentScan("com.govtech.assignment.service")
public class AssignmentMain {
    public static void main(String[] args) {
        SpringApplication.run(AssignmentMain.class, args);
    }
}
