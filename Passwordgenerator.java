package org.example;

import java.util.Random;
import java.util.Scanner;

public class Passwordgenerator {
    private static final Random random = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static String generatePassword(int len){
        System.out.println("no = 0, yes = 1");

        boolean includeNums = includeNumbers();
        boolean includeLetter = includeLetters();
        boolean includeSpecCh = includeSpacialCharacters();

        StringBuilder password = new StringBuilder();
        String[] passwordArr = new String[len];

        if (includeNums && includeLetter && includeSpecCh){
            for (int i = 0; i < len; i++) {
                int randomNum = random.nextInt(1, 4);
                switch(randomNum){
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomLetterGen();
                        break;
                    case 3:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                password.append(passwordArr[i]);
            }
        }

        if(includeNums && includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                int randomNum = random.nextInt(1, 3);
                switch(randomNum){
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomLetterGen();
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                password.append(passwordArr[i]);
            }
        }

        if (!includeNums && includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                int randomNum = random.nextInt(1, 3);
                switch(randomNum){
                    case 1:
                        passwordArr[i] = randomLetterGen();
                        break;
                    case 2:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                password.append(passwordArr[i]);
            }
        }

        if (includeNums && !includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                int randomNum = random.nextInt(1, 3);
                switch(randomNum){
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                    default:
                        System.out.println("");
                        break;
                }
                password.append(passwordArr[i]);
            }
        }

        if (includeNums && !includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomNumberGen();
                password.append(passwordArr[i]);
            }

        }

        if (!includeNums && includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomLetterGen();
                password.append(passwordArr[i]);
            }
        }

        if (!includeNums && !includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomSpecialCharacterGen();
                password.append(passwordArr[i]);
            }
        }

        return password.toString();
    }

    private static boolean includeNumbers(){
        System.out.println("Do you want to include special numbers?");
        int trueOrfalse = sc.nextInt();
        if (trueOrfalse == 1){
            return true;
        } else if (trueOrfalse == 0) {
            return false;
        }else {
            throw new IllegalArgumentException("input 0 or 1");
        }
    }

    private static boolean includeLetters(){
        System.out.println("Do you want to include special letters?");
        int trueOrfalse = sc.nextInt();
        if (trueOrfalse == 1){
            return true;
        } else if (trueOrfalse == 0) {
            return false;
        }else {
            throw new IllegalArgumentException("input 0 or 1");
        }

    }

    private static boolean includeSpacialCharacters(){
        System.out.println("Do you want to include special characters?");
        int trueOrfalse = sc.nextInt();
        if (trueOrfalse == 1){
            return true;
        } else if (trueOrfalse == 0) {
            return false;
        }else {
            throw new IllegalArgumentException("input 0 or 1");
        }
    }

    private static String randomNumberGen(){
        String randomNumberGenerated = "";
        String numbers = "0123456789";
        int randN = random.nextInt(0,10);
        char[] numbersArr = numbers.toCharArray();
        randomNumberGenerated += numbersArr[randN];
        return randomNumberGenerated;
    }

    private static String randomLetterGen(){
        String randomLetterGenerated = "";
        String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpRrSsTtUuWwYyZz";
        int randN = random.nextInt(0, 46);
        char[] letterArr = letters.toCharArray();
        randomLetterGenerated += letterArr[randN];
        return randomLetterGenerated;
    }

    private static String randomSpecialCharacterGen(){
        String randomCharGenerated = "";
        String characters = "!@#$%^&*()_+-}{[]";
        int  randomN = random.nextInt(0, 17);
        char[] charactersArr = characters.toCharArray();
        randomCharGenerated += charactersArr[randomN];
        return randomCharGenerated;
    }
}
