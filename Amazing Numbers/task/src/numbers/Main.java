package numbers;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
//        write your code here
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number <= 0) {
            System.out.println("This number is not natural!");
            exit(0);
        }
        System.out.println("Properties of " + number);
        if (number % 2 == 1) System.out.println("\todd: true\n\teven: false");
        else System.out.println("\todd: false\n\teven: true");
        int temp = number;
        boolean seven = false;

        int lastDigit = temp % 10;
        temp /= 10;
        lastDigit *= 2;
        int res = temp - lastDigit;
        if (res % 7 == 0) {
            seven = true;
        }

        if (number % 10 != 7 && !seven) {
            System.out.println(number + " is neither divisible by 7 nor does it end with 7");
        }else if(seven && number % 10 != 7) {
            System.out.println("It is a Buzz number.\n" +
                    "Explanation:");
            System.out.println(number + " is divisible by 7");
        } else if(!seven && number % 10 == 7) {
            System.out.println("It is a Buzz number.\n" +
                    "Explanation:");
            System.out.println(number + " ends with 7");
        }else{
            System.out.println("It is a Buzz number.\n" +
                    "Explanation:");
            System.out.println(number + " is divisible by 7 and ends with 7");

        }

    }
}
