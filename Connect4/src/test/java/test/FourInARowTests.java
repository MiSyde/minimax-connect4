package test;

import core.*;
import gui.*;
import org.junit.jupiter.api.*;

import java.awt.*;

public class FourInARowTests {
    Game game = new Game();
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
}
