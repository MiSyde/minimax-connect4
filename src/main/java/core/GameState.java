package core;

import gui.Coin;

import java.awt.*;
import java.util.ArrayList;

public class GameState {
    private Color[][] board;
    private Color currentPlayer;
    private boolean gameWon;
    private String gameMode;
    private int turn;
    private java.util.List<Coin> coins;

    public Color[][] getBoard() { return board; }

    public void setBoard(Color[][] board) { this.board = board; }

    public Color getCurrentPlayer() { return currentPlayer; }

    public void setCurrentPlayer(Color currentPlayer) { this.currentPlayer = currentPlayer; }

    public boolean isGameWon() { return gameWon; }

    public void setGameWon(boolean gameWon) { this.gameWon = gameWon; }

    public String getGameMode() { return gameMode; }

    public void setGameMode(String gameMode) { this.gameMode = gameMode; }

    public int getTurn() { return turn; }

    public void setTurn(int turn) { this.turn = turn; }

    public GameState(Game game, String gameMode, int turn, java.util.List<Coin> coins) {
        this.coins = new ArrayList<>(coins);
        board = copyBoard(game.getBoard());
        currentPlayer = game.getCurrentPlayer();
        gameWon = game.getWon();
        this.gameMode = gameMode;
        this.turn = turn;
    }

    public java.util.List<Coin> getCoins() { return coins; }

    private Color[][] copyBoard(Color[][] board) {
        Color[][] newBoard = new Color[7][6];
        for (int i = 0; i < 7; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 6);
        }
        return newBoard;
    }
}
