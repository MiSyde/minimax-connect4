package test;

import core.*;
import org.junit.jupiter.api.*;
import java.awt.*;

public class FourInARowTests {
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
            [][][][][][]
            [][][][][][]
            [][][][][][]
            [][][][][][]
            [][][][][][]
            [][][][][][]
            [][][][][][]
             */
    @Test
    @DisplayName("Diagonally to the Left -- True")
    public void diagonallyToTheLeftTrueTest(){
        game.addToBoard(0, 5, Color.RED);
        game.addToBoard(1, 4, Color.RED);
        game.addToBoard(3, 2, Color.RED);
        game.addToBoard(2, 3, Color.RED);
        Assertions.assertTrue(game.getWon());
    }

    @Test
    @DisplayName("Diagonally to the Left -- False")
    public void diagonallyToTheLeftFalseTest(){
        game.addToBoard(0, 5, Color.RED);
        game.addToBoard(1, 4, Color.RED);
        game.addToBoard(2, 3, Color.RED);
        Assertions.assertFalse(game.getWon());
    }

    @Test
    @DisplayName("Diagonally to the Right -- True")
    public void diagonallyToTheRightTrueTest(){
        game.addToBoard(3, 3, Color.RED);
        game.addToBoard(4, 2, Color.RED);
        game.addToBoard(6, 0, Color.RED);
        game.addToBoard(5, 1, Color.RED);
        Assertions.assertTrue(game.getWon());
    }

    @Test
    @DisplayName("Diagonally to the Right -- False")
    public void diagonallyToTheRightFalseTest(){
        game.addToBoard(3, 3, Color.RED);
        game.addToBoard(4, 2, Color.RED);
        game.addToBoard(5, 1, Color.RED);
        Assertions.assertFalse(game.getWon());
    }


}
