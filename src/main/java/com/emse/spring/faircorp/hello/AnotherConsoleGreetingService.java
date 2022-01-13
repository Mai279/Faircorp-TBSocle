package com.emse.spring.faircorp.hello;

import org.springframework.stereotype.Service;

/**
 * class for handling application startup messages in the console (CLI), to say bonjour !
 */
@Service
public class AnotherConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Bonjour, "+ name + "!");
    }
}
