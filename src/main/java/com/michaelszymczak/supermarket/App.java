package com.michaelszymczak.supermarket;


public class App {
    public static void main(String[] args) {
        System.out.println(new App().getGreetings());
    }

    public String getGreetings() {
        return "Welcome!";
    }
}
