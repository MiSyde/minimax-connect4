package gui;

import core.ChangeColorRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardClickListener extends MouseAdapter {

    private final CoinPanel panel;
    private final Board board;

    /**
     * @param panel The panel that will be assigned to this listener
     */
    public BoardClickListener(CoinPanel panel, Board board) {
        this.board = board;
        this.panel = panel;
    }

    /**
     * Handles the logic behind triggering 'game overs', adding coins, when to add them and their color
     * @see CoinPanel#applyMove(int, int)
     * @see BoardClickListener#posCalc(MouseEvent)
     * @see BoardClickListener#redAdd(int, int)
     * @see Board#endScreenDelay(int, String)
     */
    public void mouseClicked(MouseEvent e) {
        if(panel.getAI() != null){
            if(panel.game.isAIThinking()) { return; }

            if(!panel.game.isRed(panel.game.getCurrentPlayer())) { return; }

            int[] xAndY = posCalc(e);
            int x = xAndY[0];
            int y = xAndY[1];

            if(y != -1){
                redAdd(x, y);

                if (!panel.game.getWon()) {
                    panel.triggerAIMove();
                }
            }
        } else {
            int[] xAndY = posCalc(e);
            int x = xAndY[0];
            int y = xAndY[1];

            if(y != -1){
                if(panel.getTurn() % 2 == 0){
                    redAdd(x, y);
                } else{
                    panel.applyMove(x, y);
                }
            }
        }
        if(!panel.game.getWon() && panel.game.isBoardFull(panel.game.getBoard())) { board.endScreenDelay(2500, "Draw!"); }
        if(panel.game.getWon()){
            Color winningColor = (panel.getTurn() % 2 == 0) ? Color.YELLOW : Color.RED;
            ArrayList<Coin> winningCoins = new ArrayList<>();
            ArrayList<int[]> winningCoinCords = panel.game.getConsecutiveTiles();
            for(Coin c : panel.getCoins()) {
                if(!c.getColor().equals(winningColor)) continue;
                int[] cords = new int[] {c.getColumn(), c.getRow()};
                for(int[] i : winningCoinCords) {
                    if(i[0] == cords[0] && i[1] == cords[1]) winningCoins.add(c);
                }
                if(winningCoinCords.contains(cords)) winningCoins.add(c);
            }
            ChangeColorRunnable changeColor = new ChangeColorRunnable(winningCoins, Color.PINK, panel);
            ChangeColorRunnable changeBack = new ChangeColorRunnable(winningCoins, winningColor, panel);
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            AtomicInteger count = new AtomicInteger(0);
            ScheduledFuture<?>[] blink = new ScheduledFuture[1];
            blink[0] = executorService.scheduleAtFixedRate(() -> {
                if(count.getAndIncrement() % 2 == 0) {
                    changeColor.run();
                } else {
                    changeBack.run();
                }
                if(count.get() >= 5) {
                    blink[0].cancel(false);
                }
            }, 250, 500, TimeUnit.MILLISECONDS);

            String winner = (winningColor == Color.YELLOW) ? "Yellow" : "Red";
            board.endScreenDelay(3000, winner + " won!");
        }
    }

    /**
     * Calculates the column where the player clicked and the available row in that column
     * @return the column and row of the new coin that's about to the placed
     * @see core.Game#getCurrentY(Color[][], int)
     * @see MouseEvent#getX()
     */
    private int[] posCalc(MouseEvent e){
        int x = -1;
        int y = -1;
        int cellWidth = Board.width / 7;

        if(e.getX() <= cellWidth) x = 0;
        else if(e.getX() <= cellWidth * 2) x = 1;
        else if(e.getX() <= cellWidth * 3) x = 2;
        else if(e.getX() <= cellWidth * 4) x = 3;
        else if(e.getX() <= cellWidth * 5) x = 4;
        else if(e.getX() <= cellWidth * 6) x = 5;
        else if (e.getX() <= Board.width) x = 6;

        if(x != -1){
            y = panel.game.getCurrentY(panel.game.getBoard(), x);
        }
        return new int[]{x, y};
    }

    /**
     * Puts a red coin on the board at the given coordinates
     * @param x The column of the coin
     * @param y The row of the coin
     * @see core.Game#addToBoard(int, int, Color) 
     * @see CoinPanel#addCoin(Coin)  
     */
    private void redAdd(int x, int y){
        int cellWidth = Board.width / 7;
        int cellHeight = Board.height / 6;
        int pauseWidth = cellWidth / 10;
        int pauseHeight = cellHeight / 10;

        panel.game.addToBoard(x, y, Color.RED);
        panel.addCoin(new Coin.RedCoin(x * cellWidth + pauseWidth, (5 - y) * cellHeight + pauseHeight, cellHeight - pauseHeight * 2, cellWidth - pauseWidth * 2, x, y));
    }

}
