package numbers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main extends DataProcessing {

    static boolean isExit = false;
    static public String[] wrongProperty = new String[2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!" + "\n");
        DisplayingMessages.printInstructions();

        while (!isExit) {
            int invalidProperties = 0;

            System.out.print("\n" + "Enter a request: ");

            String stringInput = scanner.nextLine().trim();
            String[] inputData = stringInput.split("\\s+");

            long number = getNumeric(inputData[0]);

            int lengthList = (int) getNumeric(inputData.length > 1 ? inputData[1] : "0");

            String[] enteredProperties = new String[inputData.length < 2 ? 0 : inputData.length - 2];
            int[] indexesPropertyValues = new int[enteredProperties.length];

            for (int i = 0; i < enteredProperties.length; i++) {
                enteredProperties[i] = inputData[i + 2].toUpperCase();
                indexesPropertyValues[i] = getIndexOf(enteredProperties[i]);
                invalidProperties = indexesPropertyValues[i] != PROPERTIES.length
                        ? invalidProperties : invalidProperties + 1;
            }
            //System.out.println("lengthList " + lengthList + " number " + number);
            UserInteraction.displayResults(inputData, number, lengthList, enteredProperties,
                    indexesPropertyValues, stringInput, invalidProperties);
        }
        scanner.close();
    }
}

class UserInteraction extends DisplayingMessages {
    public static void displayResults(String[] inputData, long number, int lengthList, String[] enteredProperties,
                                      int[] indexesPropertyValues, String stringInput, int invalidProperties) {
        System.out.println();
        if (number == 0) {
            System.out.println("Goodbye!");
            isExit = true;
        } else if (inputData[0].isEmpty()) {
            printInstructions();
        } else if (number < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else if (lengthList < 0) {
            System.out.println("The second parameter should be a natural number.");
        } else if (invalidProperties > 0) {
            printErrorInvalidProperties(enteredProperties, indexesPropertyValues, invalidProperties);

        } else if (isMutuallyExclusiveNumbers(stringInput,
                "ODD", "EVEN", "-ODD", "-EVEN", "ODD", "-ODD", "EVEN", "-EVEN", "PALINDROMIC", "-PALINDROMIC", "BUZZ", "-BUZZ", "DUCK", "-DUCK", "GAPFUL", "-GAPFUL", "SPY", "-SPY", "SQUARE", "-SQUARE", "SUNNY", "-SUNNY", "JUMPING", "-JUMPING", "HAPPY", "-HAPPY", "SAD", "-SAD", "ODD", "EVEN", "DUCK", "SPY", "SUNNY", "SQUARE")) {

            //printErrorMutuallyExclusiveNumbers(enteredProperties);
            printErrorMutuallyExclusiveNumbers(Main.wrongProperty);
        } else if (enteredProperties.length != 0) {
            int countOperation = 0;
            if (lengthList == 1 && countOperation < 30) {
                long[] numbersByProperties = getNumbersWithGivenProperties(number, lengthList, indexesPropertyValues);
                //System.out.println("numbersByProperties " + numbersByProperties[0] );
                countOperation++;
                for (long num : numbersByProperties) printProperties(num, lengthList);
            } else if (lengthList != 1) {
                long[] numbersByProperties = getNumbersWithGivenProperties(number, lengthList, indexesPropertyValues);
                //System.out.println("numbersByProperties " + numbersByProperties[0] );

                for (long num : numbersByProperties) printProperties(num, lengthList);
            }
        } else if (lengthList > 0) {
            for (long i = number; i < number + lengthList; i++) printProperties(i, lengthList);
        } else {
            printProperties(number, lengthList);
        }
    }
}

class DataProcessing extends NumberProperties {
    static long getNumeric(String string) {
        try {
            return Long.parseLong(string);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getIndexOf(String enteredProperty) {
        int indexPropertyName = PROPERTIES.length;
        for (int i = 0; i < PROPERTIES.length; i++) {
            indexPropertyName = enteredProperty.equals(PROPERTIES[i]) ? i : indexPropertyName;
        }
        return indexPropertyName;
    }

    public static long[] getNumbersWithGivenProperties(long number, int lengthList, int[] indexesPropertyValues) {
        long[] numbers = new long[lengthList];
        for (int i = 0; i < lengthList; ) {
            boolean checkProperty = true;
            for (int index : indexesPropertyValues) {
                if (!getAllPropertiesValue(number)[index]) {
                    checkProperty = false;
                    break;
                }
            }
            if (checkProperty) {
                numbers[i] = number;
                i++;
            }
            number++;
        }
        return numbers;
    }

    static boolean isMutuallyExclusiveNumbers(String stringInput, String... properties) {
        int count = 0;
        /*if (builder.toString().compareTo("sunny, square") == 0 || builder.toString().compareTo("square, sunny") == 0 || builder.toString().compareTo("even, odd") == 0 || builder.toString().compareTo("odd, even") == 0 || builder.toString().compareTo("duck, spy") == 0 || builder.toString().compareTo("spy, duck") == 0) {
            System.out.println("The request contains mutually exclusive properties: " + builder + "\n" +
                    "There are no numbers with these properties.");
            IsNeedToReturnInput = true;
        }*/
        ArrayList<String[]> finalList = new ArrayList<>();
        for (int i = 0; i < properties.length; i += 2) {
            String[] str1 = new String[2];
            str1[0] = properties[i];
            str1[1] = properties[i + 1];
            //System.out.println(str1[0] + " " + str1[1]);
            finalList.add(str1);
        }


        stringInput = stringInput.toUpperCase();
        String[] tstr = stringInput.split(" ");
        ArrayList<String> list = new ArrayList<>();
        //System.out.println("Список переданных свойств");
        for (int i = 2; i < tstr.length; i++) {
            list.add(tstr[i]);
            //System.out.println(tstr[i]);
        }
        ArrayList<String> compare = new ArrayList<>();

        for (int j = 0; j < list.size(); j++) {
            if (!compare.contains(list.get(j))) {
                compare.add(list.get(j));
            }
        }
        //System.out.println("Список переданных свойств");
        /*for (int i = 0; i < compare.size(); i++) {

            System.out.println(compare.get(i));
        }*/
       /* //множество с дубликатами
         HashSet<String> duplicates = new HashSet<>();
//множество для отслеживания повторяющихся элементов
         Set<String> tracking = new HashSet<>();
//пробегаемся по всем пользователям
        for (String user: list) {
            //добавляем их во множество для отслеживания
            //если не получилось добавить, то значит пользователь уже встречался в списке
            if (!tracking.add(user)) {
                //в этом случае добавляем его во множество дубликатов
                duplicates.add(user);
            }
        }*/
        /**
         * Convert Set to List
         */
        // List<String> list2 = new ArrayList<String>( duplicates );
        // System.out.println(" list2.size() = " + list2.size());
        if (compare.size() != 0) {
            for (int i = 0; i < finalList.size(); i++) {
                String[] tyu = new String[2];
                tyu = finalList.get(i);
            /*String property1 = tyu[0];
            String property2 = tyu[1];*/

                for (int j = 0; j < compare.size(); j++) {
                    try {
                        if (compare.get(j).compareTo(tyu[0]) == 0) {
                            Main.wrongProperty[0] = tyu[0];
                            count++;
                        }
                        if (compare.get(j).compareTo(tyu[1]) == 0) {
                            Main.wrongProperty[1] = tyu[1];
                            count++;
                        }
                    } catch (NullPointerException e) {
                        //System.out.println("error");
                    }

                }
                if (count == 2) {

                    return true;
                }
                count = 0;
            }
        }
       /* if (
                (stringInput.contains("ODD") && stringInput.contains("-ODD")) || (stringInput.contains("EVEN") && stringInput.contains("-EVEN")) ||
                        (stringInput.contains("PALINDROMIC") && stringInput.contains("-PALINDROMIC")) || (stringInput.contains("BUZZ") && stringInput.contains("-BUZZ")) ||
                        (stringInput.contains("DUCK") && stringInput.contains("-DUCK")) || (stringInput.contains("GAPFUL") && stringInput.contains("-GAPFUL")) || (stringInput.contains("SPY") && stringInput.contains("-SPY")) || (stringInput.contains("SQUARE") && stringInput.contains("-SQUARE")) || (stringInput.contains("SUNNY") && stringInput.contains("-SUNNY")) ||
                        (stringInput.contains("JUMPING") && stringInput.contains("-JUMPING")) || (stringInput.contains("HAPPY") && stringInput.contains("-HAPPY")) || (stringInput.contains("SAD") && stringInput.contains("-SAD")) || (stringInput.contains("ODD") && stringInput.contains("EVEN")) || (stringInput.contains("DUCK") && stringInput.contains("SPY")) || (stringInput.contains("SUNNY") && stringInput.contains("SQUARE"))) {
            return true;
        }*/
        /*for (String property : properties) {
            System.out.println( " str input " + stringInput + " property = " + property);
            count = stringInput.toUpperCase().contains(property) ? count + 1 : count + 0;
            System.out.println("\ncount " + count);
            if (count == 2) break;
        }*/
        //return count == 2; // если да то выведет ошибку свойств
        return false;
    }
}

class DisplayingMessages extends Main {
    public static void printInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }

    public static void printProperties(long number, int lengthList) {
        if (lengthList == 0) {
            System.out.printf("Properties of %,d\n", number);
            for (int i = 0; i < PROPERTIES.length - 12; i++) {
                System.out.printf("%12s: %b%n", PROPERTIES[i].toLowerCase(), getAllPropertiesValue(number)[i]);
            }
        } else {
            StringBuilder propertiesToString = new StringBuilder();
            for (int i = 0; i < PROPERTIES.length - 12; i++) {
                propertiesToString.append(getAllPropertiesValue(number)[i] ? PROPERTIES[i].toLowerCase() + ", " : "");
            }
            System.out.printf("%,16d is %s%n", number, propertiesToString.substring(0, propertiesToString.length() - 2));
        }
    }

    public static void printErrorInvalidProperties(String[] enteredProperties, int[] indexesPropertyValues, int invalidProperties) {
        for (int i = 0; i < enteredProperties.length; i++) {
            if (indexesPropertyValues[i] == PROPERTIES.length && invalidProperties < 2) {
                System.out.printf("The property [%s] is wrong.\nAvailable PROPERTIES: %s\n",
                        enteredProperties[i], Arrays.toString(PROPERTIES));
            } else if (invalidProperties > 1) {
                System.out.printf("The PROPERTIES %s are wrong.\nAvailable PROPERTIES: %s\n",
                        Arrays.toString(enteredProperties), Arrays.toString(PROPERTIES));
                break;
            }
        }
    }

    public static void printErrorMutuallyExclusiveNumbers(String[] enteredProperties) {
        System.out.print("The request contains mutually exclusive PROPERTIES: ");
        for (int i = 0; i < enteredProperties.length; i++) {
            System.out.print(enteredProperties[i] + " ");
        }
        System.out.println("\nThere are no numbers with these PROPERTIES.");

    }
}

class NumberProperties {
    final static String[] PROPERTIES = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC",
            "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD", "-EVEN", "-ODD",
            "-BUZZ", "-DUCK", "-PALINDROMIC",
            "-GAPFUL", "-SPY", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD"};

    static boolean[] getAllPropertiesValue(long number) {
        return new boolean[]{isEven(number), !isEven(number), isBuzz(number),
                isDuck(number), isPalindrome(number), isGapful(number),
                isSpy(number), isSquare(number), isSunny(number), isJumping(number), isHappy(number), !isHappy(number),
                !isEven(number), isEven(number), !isBuzz(number),
                !isDuck(number), !isPalindrome(number), !isGapful(number),
                !isSpy(number), !isSquare(number), !isSunny(number), !isJumping(number), !isHappy(number), isHappy(number)
        };
    }

    static boolean isSpy(long number) {
        String[] str = String.valueOf(number).split("");
        int nSum = 0;
        int nProd = 1;
        for (String s : str) {
            nSum += Integer.parseInt(s);
            nProd *= Integer.parseInt(s);
        }
        return nSum == nProd;
    }

    static boolean isJumping(long number) {
        String[] str = String.valueOf(number).split("");
        for (int i = 0; i < str.length - 1; i++) {
            if (Math.abs(Long.parseLong(str[i]) - Long.parseLong(str[i + 1])) != 1) {
                return false;
            }
        }
        return true;
    }

    static boolean isHappy(long number) {
        //System.out.println("isHappy " + number);
        long temp = number;
        boolean flag = false;

        while (!flag) {


            ArrayList<Long> list = new ArrayList<Long>();
            long temp2 = temp;
            while (temp2 > 0) {
                list.add(temp2 % 10);
                temp2 /= 10;
            }
            long sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i) * list.get(i);
            }
            //System.out.println("sum = " + sum);
            if (sum == 1 || sum == 10 || sum == 100) {
                flag = true;
            }
            if (sum == 4 || sum == 16 || sum == 37 || sum == 58 || sum == 89 || sum == 145 || sum == 42 || sum == 20) {

                return false;
            }
            temp = sum;

        }
        return true;
    }

    static boolean isPalindrome(long number) {
        StringBuilder sb = new StringBuilder(String.valueOf(number));
        return sb.substring(0, sb.length() / 2).equals(sb.reverse().substring(0, sb.length() / 2));
    }

    static boolean isGapful(long number) {
        String[] str = String.valueOf(number).split("");
        return number / 100 > 0 && number % Integer.parseInt(str[0] + str[str.length - 1]) == 0;
    }

    static boolean isEven(long number) {
        return (number % 2 == 0);
    }

    static boolean isBuzz(long number) {
        return (number % 7 == 0 || number % 10 == 7);
    }

    static boolean isDuck(long number) {
        return String.valueOf(number).substring(1).contains("0");
    }

    static boolean isSquare(long number) {
        return number % Math.sqrt(number) == 0;
    }

    static boolean isSunny(long number) {
        return (number + 1) % Math.sqrt(number + 1) == 0;
    }
}
/*
package numbers;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
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

        System.out.println("Welcome to Amazing Numbers!\n\nSupported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        Scanner scanner = new Scanner(System.in);
         long numberFirst = 0L;
        do {
            System.out.println("Enter a request: ");
            String number = scanner.nextLine();
            String[] numbers = number.split(" ");

            try {
                numberFirst = Long.parseLong(numbers[0]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка");
            }
            if (numbers.length == 2) {
                if (checkIfNatural((numbers))) {
                    continue;
                }

                for (long i = Long.parseLong(numbers[0]); i < Long.parseLong(numbers[0]) + Long.parseLong(numbers[1]); i++) {
                    Methods(i, 2);
                }
            }
             else if (numbers.length >= 3) {

                if (checkIfNatural((numbers))) {
                    continue;
                }
                long startNumber = Long.parseLong(numbers[0]);
                long kolvoNumbers = Long.parseLong(numbers[1]);
                String[] property = new String[numbers.length - 2];
                for (int i = 0; i < property.length; i++) {
                    property[i] = numbers[2 + i].toLowerCase(Locale.ROOT);
                }

                int count = 0;
                long tempNum = startNumber;
                while (count < kolvoNumbers) {
                    if (Methods(tempNum, property)) {

                        count++;
                    }
                    //System.out.println("temp Num " + tempNum);
                    tempNum++;
                }
            } else {
                try {

                    if (numberFirst == 0) break;
                    String[] temp = new String[1];
                    temp[0] = numbers[0];
                    checkIfNatural(temp);
                    if (!isNatural) continue;
                    Methods(numberFirst, 1);
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            }
        } while (true);
        System.out.println("Goodbye!");
    }

    public static boolean isJumping(long number) {
        boolean isJumping = true;
        int countDigits = 0;
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (number > 0) {
            int lastDigit = (int) (number % 10);
            digits.add(lastDigit);
            countDigits++;
            number /= 10;
        }
        for (int i = 0; i < digits.size() - 1; i++) {
            if (Math.abs(digits.get(i) - digits.get(i + 1)) != 1) {
                isJumping = false;
                break;
            }
        }
        return isJumping;

    }

    static void Methods(long numberFirst, int param) {
        String[] temp = new String[1];
        temp[0] = String.valueOf(numberFirst);
        checkIfNatural(temp);
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
            System.out.println("\tjumping: " + isJumping(numberFirst));
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
            if (isJumping(numberFirst)) builder.append("jumping, ");
            builder.delete(builder.length() - 2, builder.length());
            String res = builder.toString();
            System.out.println(res);
        }

    }

    static boolean Methods(long numberFirst, String[] property) {
        String[] temp = new String[1];
        temp[0] = String.valueOf(numberFirst);
        checkIfNatural(temp);
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
        if (isJumping(numberFirst)) builder.append("jumping, ");

        builder.delete(builder.length() - 2, builder.length());

        String res = builder.toString();
        boolean[] flag = new boolean[property.length];
        for (int i = 0; i < property.length; i++) {
            if (res.contains(property[i])) flag[i] = true;
        }
        //System.out.println(property[0] + " find or not " + flag[0] );
        boolean resFlagProprty = true;
        for (int i = 0; i < flag.length; i++) {

            if (!flag[i]) {
                resFlagProprty = false;
                break;
            }

        }
        if (resFlagProprty) {
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
        String[] properties = new String[]{"odd", "even", "buzz", "gapful", "duck", "palindromic", "spy", "sunny", "square", "jumping"};
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
                    "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING]");
            flag = false;
        } else if (exits.length == 2 && (!exits[0] && !exits[1])) {
            System.out.println("The properties " + property[0] + ", " + property[1] + " are wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
            flag = false;
        } else if (exits.length == 2 && !exits[0]) {
            System.out.println("The property " + property[0] + " is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
            flag = false;
        } else if (exits.length == 2 && !exits[1]) {
            System.out.println("The property " + property[1] + " is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
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

    */
/*static void checkIfNatural(long userNumber) {
        isNatural = userNumber > 0 ? true : false;

        if (!isNatural) {
            System.out.println("The first parameter should be a natural number or zero.");

        }
    }*//*


    static boolean checkIfNatural(String[] userNumber) {
        boolean IsNeedToReturnInput = false;
        boolean wasIn = false;
        int granicha = 2;
        if(userNumber.length == 1) granicha = 1;
        for (int i = 0; i < granicha; i++) {

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
*/
