package xyz.trackbuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"xyz.trackbuck", "it.ozimov.springboot"})
public class RestrubApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestrubApplication.class, args);
    }
}
