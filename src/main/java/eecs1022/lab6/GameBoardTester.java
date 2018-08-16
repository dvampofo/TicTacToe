package eecs1022.lab6;

/**
 * Created by davidampofo on 2018-04-11.
 */

public class GameBoardTester {

    public static void main(String[] args)
    {

        GameBoard myGame = new GameBoard();
        myGame.displayBoard();
        System.out.println();

        int counter = 0 ;

        // Either want player X or O to go. Everytime we go through the looo
        // it flips between X and O.
        while(myGame.gameActive() && counter < 10)
        {
            if(counter % 2 == 0)
            {
                myGame.askPlayer('O');
            }
            else
            {
                myGame.askPlayer('X');
            }
            counter++;

            System.out.println("\n");
            myGame.displayBoard();
            myGame.checkForWinner();

            if (counter == 10)
            {
                System.out.println("Stalemate.\n");
            }
        }



    }
}
