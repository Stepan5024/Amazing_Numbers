package numbers;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    static boolean isNatural = false;
    static boolean isEven = false;
    static boolean isOdd = false;
    static boolean isBuzz = false;
    static boolean isDuck = false;
    static boolean isPalindromic = false;

    public static void main(String[] args) {

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        Scanner scanner = new Scanner(System.in);
        long numberFirst = 0;
        do {
            System.out.println("Enter a request: ");
            String number = scanner.nextLine();
            String[] numbers = number.split(" ");

            try {
                numberFirst = Long.parseLong(numbers[0]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка");
            }
            if (numbers.length > 3) {
                System.out.println("Не корректный ввод");
            } else if (numbers.length == 2) {
                if (checkIfNatural((numbers))) {
                    continue;
                }

                for (long i = Long.parseLong(numbers[0]); i < Long.parseLong(numbers[0]) + Long.parseLong(numbers[1]); i++) {
                    Methods(i, 2);
                }
            } else if (numbers.length == 3) {
                if (checkIfNatural((numbers)) || !checkIfPropertyExits(numbers[2].toLowerCase(Locale.ROOT))) {
                    continue;
                }

                long startNumber = Long.parseLong(numbers[0]);
                long kolvoNumbers = Long.parseLong(numbers[1]);
                String property = numbers[2].toLowerCase(Locale.ROOT);
                int count = 0;
                long tempNum = startNumber;
                while (count < kolvoNumbers) {
                    if (Methods(tempNum, property)) {

                        count++;
                    }
                    tempNum++;
                }
            } else {
                try {
                    numberFirst = Long.parseLong(numbers[0]);
                    if (numberFirst == 0) break;
                    checkIfNatural(numberFirst);
                    if (!isNatural) continue;
                    Methods(numberFirst, 1);
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            }
        } while (true);
        System.out.println("Goodbye!");
    }

    static void Methods(long numberFirst, int param) {
        checkIfNatural(numberFirst);
        checkIfOddOrEven(numberFirst);
        if (param == 1) {

            System.out.println("Properties of " + numberFirst);
            System.out.println("\todd: " + isOdd + "\n\teven: " + isEven);
            System.out.println("\tbuzz: " + checkIfBuzz(numberFirst));
            System.out.println("\tduck: " + checkIfDuck(numberFirst));
            System.out.println("\tgapful: " + checkIfGapful(numberFirst));
            System.out.println("\tpalindromic: " + checkIfPalindromic(numberFirst));
            System.out.println("\tspy: " + checkIfSpy(numberFirst));
        } else {

            String res = numberFirst + " is ";
            if (isOdd) res += "odd, ";
            if (isEven) res += "even, ";
            if (checkIfBuzz(numberFirst)) res += "buzz, ";
            if (checkIfGapful(numberFirst)) res += "gapful, ";
            if (checkIfDuck(numberFirst)) res += "duck, ";
            if (checkIfPalindromic(numberFirst)) res += "palindromic, ";
            if (checkIfSpy(numberFirst)) res += "spy, ";
            res = removeLastChar(res);
            System.out.println(res);
        }

    }

    static boolean Methods(long numberFirst, String property) {
        checkIfNatural(numberFirst);
        checkIfOddOrEven(numberFirst);
        boolean propertyIsFind = false;
        StringBuilder builder = new StringBuilder(numberFirst + " is ");

        if (isOdd) builder.append("odd, ");
        if (isEven) builder.append("even, ");
        if (checkIfBuzz(numberFirst)) builder.append("buzz, ");
        if (checkIfGapful(numberFirst)) builder.append("gapful, ");
        if (checkIfDuck(numberFirst)) builder.append("duck, ");
        if (checkIfPalindromic(numberFirst)) builder.append("palindromic, ");
        if (checkIfSpy(numberFirst)) builder.append("spy, ");
        builder.delete(builder.length() - 2, builder.length());
        //res = removeLastChar(res);
        String res = builder.toString();
        if (res.contains(property)) {
            System.out.println(res);
            propertyIsFind = true;
        }
        return propertyIsFind;
    }

    public static boolean checkIfPropertyExits(String property) {
        boolean exits = false;
        String[] properties = new String[]{"odd", "even", "buzz", "gapful", "duck", "palindromic", "spy"};
        for (String s : properties) {
            if (s.compareTo(property) == 0) {
                exits = true;
                break;
            }
        }
        if (!exits) System.out.println("The property " + property + " is wrong.\n" +
                "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
        return exits;
    }

    public static boolean checkIfSpy(long number) {
        boolean isSpy = false;
        long temp = number;
        int sumDigits = 0;
        int multDigits = 1;
        while (temp > 0) {
            sumDigits += temp % 10;
            multDigits *= temp % 10;
            temp /= 10;
        }
        if (sumDigits == multDigits) isSpy = true;

        return isSpy;
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 2));
    }

    static boolean checkIfGapful(long userNumber) {
        boolean gapful = false;
        if (userNumber / 100 >= 1) {
            int lastNum = (int) (userNumber % 10);
            int kolNum = 0;
            long temp = userNumber;
            while (temp > 0) {
                temp /= 10;
                kolNum++;
            }
            int firstNum = (int) (userNumber / Math.pow(10, kolNum - 1));
            if (userNumber % (firstNum * 10 + lastNum) == 0) gapful = true;
        }
        return gapful;
    }

    static void checkIfOddOrEven(long userNumber) {
        isEven = (userNumber % 2 == 0) ? true : false;
        isOdd = (userNumber % 2 != 0) ? true : false;
    }

    static void checkIfNatural(long userNumber) {
        isNatural = userNumber > 0 ? true : false;

        if (!isNatural) {
            System.out.println("The first parameter should be a natural number or zero.");

        }
    }

    static boolean checkIfNatural(String[] userNumber) {
        boolean IsNeedToReturnInput = false;
        for (int i = 0; i < userNumber.length; i++) {
            try {
                isNatural = Long.parseLong(userNumber[i]) > 0 ? true : false;

                if (!isNatural && i == 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    IsNeedToReturnInput = true;

                }
                if (!isNatural && i == 1) {
                    System.out.println("The second parameter should be a natural number or zero.");
                    IsNeedToReturnInput = true;
                }
            } catch (NumberFormatException e) {
                //System.out.println(e);
            }


        }
        return IsNeedToReturnInput;

    }

    static boolean checkIfPalindromic(long userNumber) {
        isPalindromic = false;
        String number = userNumber + "";
        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - i - 1)) {
                isPalindromic = false;
                break;
            } else isPalindromic = true;
        }
        if (userNumber < 10) isPalindromic = true;
        return isPalindromic;
    }

    static boolean checkIfDuck(long userNumber) {
        return isDuck = ("" + userNumber).contains("0") && ("" + userNumber).charAt(0) != '0' ? true : false;
    }

    static boolean checkIfBuzz(long userNumber) {
        return isBuzz = userNumber % 7 == 0 || userNumber % 10 == 7 ? true : false;
    }
}
