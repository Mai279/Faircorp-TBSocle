package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class to configurate beans
 */
@Configuration
public class FaircorpApplicationConfig {

    /**
     * this method return a new Bean Spring
     * @param greetingService   object of type GreetingService, i.e. which instantiates a class implementing the
     *                          GreetingService interface
     * @return  a new CommandLineRunner bean
     */
    @Bean
    public CommandLineRunner greetingCommandLine(GreetingService greetingService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                greetingService.greet("Spring");
            }
        };
    }
}
