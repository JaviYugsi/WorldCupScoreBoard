package com.worldcupscoreboard.svc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputOutPutSvcTest {

    InputOutPutSvc inputOutPutSvc;

    @BeforeEach
    void setUp() {
        inputOutPutSvc = new InputOutPutSvc();
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }

    @Test
    void enterValidScoreBadRequest() {

        String invalidStr = "555";
       provideConsoleInput(invalidStr);
        String res = inputOutPutSvc.enterValidScore();
        assertEquals(null, res);
    }

    @Test
    void enterValidScoreGoodRequest() {

        String validStr = "10";
       provideConsoleInput(validStr);
        String res = inputOutPutSvc.enterValidScore();
        assertEquals(validStr, res);
    }

    private void provideConsoleInput(String input) {
        InputOutPutSvc test = new InputOutPutSvc();
        InputStream sysInBackUp = System.in;
        ByteArrayInputStream consoleInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(consoleInput);

    }

    @Test
    void enterValidStringOptionGoodReq() {
        String valid = "3";
        provideConsoleInput(valid);
        String res = inputOutPutSvc.enterValidStringOption();
        assertEquals(valid, res);
    }

    @Test
    void enterValidStringOptionBadReq() {
        String valid = "33";
        provideConsoleInput(valid);
        String res = inputOutPutSvc.enterValidStringOption();
        assertEquals(null, res);
    }

    @Test
    void enterValidGameIdGoodReq() {
        String valid = "1234567895";
        provideConsoleInput(valid);
        String res = inputOutPutSvc.enterValidGameId();
        assertEquals(valid, res);
    }

    @Test
    void enterValidGameIdBadReq() {
        String inValid = "12345678958545875";
        provideConsoleInput(inValid);
        String res = inputOutPutSvc.enterValidGameId();
        assertEquals(null, res);
    }
}