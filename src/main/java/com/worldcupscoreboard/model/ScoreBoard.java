package com.worldcupscoreboard.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ScoreBoard {

    private final LocalDateTime localDateTime;
    private final Map<Long, Game> ongoingGames;
    private static final Scanner inputScanner = new Scanner(System.in);


    public ScoreBoard(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.ongoingGames = new TreeMap<>();
    }



    public void initScoreBoard() throws InterruptedException  {
        boolean conditional = true;
        do {
            conditional = printMenu();
        } while (conditional);
    }

    private boolean printMenu () throws InterruptedException  {
        System.out.println("\n\n Football World Cup Score Board ");
        System.out.println("-------------------------------------");
        System.out.println("1 - Start a new match");
        System.out.println("2 - Update one of the matches");
        System.out.println("3 - Finish one of the matches ");
        System.out.println("4 - Get a summary of the games");
        System.out.println("5 - Finish this ScoreBoard");

        System.out.println("\n ---> Select one option:");
        // test of enetering data
        String input = inputScanner.next().trim();
        switch (input) {
            case "1" :
                startNewGame();
                break;
            case "2" :
                updateGame();
                break;
            case "3" :
                finishGame();
                break;
            case "4" :
                showGamesSummary();
                break;
            case "5" :
                System.out.println("Finishing the ScoreBoard. Thanks for playing");
                return false;
            default:
                System.out.println("You didnt select a valid option. Try again");
                Thread.sleep(500);
        }
        return true;
    }

    private void showGamesSummary() {
    }

    private void finishGame() {
    }

    private void updateGame() {
    }

    private void startNewGame() {
    }
}
