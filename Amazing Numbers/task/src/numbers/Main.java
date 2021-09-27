package numbers;

import org.w3c.dom.ls.LSOutput;

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
                "- two natural numbers and two properties to search for;\n" +
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
            if (numbers.length > 4) {
                System.out.println("Не корректный ввод");
            } else if (numbers.length == 2) {
                if (checkIfNatural((numbers))) {
                    continue;
                }

                for (long i = Long.parseLong(numbers[0]); i < Long.parseLong(numbers[0]) + Long.parseLong(numbers[1]); i++) {
                    Methods(i, 2);
                }
            } else if (numbers.length == 3) {

                if (checkIfNatural((numbers))) {
                    continue;
                }

                long startNumber = Long.parseLong(numbers[0]);
                long kolvoNumbers = Long.parseLong(numbers[1]);
                String[] property = new String[1];
                property[0] = numbers[2].toLowerCase(Locale.ROOT);
                int count = 0;
                long tempNum = startNumber;
                while (count < kolvoNumbers) {
                    if (Methods(tempNum, property)) {

                        count++;
                    }
                    tempNum++;
                }
            } else if (numbers.length == 4) {

                if (checkIfNatural((numbers))) {
                    continue;
                }
                long startNumber = Long.parseLong(numbers[0]);
                long kolvoNumbers = Long.parseLong(numbers[1]);
                String[] property = new String[2];
                property[0] = numbers[2].toLowerCase(Locale.ROOT);
                property[1] = numbers[3].toLowerCase(Locale.ROOT);
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
            System.out.println("\tsunny: " + checkIfSunny(numberFirst));
            System.out.println("\tsquare: " + checkIfSquare(numberFirst));
        } else {

            StringBuilder builder = new StringBuilder(numberFirst + " is ");

            if (isOdd) builder.append("odd, ");
            if (isEven) builder.append("even, ");
            if (checkIfBuzz(numberFirst)) builder.append("buzz, ");
            if (checkIfGapful(numberFirst)) builder.append("gapful, ");
            if (checkIfDuck(numberFirst)) builder.append("duck, ");
            if (checkIfPalindromic(numberFirst)) builder.append("palindromic, ");
            if (checkIfSpy(numberFirst)) builder.append("spy, ");
            if (checkIfSunny(numberFirst)) builder.append("sunny, ");
            if (checkIfSquare(numberFirst)) builder.append("square, ");
            builder.delete(builder.length() - 2, builder.length());
            String res = builder.toString();
            System.out.println(res);
        }

    }

    static boolean Methods(long numberFirst, String[] property) {
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
        if (checkIfSunny(numberFirst)) builder.append("sunny, ");
        if (checkIfSquare(numberFirst)) builder.append("square, ");

        builder.delete(builder.length() - 2, builder.length());

        String res = builder.toString();
        boolean[] flag = new boolean[property.length];
        for (int i = 0; i < property.length; i++) {
            if (res.contains(property[i])) flag[i] = true;
        }
        //System.out.println(property[0] + " find or not " + flag[0] );
        if (flag.length == 1 && flag[0]) {
            System.out.println(res);
            propertyIsFind = true;
        } else if (flag.length == 2 && flag[0] && flag[1]) {
            System.out.println(res);
            propertyIsFind = true;
        }
        return propertyIsFind;
    }

    public static boolean checkIfSunny(long number) {
        boolean isSunny = false;

        if (checkIfSquare(number + 1)) {
            isSunny = true;
        }
        return isSunny;

    }

    public static boolean checkIfSquare(long number) {
        boolean isSquare = false;
        if (Math.sqrt(number) % 1 == 0) {
            isSquare = true;
        }
        return isSquare;

    }

    public static boolean checkIfPropertyExits(String[] property) {

        boolean[] exits = new boolean[property.length];
        String[] properties = new String[]{"odd", "even", "buzz", "gapful", "duck", "palindromic", "spy", "sunny", "square"};
        for (int i = 0; i < property.length; i++) {
            for (String s : properties) {
                if (property[i].compareTo(s) == 0) {
                    exits[i] = true;
                }
            }
        }

        boolean flag = true;
        if (exits.length == 1 && !exits[0]) {
            System.out.println("The property " + property[0] + " is wrong.\n" +
                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
            flag = false;
        }
        else if (exits.length == 2 && (!exits[0] && !exits[1])) {
            System.out.println("The properties " + property[0] + ", " + property[1] + " are wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            flag = false;
        }else if(exits.length == 2 && !exits[0]){
            System.out.println("The property " + property[0] + " is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            flag = false;
        }else if(exits.length == 2 && !exits[1]){
            System.out.println("The property " + property[1]  + " is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            flag = false;
        }

        return flag;
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
            if (userNumber % (firstNum * 10L + lastNum) == 0) gapful = true;
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
        boolean wasIn = false;
        for (int i = 0; i < 2; i++) {

                isNatural = Long.parseLong(userNumber[i]) > 0 ? true : false;

                if (!isNatural && i == 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    IsNeedToReturnInput = true;
                    continue;
                }
                if (!isNatural && i == 1) {
                    System.out.println("The second parameter should be a natural number or zero.");
                    IsNeedToReturnInput = true;

                }
            for (int j = 2; j < userNumber.length; j++) {


                if (!wasIn) {
                    //System.out.println("Выясняем сууществует ли свойство");
                    wasIn = true;
                    String[] str = new String[userNumber.length - 2];
                    for (int k = 0; k < str.length; k++) {
                        str[k] = userNumber[2 + k].toLowerCase(Locale.ROOT);

                    }

                    if (!checkIfPropertyExits(str)) {

                        IsNeedToReturnInput = true;
                        continue;
                    }

                    StringBuilder builder = new StringBuilder();
                    for (String s : str) {
                        builder.append(s).append(", ");
                    }
                    builder.replace(builder.length() - 2, builder.length(), "");
                    //System.out.println("///////////" + builder);
                    if (builder.toString().compareTo("sunny, square") == 0 || builder.toString().compareTo("square, sunny") == 0 || builder.toString().compareTo("even, odd") == 0 || builder.toString().compareTo("odd, even") == 0 || builder.toString().compareTo("duck, spy") == 0 || builder.toString().compareTo("spy, duck") == 0) {
                        System.out.println("The request contains mutually exclusive properties: " + builder + "\n" +
                                "There are no numbers with these properties.");
                        IsNeedToReturnInput = true;
                    }

                }
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
