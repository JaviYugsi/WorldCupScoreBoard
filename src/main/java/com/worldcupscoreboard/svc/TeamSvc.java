package com.worldcupscoreboard.svc;

import com.worldcupscoreboard.model.Country;
import com.worldcupscoreboard.model.ScoreBoard;
import com.worldcupscoreboard.model.Team;

/**
 * This class provide all services methods related to the Team class Model
 */
public class TeamSvc {

    public Team enterValidTeam( ) {
        boolean invalidInput = true;
        do {
            String teamCountry = ScoreBoard.INPUT_OUT_PUT_SVC.validateInputTeam();
            if (teamCountry != null) {
                Team newTeam = new Team(Country.valueOf(teamCountry));
                // check if this Team is already playing a game
                if (ScoreBoard.ongoingGames.size() == 0
                        || (ScoreBoard.ongoingGames.values().stream()
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
}
