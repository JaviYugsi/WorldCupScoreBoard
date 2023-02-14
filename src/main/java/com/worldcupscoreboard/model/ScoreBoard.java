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
}
