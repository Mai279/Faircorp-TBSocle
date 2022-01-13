package com.emse.spring.faircorp.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * class for handling application startup messages in the console (CLI), to say hello !
 * The @primary lets the framework know that it should use this service rather than AnotherConsoleGreetingService when
 * looking for an implementation of the GreetingService interface.
 */
@Primary
@Service
public class ConsoleGreetingService implements GreetingService {

    @Override
    public void greet(String name) {
        System.out.println("Hello, "+ name + "!");
    }
}
