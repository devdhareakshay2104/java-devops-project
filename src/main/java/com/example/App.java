package com.example;

public class App {

    public static String getMessage() {
        return "Java DevOps Project";
    }

    public static String getApplicationName() {
        return "Java Maven CI/CD Application";
    }

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" " + getApplicationName());
        System.out.println("=================================");
        System.out.println("Application is running successfully!");
        System.out.println(getMessage());
        System.out.println("Version: 1.0");

    }
}