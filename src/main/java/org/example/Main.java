package org.example;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws NegativeNumberException {
        System.out.println("Input password length: ");
        int len = scanner.nextInt();
        if(len < 1){
            throw new NegativeNumberException("Number can't be smaller than 1");
        }
        System.out.println("Your password: " + Passwordgenerator.generatePassword(len));
    }
}