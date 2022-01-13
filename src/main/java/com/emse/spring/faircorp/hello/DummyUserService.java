package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class for handling application startup messages in the console (CLI) to welcome the users (fictitious here)
 */
@Service
public class DummyUserService implements UserService {

    @Autowired
    private GreetingService greetingService;
    //will look for an implementation of Greetingservice not the interface itself.
    //It is thus a class that we find and that we use to define the greetingservice object.

    @Override
    public void greetAll() {
        String[] listeNoms = {"Elodie", "Charles"};
        for (String nom : listeNoms) {
            greetingService.greet(nom);
        }
    }
}
