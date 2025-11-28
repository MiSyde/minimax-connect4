package tests;

import core.*;
import org.junit.jupiter.api.*;
import java.awt.*;

public class GameTests {
    Game game;

    @BeforeEach
    public void setupTests(){
        game = new Game();
    }

    @Test
    @DisplayName("In a Row")
    public void inARowTest(){
        game.addToBoard(0, 1, Color.RED);
        game.addToBoard(0, 3, Color.RED);
        game.addToBoard(0, 2, Color.RED);
        game.addToBoard(0, 4, Color.RED);
        Assertions.assertTrue(game.getWon());
    }

    @Test
    @DisplayName("In a Column")
    public void inAColumnTest(){
        game.addToBoard(0, 1, Color.RED);
        game.addToBoard(1, 1, Color.RED);
        game.addToBoard(3, 1, Color.RED);
        game.addToBoard(2, 1, Color.RED);
        Assertions.assertTrue(game.getWon());
    }

/*
        [0,0][0,1][0,2][0,3][0,4][0,5]
        [1,0][1,1][1,2][1,3][1,4][1,5]
        [2,0][2,1][2,2][2,3][2,4][2,5]
        [3,0][3,1][3,2][3,3][3,4][3,5]
        [4,0][4,1][4,2][4,3][4,4][4,5]
        [5,0][5,1][5,2][5,3][5,4][5,5]
        [6,0][6,1][6,2][6,3][6,4][6,5]
*/

    @Test
    @DisplayName("Diagonally to the Left")
    public void diagonallyToTheLeftTrueTest(){
        game.addToBoard(0, 5, Color.RED);
        game.addToBoard(1, 4, Color.RED);
        game.addToBoard(3, 2, Color.RED);
        game.addToBoard(2, 3, Color.RED);
        Assertions.assertTrue(game.getWon());
    }

    @Test
    @DisplayName("3 in a row")
    public void diagonallyToTheLeftFalseTest(){
        game.addToBoard(0, 1, Color.RED);
        game.addToBoard(0, 2, Color.RED);
        game.addToBoard(0, 3, Color.RED);
        Assertions.assertFalse(game.getWon());
    }

    @Test
    @DisplayName("Diagonally to the Right")
    public void diagonallyToTheRightTrueTest(){
        game.addToBoard(3, 3, Color.RED);
        game.addToBoard(4, 2, Color.RED);
        game.addToBoard(6, 0, Color.RED);
        game.addToBoard(5, 1, Color.RED);
        Assertions.assertTrue(game.getWon());
    }




}
