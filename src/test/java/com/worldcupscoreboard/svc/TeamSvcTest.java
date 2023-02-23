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

class TeamSvcTest {


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
    void enterValidTeamGoodReq() {
        ScoreBoard testScoreBoard = new ScoreBoard(LocalDateTime.now());
        provideConsoleInput("MEXICO");
        Team res = testScoreBoard.getTeamSvc().enterValidTeam();
        Team expectedRes = new Team(Country.valueOf("MEXICO"));
        assertEquals(expectedRes, res);
    }

    @Test
    void enterValidTeamBadReq() {
        ScoreBoard testScoreBoard = new ScoreBoard(LocalDateTime.now());
        provideConsoleInput("ITALY");
        try {
            Team res = testScoreBoard.getTeamSvc().enterValidTeam();
        } catch (Exception e) {
            boolean res = outContent.toString().contains("Pick a correct country. Pick a classified country to the last 2022 World Cup:");
            assertTrue(res);
        }
    }

    @Test
    void enterValidTeamBadReqExistingTeam() {
        ScoreBoard testScoreBoard = new ScoreBoard(LocalDateTime.now());
        Game testGame = new Game(LocalDateTime.now(), new Team(Country.ARGENTINA), new Team(Country.AUSTRALIA));
        provideConsoleInput("AUSTRALIA");
        try {
            Team res = testScoreBoard.getTeamSvc().enterValidTeam();
        } catch (Exception e) {
            boolean res = outContent.toString().contains("The selected country is already playing a game. Pick another one:");
            assertTrue(res);
        }
    }

}