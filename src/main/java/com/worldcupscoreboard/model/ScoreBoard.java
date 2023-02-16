package com.worldcupscoreboard.model;

import com.worldcupscoreboard.svc.InputOutPutSvc;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final LocalDateTime localDateTime;
    private final Map<Long, Game> ongoingGames;
    public static final InputOutPutSvc INPUT_OUT_PUT_SVC = new InputOutPutSvc();

    public ScoreBoard(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        this.ongoingGames = new LinkedHashMap<>();
    }

    /**
     * Method created for testing reasons
     * @param inGame Game to test
     */
    public void addNewGame (Game inGame) {
        ongoingGames.put(Long.valueOf(inGame.getIdGame()), inGame);
    }

    /**
     * Once started the game will be kept running until the user decide to finish it
     * @throws InterruptedException
     */
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
        String input = INPUT_OUT_PUT_SVC.enterValidStringOption();
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

    /**
     * This method uses the Total Score of each game and the game id to Sort the elements.
     * The GameId field was created with this requirement in mind
     */
    private void showGamesSummary() {
        List<Game> newSortedMap = ongoingGames.values().stream()
                .sorted(Comparator.comparing(game -> game.getGameTime(), Comparator.reverseOrder()))
                .sorted(Comparator.comparing(game -> game.getTotalGameScore(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        printGamesMap(newSortedMap);
        INPUT_OUT_PUT_SVC.pressEnterContinue();
    }

    /**
     * The user has to enter a game id in order to delete any game.
     */
    private void finishGame() throws InterruptedException {
        if (ongoingGames.isEmpty()) {
            System.out.println("There are not ongoing games. Create one first.");
            Thread.sleep(1500);
            return;
        }
        Game gameToFinish = pickOngoingGame();
        //The possible exception has already dealed in the pickOngoingGame method
        ongoingGames.remove(Long.valueOf(gameToFinish.getIdGame()));
        System.out.println("Game removed successfully. New Score board:");
        printGamesMap(ongoingGames.values());
        INPUT_OUT_PUT_SVC.pressEnterContinue();
    }

    private void updateGame() throws InterruptedException {
        if (ongoingGames.isEmpty()) {
            System.out.println("You must create a Game to start updating.");
            Thread.sleep(1500);
            return;
        }
        Game gameToUpdate = pickOngoingGame();
        gameToUpdate.updateLocalTeamScore();
        gameToUpdate.updateVisitorTeamScore();
        System.out.println("Game updated successfully. New Score board:");
        printGamesMap(ongoingGames.values());
        INPUT_OUT_PUT_SVC.pressEnterContinue();
    }

    /**
     * This method starts a new Game.
     * The user will be requested to enter the correct Country names to do so
     */
    private void startNewGame() {
        System.out.println("Type local team and press enter:");
        Team localTeam = enterValidTeam();
        System.out.println("Type visitor team and press enter:");
        Team visitorTeam = enterValidTeam();

        LocalDateTime playDateTime = LocalDateTime.now();
        Game newGame = new Game(playDateTime, localTeam, visitorTeam);
        // Since each game can be updated at the same time, each game will run in its own thread
        Thread thread = new Thread(newGame);
        thread.start();

        System.out.println("New game started: "
                + newGame.getLocalTeam().getCountry()
                + " VS "
                + newGame.getVisitorTeam().getCountry());

        ongoingGames.put(Long.valueOf(newGame.getIdGame()), newGame);
        INPUT_OUT_PUT_SVC.pressEnterContinue();
    }

    public Team enterValidTeam() {
        boolean invalidInput = true;
        do {
            String teamCountry = INPUT_OUT_PUT_SVC.validateInputTeam();
            if (teamCountry != null) {
                Team newTeam = new Team(Country.valueOf(teamCountry));
                // check if this Team is already playing a game
                if (ongoingGames.size() == 0
                        || (ongoingGames.values().stream()
                            .noneMatch(game -> game.getLocalTeam().equals(newTeam)
                                        || game.getVisitorTeam().equals(newTeam)))) {
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

    /**
     * The user is requested to enter a GameId from the ScoreBoard.
     * If the GameId is correct a Game object is returned, otherwise the process will be repeated.
     * @return
     */
    public Game pickOngoingGame() {
        printGamesMap(ongoingGames.values());
        boolean validIdGame = false;
        do {
            System.out.println("Pick one Game using its Game Id:");
            String idGame = INPUT_OUT_PUT_SVC.enterValidGameId();
            try {
                Long intIdGame = Long.valueOf(idGame);
                validIdGame = ongoingGames.containsKey(intIdGame);
                if(validIdGame) {
                    return ongoingGames.get(intIdGame);
                } else {
                    printGamesMap(ongoingGames.values());
                    System.out.println("Invalid Game Id, select another one.");
                }
            } catch (NumberFormatException e) {
                printGamesMap(ongoingGames.values());
                System.out.println("Invalid Game Id, select another one.");
            }
        } while(!validIdGame);
        return null;
    }


    private void printGamesMap(Collection<Game> ongoingGames) {
        System.out.println("========================== SCORE BOARD ==========================");
        System.out.format("%15s%15s%15s%10s%10s%n", "GAME ID", "LOCAL TEAM", "VISITOR TEAM", "L. SCORE", "V. SCORE");
        ongoingGames.forEach(game -> {
            System.out.format("%15s%15s%15s%10s%10s%n",
                    game.getIdGame(),
                    game.getLocalTeam().getCountry(),
                    game.getVisitorTeam().getCountry(),
                    game.getLocalTeamScore(),
                    game.getVisitorTeamScore());
        });
    }
}
