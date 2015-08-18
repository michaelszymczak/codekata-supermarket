package com.michaelszymczak.supermarket;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Ignore("not implemented yet")
    @Test
    public void welcomes_you() throws Exception {
        App app = new App();

        String message = app.getGreetings();

        assertEquals("Welcome!", message);
    }

}