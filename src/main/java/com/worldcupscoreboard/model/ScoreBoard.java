package com.worldcupscoreboard.model;

import com.worldcupscoreboard.svc.InputOutPutSvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ScoreBoard {

    private final LocalDateTime localDateTime;
    private final Map<Long, Game> ongoingGames;
    private static final InputOutPutSvc INPUT_OUT_PUT_SVC = new InputOutPutSvc();
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
        System.out.println("Type local team:");
        Team localTeam = validateInputTeam();

        System.out.println("Type visitor team:");
        Team visitorTeam = validateInputTeam();

        LocalDateTime playDateTime = LocalDateTime.now();
        Game newGame = new Game(playDateTime, localTeam, visitorTeam);
        Thread thread = new Thread(newGame);
        thread.start();

        System.out.println("New game started: "
                + newGame.getLocalTeam().getCountry()
                + " VS "
                + newGame.getVisitorTeam().getCountry());

        ongoingGames.put(Long.valueOf(newGame.getIdGame()), newGame);
        INPUT_OUT_PUT_SVC.pressEnterContinue();

    }

    public Team validateInputTeam() {
        boolean invalidInput = true;
        do {
            String team = inputScanner.next();
            String inCountry = team.toUpperCase();

            if (Arrays.stream(Country.values()).anyMatch(country -> country.name().equals(inCountry))) {
                Team newTeam = new Team(Country.valueOf(inCountry));
                // check if this Team is already playing a game
                if (ongoingGames.values().stream().noneMatch(game -> game.getLocalTeam().equals(newTeam) || game.getVisitorTeam().equals(newTeam))) {
                    invalidInput = false;
                    return newTeam;
                }
                System.out.println("The selected country is already playing a game. Pick another one:");
            } else {
                System.out.println("Pick a correct country. Pick a classified country to the last 2022 World Cup:");
            }

        } while (invalidInput);
        return null;
    }

}
