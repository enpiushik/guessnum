package lv.tsi.java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String answer;
        do {
        int myNum = rand.nextInt(100) + 1;
        System.out.println(myNum);
        boolean userLost = true;

            for (int i = 1; i <= 10; i++) {
                System.out.println("Try #" + i);
                int userNum = askNum();

                if (myNum < userNum) {
                    System.out.println("My number is smaller than yours");
                } else if (myNum > userNum) {
                    System.out.println("My number is bigger than yours");
                } else {
                    System.out.println("You won!!!");
                    userLost = false;
                    break;
                }
            }
            if (userLost) {
                System.out.println("You lost!!!");
            }
            System.out.print("New game? (y/n)");
            answer = askYN();
        } while (answer.equals("y"));
        System.out.println("Goodbye!");
    }


    static String askYN () {
        String answer;
        do {
            answer = scan.next();
            if (!answer.equals ("y") && !answer.equals("n")) {
                System.out.println("You can enter only 'y' or 'n'");
            } else {
                return answer;
            }
        } while (true);
    }


    static int askNum (){
        int answer;
        do {
            try {
                answer = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This is not a number! Please, enter a number.");
                scan.next();
                continue;
            }
            if (answer < 1 || answer > 100) {
                System.out.println("Please, enter a number between 1 and 100.");
            } else {
                return answer;
            }
        } while (true);
    }
}