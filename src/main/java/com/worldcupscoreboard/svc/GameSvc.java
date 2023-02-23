package com.worldcupscoreboard.svc;

import com.worldcupscoreboard.model.Game;
import com.worldcupscoreboard.model.ScoreBoard;

public class GameSvc {

    /**
     * The user is requested to enter a GameId from the ScoreBoard.
     * If the GameId is correct a Game object is returned, otherwise the process will be repeated.
     * @return
     */
    public Game pickOngoingGame() {
        ScoreBoard.printGamesMap(ScoreBoard.ongoingGames.values());
        boolean validIdGame = false;
        do {
            System.out.println("Pick one Game using its Game Id:");
            String idGame = ScoreBoard.INPUT_OUT_PUT_SVC.enterValidGameId();
            try {
                Long intIdGame = Long.valueOf(idGame);
                validIdGame = ScoreBoard.ongoingGames.containsKey(intIdGame);
                if(validIdGame) {
                    return ScoreBoard.ongoingGames.get(intIdGame);
                } else {
                    ScoreBoard.printGamesMap(ScoreBoard.ongoingGames.values());
                    System.out.println("Invalid Game Id, select another one.");
                }
            } catch (NumberFormatException e) {
                ScoreBoard.printGamesMap(ScoreBoard.ongoingGames.values());
                System.out.println("Invalid Game Id, select another one.");
            }
        } while(!validIdGame);
        return null;
    }


    public void updateVisitorTeamScore(Game gameToUpdate) {
        boolean validScore = false;
        do {
            System.out.println("Enter new score for Visitor Team, " + gameToUpdate.getVisitorTeam().getCountry() + ":");
            String visitorNewScore = ScoreBoard.INPUT_OUT_PUT_SVC.enterValidScore();
            try {
                Integer intVisitorNewScore = Integer.valueOf(visitorNewScore);
                validScore = true;
                gameToUpdate.setVisitorTeamScore(intVisitorNewScore);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number try again.");
            }
        } while (!validScore);
    }

    public void updateLocalTeamScore(Game gameToUpdate) {
        boolean validScore = false;
        do {
            System.out.println("Enter new score for Local Team, " + gameToUpdate.getLocalTeam().getCountry() + ":");
            String localNewScore = ScoreBoard.INPUT_OUT_PUT_SVC.enterValidScore();
            try {
                Integer intLocalNewScore = Integer.valueOf(localNewScore);
                validScore = true;
                gameToUpdate.setLocalTeamScore(intLocalNewScore);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number try again.");
            }
        } while (!validScore);
    }
}
