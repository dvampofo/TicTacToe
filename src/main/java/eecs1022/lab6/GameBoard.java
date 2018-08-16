package eecs1022.lab6;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by davidampofo on 2018-04-11.
 */

public class GameBoard {


    char[][] gameBoard;
    private boolean gameOnGoing = true;


    // Constructor for the board
    public GameBoard() {

        gameBoard = new char[3][3];
        for (int row = 0; row < gameBoard.length; row++) {
            Arrays.fill(gameBoard[row], ' ');
        }
    }


    // Display board to the screen
    public void displayBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                System.out.print(gameBoard[row][col]);
                if (col == 0 || col == 1) {
                    // Print vertical bars when at at col 0 and col 1
                    System.out.print("    |");
                }
            }
            if (row == 0 || row == 1) {
                System.out.println("\n_________________");
            }
        }
        System.out.println();
    }

    // This method will validate if a players move is valid or not and return true if the move
    // was completed
    public boolean makeMove(char player, int row, int col) {
        // Validate that moves will only be within these dimensions
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            // Make sure that board has an empty space
            if (gameBoard[row][col] != ' ') {
                return false;
            } else {
                // Put the player, X or O at that space
                gameBoard[row][col] = player;
                return true;
            }
        } else {
            return false;
        }
    }

    // This method will return true if the game is still active
    public boolean gameActive() {
        return true;
    }

    // Method will ask the user to pick a column and row, validate the inputs, and call the method
    // makeMove

    public void askPlayer(char player)
    {
        Scanner keyboard = new Scanner(System.in);
        int row, col;


        do {
            System.out.printf("Player %s Enter a row from (1-3): \n", player );
            row = keyboard.nextInt();

            System.out.printf("Player %s Enter a column from (1-3): \n", player );
            col = keyboard.nextInt();

        }while (notValid(row,col)); // If this is true, it is not a valid thing and it'll keep asking

        makeMove(player, row-1, col-1); // -1 added so the X/O is placed in the correct position
    }

    // Method will validate if the row and col are between 1-3
    // and if position is currently empty
    public boolean notValid(int row, int col)
    {

        // If any of these return true, it is an invalid choice the player has made
        if (row > 3 || row < 1 || col > 3 || col < 1 || !isEmpty(row, col))
            return true;


        return false;
    }

    public boolean isEmpty(int row, int col)
    {
        if(gameBoard[row -1 ][col - 1 ] == ' ')
        {
            return true; // Space is empty
        }
        else
        {
            System.out.println("That position is already taken.\n");
            return false;
        }
    }


    // This method will check for winner to see if there are 3 X's or 3 O's in a row
    // and will return true if there is a winner, false otherwise.

    public boolean checkForWinner()
    {
        // Loop over each ROW and check for winner
        for (int row = 0; row < gameBoard.length; row++)
        {
            if(gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2]
                    && gameBoard[row][0] != ' ')
            {
                System.out.println("The winner is " + gameBoard[row][0]);
                gameOnGoing = false;
            }
        }

        // Loop over each COLUMN and check for winner
        for (int col = 0; col < gameBoard.length; col++)
        {
            if(gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col]
                    && gameBoard[0][col] != ' ')
            {
                System.out.println("The winner is " + gameBoard[0][col]);
                gameOnGoing = false;
            }
        }

        // Check diagonals
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]
                && gameBoard[0][0] != ' ')
        {
            System.out.println("The winner is " + gameBoard[0][0]);
            gameOnGoing = false;
        }

        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2]
                && gameBoard[0][0] != ' ')
        {
            System.out.println("The winner is " + gameBoard[1][1]);
            gameOnGoing = false;
        }

        return false;
    }

}
