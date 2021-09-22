import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        boolean b = true;
        for (int i = 0; i < str.length() / 2; i++) {
            String s1 = str.charAt(i) + "";
            String s2 = str.charAt(str.length() - i - 1) + "";
            if(!s1.equals(s2)) {
                b = false;
            }
        }
        if(b)System.out.println("yes");
        else System.out.println("no");
    }
}