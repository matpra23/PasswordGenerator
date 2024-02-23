package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Input password length: ");
        int len = scanner.nextInt();
        System.out.println("Your password: " + generatePassword(len));

    }

    public static String generatePassword(int len){
        String password = "";
        String[] passwordArr = new String[len];
        for (int i = 0; i < len; i++) {
            int randomNum = random.nextInt(1, 3);

            //z jakiegos powodu zawsze jest 3 i haslo to same char'y, DO NAPRAWY
            switch(randomNum){
                case 1:
                    passwordArr[i] = randomNumberGen();
                case 2:
                    passwordArr[i] = randomLetterGen();
                case 3:
                    passwordArr[i] = randomCharacterGen();
            }
            password += passwordArr[i];
        }
        return password;
    }

    static String randomNumberGen(){
        String randomNumberGenerated = "";
        int randN = random.nextInt(0,10);
        char[] numbers = new char[]{0,1,2,3,4,5,6,7,8,9};
        randomNumberGenerated += numbers[randN];
        return randomNumberGenerated;
    }

    static String randomLetterGen(){
        String randomLetterGenerated = "";
        String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpRrSsTtUuWwYyZz";
        int randN = random.nextInt(0, 46);
        char[] letterArr = letters.toCharArray();
        randomLetterGenerated += letterArr[randN];
        return randomLetterGenerated;
    }

    static String randomCharacterGen(){
        String randomCharGenerated = "";
        String characters = "!@#$%^&*()_+-}{[]";
        int  randomN = random.nextInt(0, 17);
        char[] charactersArr = characters.toCharArray();
        randomCharGenerated += charactersArr[randomN];
        return randomCharGenerated;
    }
}