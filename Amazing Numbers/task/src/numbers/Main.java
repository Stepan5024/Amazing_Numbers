package numbers;

import java.text.ParseException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

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
            if (numbers.length > 2) {
                System.out.println("Не корректный ввод");
            } else if (numbers.length == 2) {
                if (checkIfNatural((numbers))) {
                    continue;
                }


                for (long i = Long.parseLong(numbers[0]); i < Long.parseLong(numbers[0]) + Long.parseLong(numbers[1]); i++) {
                    Methods(i, 2);
                }
            } else {
                numberFirst = Long.parseLong(numbers[0]);
                if (numberFirst == 0) break;
                checkIfNatural(numberFirst);
                if (!isNatural) continue;
                Methods(numberFirst, 1);
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
        } else {

            String res = numberFirst + " is ";
            if (isOdd) res += "odd, ";
            if (isEven) res += "even, ";
            if (checkIfBuzz(numberFirst)) res += "buzz, ";
            if (checkIfGapful(numberFirst)) res += "gapful, ";
            if (checkIfDuck(numberFirst)) res += "duck, ";
            if (checkIfPalindromic(numberFirst)) res += "palindromic, ";
            res = removeLastChar(res);
            System.out.println(res);
        }

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
            isNatural = Long.parseLong(userNumber[i]) > 0 ? true : false;

            if (!isNatural && i == 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                IsNeedToReturnInput = true;

            }
            if (!isNatural && i == 1) {
                System.out.println("The second parameter should be a natural number or zero.");
                IsNeedToReturnInput = true;
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
