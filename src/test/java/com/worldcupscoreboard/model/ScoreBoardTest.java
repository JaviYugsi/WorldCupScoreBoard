package com.worldcupscoreboard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void enterValidTeamGoodReq() {
        ScoreBoard testScoreBoard = new ScoreBoard(LocalDateTime.now());
        provideConsoleInput("MEXICO");
        Team res = testScoreBoard.enterValidTeam();
        Team expectedRes = new Team(Country.valueOf("MEXICO"));
        assertEquals(expectedRes, res);
    }

    private void provideConsoleInput(String input) {
        ByteArrayInputStream consoleInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(consoleInput);

    }
}