package com.worldcupscoreboard.svc;


import java.io.IOException;
import java.util.Scanner;

/**
 * This class serve as a toolbox for the game
 */
public class WorldCupScoreBoardSvc {

    private static final Scanner inputScanner = new Scanner(System.in);


    public void pressEnterContinue() {
        try {
            System.out.println("Press enter to continue.");
            System.in.read();
            inputScanner.nextLine();
        } catch (IOException e) {
        }
    }

    /**
     * This method reads the System.in and checks that is in a valid format.
     * Valid String will be in range 0 - 5
     * @return
     */
    public String enterValidStringOption () {
        String res = null;
        try {
            res = inputScanner.next().trim();

        } catch (Exception e) {

        }
        return res;
    }

    /**
     * This method reads the System.in and checks that is in a valid format.
     * Valid Score will be in range 0 - 10
     * @return
     */
    public String enterValidScore () {

        return null;
    }

    /**
     * This method reads the System.in and checks that is in a valid format.
     * Valid GameId cannot be larger than 10 chars
     * @return
     */
    public String enterValidGameId () {

        return null;
    }

}
