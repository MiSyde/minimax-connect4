package tests;

import core.*;
import gui.CoinPanel;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.File;

public class LoadSaveTests {
    CoinPanel coinPanel;

    @BeforeEach
    public void setupTests(){
        Game game = new Game();
        coinPanel = new CoinPanel(game, false);
    }

    @Test
    @DisplayName("GameState correctly stores game data")
    public void gameStateStoresDataTest() {
        coinPanel.getGame().addToBoard(0, 0, Color.RED);
        coinPanel.getGame().addToBoard(1, 0, Color.YELLOW);

        GameState state = new GameState(coinPanel.getGame(), "PVP", 2, coinPanel.getCoins());

        Assertions.assertEquals("PVP", state.getGameMode());
        Assertions.assertEquals(2, state.getTurn());
        Assertions.assertFalse(state.isGameWon());
    }

    @Test
    @DisplayName("Saving and loading")
    public void saveLoadConsistencyTest() {
        coinPanel.getGame().addToBoard(0, 0, Color.RED);
        coinPanel.getGame().addToBoard(1, 0, Color.YELLOW);
        GameState originalState = new GameState(coinPanel.getGame(), "PVP", 2, coinPanel.getCoins());

        SaveSystem.saveGame(originalState, "test_save");

        File saveFile = new File("saves/test_save.json");

        GameState loadedState = SaveSystem.loadGame("test_save");

        Assertions.assertNotNull(loadedState);
        Assertions.assertEquals(originalState.getGameMode(), loadedState.getGameMode());
        Assertions.assertEquals(originalState.getTurn(), loadedState.getTurn());

       SaveSystem.deleteSavedGame("test_save");
    }

    @Test
    @DisplayName("Colors are serialized successfully")
    public void colorSerializationTest() {
        GameState redState = new GameState(coinPanel.getGame(), "PVP", 0, coinPanel.getCoins());
        Assertions.assertEquals(Color.RED.getRGB(), redState.getCurrentPlayer().getRGB());

        coinPanel.getGame().switchPlayer();

        GameState yellowState = new GameState(coinPanel.getGame(), "PVP", 1, coinPanel.getCoins());
        Assertions.assertEquals(Color.YELLOW.getRGB(), yellowState.getCurrentPlayer().getRGB());
    }
}
