package numbers;

import java.util.Scanner;

import static java.lang.System.exit;

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
                "- enter 0 to exit.");

        System.out.println("Enter a request: ");
        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        while (number != 0) {
            checkIfNatural(number);

            System.out.println("Properties of " + number);

            checkIfOddOrEven(number);
            System.out.println("\todd: " + isOdd + "\n\teven: " + isEven);


            System.out.println("\tbuzz: " + checkIfBuzz(number));
            System.out.println("\tduck: " + checkIfDuck(number));
            System.out.println("\tpalindromic: " + checkIfPalindromic(number));

            System.out.println("Enter a request: ");
            number = scanner.nextLong();
        }
        System.out.println("Goodbye!");
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
    static boolean checkIfPalindromic(long userNumber) {
        isPalindromic = false;
        String number = userNumber + "";
        for (int i = 0; i < number.length()/2; i++) {
            if(number.charAt(i) != number.charAt(number.length()-i-1)){
                isPalindromic = false;
                break;
            }else isPalindromic = true;
        }
        if(userNumber < 10) isPalindromic = true;
        return isPalindromic;
    }
    static boolean checkIfDuck(long userNumber) {
        return isDuck = ("" + userNumber).contains("0") && (""+userNumber).charAt(0) != '0' ? true : false;
    }
    static  boolean checkIfBuzz(long userNumber) {
        return isBuzz = userNumber % 7 == 0 || userNumber % 10 == 7 ? true : false;
    }
}
