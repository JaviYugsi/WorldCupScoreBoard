package com.worldcupscoreboard.main;

import com.worldcupscoreboard.model.ScoreBoard;

import java.time.LocalDateTime;

public class WorldCupScoreBoardMain {

    public static void main(String[] args) throws InterruptedException  {

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Welcome to this CupScoreBoard.");
        System.out.println("This board will be saved with local date time: " + localDateTime );

        ScoreBoard newScoreBoard = new ScoreBoard(localDateTime);

        newScoreBoard.initScoreBoard();

    }
}
