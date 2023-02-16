# World Score Board

## Description
This application has been thought to be a console application.
The user will have a set options to interact with the app. The teams available are only the ones which
classified to the last FIFA World Cup.
- JDK used: Oracle OpenJDK 18.0.1
- JUnit 5.7.2


## Asumptions

- The scores can be updated at any time, but only can be updated with integers between 0 and 10. Being realistic, the score should be updated by one everytime any team scores a goal
- At the main menu, the user only can type values between 0 - 5. These are the options shown at the menu
- Each game has its own Id, it is used to identify the game and update it when needed.
- For each case the user will be prompted to use the console input.
- The application will be running indefinitely until the used decides to stop it
- If the user does not provide a valid country, the program will keep asking for one until one is provided
- In order to sort the games, one specific field has been added to the Game Object; gameTimeField.
This is used to sort the games when the total of goals scored at one game are equal between different games.
- There are 2 score board whcih are shown in different stages of the application. As requested, the sorted one is the one
that appears when the summary option is selected


## Acknowledgments
I would like to give a special thanks for the opportunity. I have enjoyed the time I spent writting this code.
I have to accept the fact that I met difficulties on writing this code following TDD principles, but I have done my best trying it.
Definitely, this exercise has helped me to acknowledge my capacities, my pros and cons. How much I have learnt and 
how much I still have to.

Sincere thanks,

Javier

