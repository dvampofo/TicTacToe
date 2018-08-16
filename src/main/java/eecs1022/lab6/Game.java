package eecs1022.lab6;

/**
 * Created by davidampofo on 2018-04-10.
 */

public class Game {

    String playerX;
    String playerO;
    String nextPlayer;
    String error;
    String winner;
    boolean hasError;
    boolean hasWinner;
    boolean isFull;

    // Create TicTacToe board
    char [][] board = {
            {'.' ,'.', '.'},
            {'.' ,'.', '.'},
            {'.' ,'.', '.'},
    };

    // Constructor for the players
    public Game(String playerX, String playerO, String nextPlayer)
    {
        this.playerX = playerX;
        this.playerO = playerO;

        // "Who plays first?" spinner
        if (nextPlayer.equals("Player X"))
        {
            this.nextPlayer = this.playerX;
        }
        else
        {
            this.nextPlayer = this.playerO;
        }
    }

    // Display board to the screen
    String displayBoard()
    {
        String b = "";

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++)
            {
                b += board[row][col] + "\t";
            }
            b += "\n";
        }
        return b;
    }

    // Checks if the board space is empty (if the space has '.', replace it with X or O.)
    public boolean makeMove(char player, int row, int col)
    {
        if (board[row][col] != '.') {
            return false;
        } else {
            // Put the player, X or O at that space
            board[row][col] = player;
            return true;
        }
    }

    // Updates when different Rows and Columns are choosen.
    public void play(int row, int col)
    {
        // If there is NO  winner and the board is NOT full, enter this statement
        if (!hasWinner && !isFull) {
            char player;

            this.hasError = false;
            this.error = "";

            // Change the symbol on the board
            if(nextPlayer.equals(playerX))
            {
                player = 'X';
            }
            else
            {
                player = 'O';
            }

            // hasError dectects if there is a X or O in the space. If there is something there
            // return a error message.
            hasError = !makeMove(player, row, col);
            if (hasError) {
                error = "Error: Slot @ (" + (row + 1) + "," + (col + 1) + ") already occupied.\n";
            }

            // Check for winner and change hasWinner to true when Rows, Columns, Diagonals line up
            hasWinner = checkForWinner();

            isFull = true;
            // Checking to see if the board is full with X's, O's. If board is is NOT full everytime play method,
            // is run, return false, otherwise isFull has default of true.
            for (int i = 0; i < board.length; i++) // ROW
            {
                for (int j = 0; j < board[i].length; j++) // COLUMN
                {
                    if (board[i][j] == '.') {
                        isFull = false;
                        break;
                    }
                }
            }

            // Switch players NAME (not the X,O) in the toString each time player goes (e.g. "Jim", then "Joe", "Jim" etc)
            if (nextPlayer.equals(this.playerX)) {
                nextPlayer = this.playerO;
            }
            else {
                nextPlayer = this.playerX;
            }
        }
        else {
            hasError = true;
            error = "Error: Game is already over.\n";
        }
    }

    // Check for winner
    public boolean checkForWinner()
    {
        //                                      R   O   W   S
        for (int row = 0; row < board.length; row++)
        {
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2]
                    && board[row][0] != '.')
            {
                if (board[row][0] == 'X') {
                    winner = this.playerX;
                }
                else {
                    winner = this.playerO;
                }
                return true;
            }
        }

        //                                 C   O   L   U   M   N   S
        for (int col = 0; col < board.length; col++)
        {
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col]
                    && board[0][col] != '.')
            {
                if (board[0][col] == 'X')
                {
                    winner = this.playerX;
                }
                else {
                    winner = this.playerO;
                }
                return true;
            }
        }

        //                            D   I   A   G   O   N   A   L   S
        // Top Left to Bottom Right
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]
                && board[0][0] != '.')
        {
            if (board[0][0] == 'X')
            {
                winner = this.playerX;
            }
            else {
                winner = this.playerO;
            }
            return true;
        }

        // Bottom Left to Top Right
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]
                && board[2][0] != '.')
        {

            if (board[1][1] == 'X')
            {
                winner = this.playerX;
            }
            else {
                winner = this.playerO;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String s = "";

        // If true, show error message
        if (hasError) {
            s += this.error + "\n";
        }

        s += "Current game status: \n" + this.displayBoard() + "\n";

        // If there is a WINNER, print their name
        if (hasWinner)
        {
            s += "Game is over with " + this.winner + " being the winner.\n";
        }
        // If there is a TIE (board is full AND there is NO winner)
        else if (isFull)
        {
            s += "Game is over with a tie " + this.playerX + " and " + this.playerO;
        }
        // Show next player to move
        else
        {
            s += "Next player to play: " + this.nextPlayer ;
        }

        return s;
    }
}
