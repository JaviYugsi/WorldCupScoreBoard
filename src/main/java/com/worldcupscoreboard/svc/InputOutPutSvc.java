package com.worldcupscoreboard.svc;

import com.worldcupscoreboard.model.Country;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class InputOutPutSvc {


    private Scanner inputScanner ;


         public void pressEnterContinue() {
             inputScanner = new Scanner("\n");
            try {
                System.out.println("Press enter to continue.");
                System.in.read();
                inputScanner.nextLine();
            } catch (IOException e) {
            }
        }

        /**
         * This method reads the System.in and checks that is in a valid format.
         * Valid String will one char length
         * @return
         */
        public String enterValidStringOption () {
            inputScanner = new Scanner(System.in);
            String res = inputScanner.next().trim();
            if (res.length() > 1)
                return "";
            return res;
        }

        /**
         * This method reads the System.in and checks that is in a valid format.
         * Valid Score will be in range 0 - 10
         * @return
         */
        public String enterValidScore () {
            inputScanner = new Scanner(System.in);
            String input = inputScanner.next();
            if (input.length() > 2) {
                return null;
            }
            return input;
        }

        /**
         * This method reads the System.in and checks that is in a valid format.
         * Valid GameId cannot be larger than 10 chars
         * @return
         */
        public String enterValidGameId () {
            inputScanner = new Scanner(System.in);
            String input = inputScanner.next();
            if (input.length() > 10)
                return null;
            return input;
        }

    /**
     * This method validates that the input Country name matches with one of the classified countries to the last
     * FIFA World Cup 2022
     * @return
     */
    public String validateInputTeam () {
            inputScanner = new Scanner(System.in);
            String input = inputScanner.next().toUpperCase();
            if (Arrays.stream(Country.values()).anyMatch(country -> country.name().equals(input)))
                return input;

            return null;
        }

}
