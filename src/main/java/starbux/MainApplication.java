package starbux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import starbux.repository.DrinkRepository;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DrinkRepository drinkRepository){
        return args -> {
            System.out.println(drinkRepository.findAll());
        };
    }
}