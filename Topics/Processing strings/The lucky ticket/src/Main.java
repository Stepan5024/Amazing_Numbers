import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);


        String s1 = scanner.nextLine();
        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < s1.length(); i++) {

            int temp = s1.charAt(i);
            if(i < 3){
                num1 += temp;
            }else num2 += temp;


        }
        if(num1 == num2) System.out.println("Lucky");
        else System.out.println("Regular");
    }
}