package algoritmen;

import java.util.Scanner;

public class Faculteit {
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a number");

        int n = Integer.parseInt(myObj.nextLine());  // Read user input
        System.out.println("number is: " + n);  // Output user input

        System.out.println("De faculteit van "+n+" is "+faculteit(n));
    }
    public static int faculteit(int n) {
        if (n == 0) {
            return 1;
        } else return (n * faculteit(n - 1));
    }
}
