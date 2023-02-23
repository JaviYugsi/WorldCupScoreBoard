package com.worldcupscoreboard.svc;

import com.worldcupscoreboard.model.Country;
import com.worldcupscoreboard.model.Game;
import com.worldcupscoreboard.model.ScoreBoard;
import com.worldcupscoreboard.model.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GameSvcTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final ScoreBoard testScoreBoard = new ScoreBoard(LocalDateTime.now());

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    private void provideConsoleInput(String input) {
        ByteArrayInputStream consoleInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(consoleInput);
    }

    @Test
    void pickOngoingGameGoodReq() {
        Game testGame = new Game(LocalDateTime.now(), new Team(Country.CANADA), new Team(Country.MOROCCO));
        testScoreBoard.addNewGame(testGame);
        provideConsoleInput(testGame.getIdGame());
        Game res = testScoreBoard.getGameSvc().pickOngoingGame();
        assertEquals(testGame, res);
    }


    @Test
    void pickOngoingGameBadReq() {
        Game testGame = new Game(LocalDateTime.now(), new Team(Country.CANADA), new Team(Country.MOROCCO));
        testScoreBoard.addNewGame(testGame);
        provideConsoleInput("2345612345");
        try {
            Game res = testScoreBoard.getGameSvc().pickOngoingGame();
        } catch (Exception e) {
            boolean res = outContent.toString().contains("Invalid Game Id, select another one.");
            assertTrue(res);
        }
    }
}