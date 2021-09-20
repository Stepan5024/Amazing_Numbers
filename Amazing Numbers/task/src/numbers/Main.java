package numbers;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static Boolean isNatural = false;
    static Boolean isEven = false;
    static Boolean isOdd = false;
    static Boolean isBuzz = false;
    static Boolean isDuck = false;

    public static void main(String[] args) {
//        write your code here
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        checkIfNatural(number);

        System.out.println("Properties of " + number);

        checkIfOddOrEven(number);
        System.out.println("\todd: " + isOdd + "\n\teven: " + isEven);

        ;
        System.out.println("\tbuzz: " + checkIfBuzz(number));
        System.out.println("\tduck: " + checkIfDuck(number));


    }
    static void checkIfOddOrEven(int userNumber) {
        isEven = (userNumber % 2 == 0) ? true : false;
        isOdd = (userNumber % 2 != 0) ? true : false;
    }

    static void checkIfNatural(int userNumber) {
        isNatural = userNumber > 0 ? true : false;

        if (!isNatural) {
            System.out.println("This number is not natural!");
            System.exit(0);
        }
    }
    static boolean checkIfDuck(int userNumber) {
        return isDuck = ("" + userNumber).contains("0") && (""+userNumber).charAt(0) != '0' ? true : false;
    }
    static  boolean checkIfBuzz(int userNumber) {
        return isBuzz = userNumber % 7 == 0 || userNumber % 10 == 7 ? true : false;
    }
}
