package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Input password length: ");
        int len = scanner.nextInt();
        System.out.println("Your password: " + Passwordgenerator.generatePassword(len));

    }
}
