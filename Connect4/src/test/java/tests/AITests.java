package tests;

import core.Game;
import org.junit.jupiter.api.*;
import core.AI;
import java.awt.*;

public class AITests {
    @Test
    @DisplayName("AI finds immediate winning move")
    public void aiFindsWinningMoveTest() {
        Game game = new Game();
        AI ai = new AI(game);

        game.addToBoard(0, 0, Color.YELLOW);
        game.addToBoard(0, 1, Color.YELLOW);
        game.addToBoard(0, 2, Color.YELLOW);


        int bestMove = ai.getBestMove(game.getBoard(), 3);
        Assertions.assertEquals(0, bestMove);
    }

    @Test
    @DisplayName("AI blocks opponent's immediate win")
    public void aiBlocksOpponentWinTest() {
        Game game = new Game();
        AI ai = new AI(game);

        game.addToBoard(0, 0, Color.RED);
        game.addToBoard(0, 1, Color.RED);
        game.addToBoard(0, 2, Color.RED);


        int bestMove = ai.getBestMove(game.getBoard(), 3);
        Assertions.assertEquals(0, bestMove);
    }
}
