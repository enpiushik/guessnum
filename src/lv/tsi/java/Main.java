package lv.tsi.java;

import jdk.dynalink.beans.StaticClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) {
        loadResults();

        String answer;
        do {

            System.out.println("What is your name?");
            String name = scan.next();

            long t1 = System.currentTimeMillis();

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
                    long t2 = System.currentTimeMillis();
                    System.out.println("You won!!!");
                    userLost = false;
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    results.add(r);
                    r.time = (t2 - t1);
                    results.sort(Comparator.<GameResult>comparingInt(r0 -> r0.triesCount)
                    .thenComparingLong (r0 -> r0.time));
                    break;
                }
            }
            if (userLost) {
                System.out.println("You lost!!!");
            }
            System.out.print("New game? (y/n)");
            answer = askYN();
        } while (answer.equals("y"));

        showResults();
        saveResults();

        System.out.println("Goodbye!");
    }

    private static void loadResults() {
        File file = new File("top_scores.txt");
        try (Scanner in = new Scanner(file)) {

            while (in.hasNext()) {
                GameResult result = new GameResult();
                result.name = in.next();
                result.triesCount = in.nextInt();
                result.time = in.nextLong();
                results.add(result);
            }
        } catch (IOException e) {
            System.out.println("Cannot load from file");
        }
    }

    private static void saveResults() {
        File file = new File("top_scores.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (GameResult r : results) {
                out.printf("%-30s %3d %d\n", r.name, r.triesCount, r.time);
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }
    }

//    private static void showResults() {
//        int count = Math.min(5, results.size());
//        for (int i = 0; i < count; i++) {
//            GameResult r = results.get(i);
//            System.out.printf("%s - %d - %.2fsec\n", r.name, r.triesCount, r.time / 1000.00);

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount))
                .limit (5)
                .forEach(r -> {
                    System.out.printf("%-25s %3d %.2fsec\n", r.name, r.triesCount, r.time / 1000.00);
                });
        }

    static String askYN() {
        String answer;
        do {
            answer = scan.next();
            if (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("You can enter only 'y' or 'n'");
            } else {
                return answer;
            }
        } while (true);
    }


    static int askNum() {
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