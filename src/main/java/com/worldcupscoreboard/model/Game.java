package com.worldcupscoreboard.model;

import java.time.LocalDateTime;

public class Game implements Runnable {

    private final String idGame;
    private final LocalDateTime dayOfGame;
    private final int gameTime;
    private final Team localTeam;
    private final Team visitorTeam;
    private int localTeamScore;
    private int visitorTeamScore;
    private int totalGameScore;

    public Game(LocalDateTime dayOfGame, Team localTeam, Team visitorTeam) {
        this.dayOfGame = dayOfGame;
        this.localTeam = localTeam;
        this.visitorTeam = visitorTeam;
        this.gameTime = generateTimeStart(dayOfGame);
        this.idGame = generateIdGame(dayOfGame, localTeam, visitorTeam);
        localTeamScore = 0;
        visitorTeamScore = 0;
    }

    private int generateTimeStart(LocalDateTime dayOfGame) {
       return  Integer.parseInt(String.valueOf(dayOfGame.getHour()) +
                String.valueOf(dayOfGame.getMinute()) +
                String.valueOf(dayOfGame.getSecond()));
    }

    private String generateIdGame(LocalDateTime dayOfGame, Team localTeam, Team visitorTeam) {
        return  String.valueOf(localTeam.getCountry().getIdCountry()) +
                String.valueOf(visitorTeam.getCountry().getIdCountry()) +
                String.valueOf(dayOfGame.getHour()) +
                String.valueOf(dayOfGame.getMinute()) +
                String.valueOf(dayOfGame.getSecond());
    }

    @Override
    public void run() {
    }

    public int getGameTime() {
        return gameTime;
    }

    public LocalDateTime getDayOfGame() {
        return dayOfGame;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public int getLocalTeamScore() {
        return localTeamScore;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setLocalTeamScore(int localTeamScore) {
        this.localTeamScore = localTeamScore;
        setTotalGameScore();
    }

    public int getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public void setVisitorTeamScore(int visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore;
        setTotalGameScore();
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    public void setTotalGameScore( ) {
        this.totalGameScore = this.localTeamScore + this.visitorTeamScore;
    }

}
