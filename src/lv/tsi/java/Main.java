package lv.tsi.java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int myNum = rand.nextInt(100) + 1;
        System.out.println(myNum);
        String answer;
        boolean userLost = true;
        do {
            for (int i = 1; i < 11; i++) {
                System.out.println("Try #" + i);
                int userNum = scan.nextInt();

                if (myNum < userNum) {
                    System.out.println("my number is smaller than yours");
                } else if (myNum > userNum) {
                    System.out.println("my number is bigger than yours");
                } else {
                    System.out.println("you won!!!");
                    userLost = false;
                    break;
                }
            }
            if (userLost) {
                System.out.println("you lost!!!");
            }
            System.out.print("new game? (Y/N)");
            answer = scan.next();
        } while (answer.equals("Y"));
        System.out.println("goodbye!");
    }
}