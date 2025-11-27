package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.Coin;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveSystem {
    private static final String saveDirection = "saves/";
    private static final Gson gson;
    static {
        gson = new GsonBuilder().registerTypeAdapter(Color.class, new ColorAdapter()).
                registerTypeAdapter(Coin.class, new CoinAdapter()).setPrettyPrinting().create();

        new File(saveDirection).mkdirs();
    }

    public static void saveGame(GameState gameState, String fileName) {
        try(Writer writer = new FileWriter(saveDirection + fileName + ".json")) {
            gson.toJson(gameState, writer);
        } catch(IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    public static GameState loadGame(String fileName) {
        try{
            String content = new String(Files.readAllBytes(Paths.get(saveDirection + fileName + ".json")));
            return gson.fromJson(content, GameState.class);
        } catch(IOException e) {
            System.err.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }

    public static void deleteSavedGame(String fileName) {
        File file = new File(saveDirection + fileName + ".json");
        file.delete();
    }

}
