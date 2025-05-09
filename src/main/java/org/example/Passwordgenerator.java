package org.example;

import java.util.Random;

public class Passwordgenerator {
    
    private static long start;
    private static long end;
    private static int randomNum;
    private static String randomNumberGenerated;
    private static String randomLetterGenerated;
    private static String randomCharGenerated;
    private static final String numbers = "0123456789";
    private static final String letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpRrSsTtUuWwYyZz";
    private static final String characters = "!@#$%^&*()_+-}{[]";

    private static StringBuilder password;
    private static final Random random = new Random();
    
    public static String generatePassword(int len, boolean includeNums, boolean includeLetter, boolean includeSpecCh) {
        start = System.currentTimeMillis();
        password = new StringBuilder();
        String[] passwordArr = new String[len];

        if (includeNums && includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                randomNum = random.nextInt(1, 4);
                switch (randomNum) {
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomLetterGen();
                        break;
                    case 3:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                }
                password.append(passwordArr[i]);
            }
        } else if (includeNums && includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                randomNum = random.nextInt(1, 3);
                switch (randomNum) {
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomLetterGen();
                        break;
                }
                password.append(passwordArr[i]);
            }
        } else if (!includeNums && includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                randomNum = random.nextInt(1, 3);
                switch (randomNum) {
                    case 1:
                        passwordArr[i] = randomLetterGen();
                        break;
                    case 2:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                }
                password.append(passwordArr[i]);
            }
        } else if (includeNums && !includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                randomNum = random.nextInt(1, 3);
                switch (randomNum) {
                    case 1:
                        passwordArr[i] = randomNumberGen();
                        break;
                    case 2:
                        passwordArr[i] = randomSpecialCharacterGen();
                        break;
                }
                password.append(passwordArr[i]);
            }
        } else if (includeNums && !includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomNumberGen();
                password.append(passwordArr[i]);
            }
        } else if (!includeNums && includeLetter && !includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomLetterGen();
                password.append(passwordArr[i]);
            }
        } else if (!includeNums && !includeLetter && includeSpecCh) {
            for (int i = 0; i < len; i++) {
                passwordArr[i] = randomSpecialCharacterGen();
                password.append(passwordArr[i]);
            }
        }

        end = System.currentTimeMillis();
        time time = (s, e) -> e - s;
        System.out.println("Password generated in " + time.miliseconds(start, end) + "ms");
        return password.toString();
    }

    private static String randomNumberGen() {
        randomNumberGenerated = "";
        int randN = random.nextInt(0, 10);
        char[] numbersArr = numbers.toCharArray();
        randomNumberGenerated += numbersArr[randN];
        return randomNumberGenerated;
    }

    private static String randomLetterGen() {
        randomLetterGenerated = "";
        int randN = random.nextInt(0, 46);
        char[] letterArr = letters.toCharArray();
        randomLetterGenerated += letterArr[randN];
        return randomLetterGenerated;
    }

    private static String randomSpecialCharacterGen() {
        randomCharGenerated = "";
        int randomN = random.nextInt(0, 17);
        char[] charactersArr = characters.toCharArray();
        randomCharGenerated += charactersArr[randomN];
        return randomCharGenerated;
    }
}