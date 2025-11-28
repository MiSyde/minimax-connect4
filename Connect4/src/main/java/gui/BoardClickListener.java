package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardClickListener extends MouseAdapter {

    private final CoinPanel panel;

    public BoardClickListener(CoinPanel panel) {
        this.panel = panel;
    }

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
        if(!panel.game.getWon() && panel.game.isBoardFull(panel.game.getBoard())) { Board.endScreenDelay("Draw!"); }
        if(panel.game.getWon()){

            String winner = (panel.getTurn() % 2 == 0) ? "Yellow" : "Red";
            Board.endScreenDelay(winner + " won!");
        }
    }

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

    private void redAdd(int x, int y){
        int cellWidth = Board.width / 7;
        int cellHeight = Board.height / 6;
        int pauseWidth = cellWidth / 10;
        int pauseHeight = cellHeight / 10;

        panel.game.addToBoard(x, y, Color.RED);
        panel.addCoin(new Coin.RedCoin(x * cellWidth + pauseWidth, (5 - y) * cellHeight + pauseHeight, cellHeight - pauseHeight * 2, cellWidth - pauseWidth * 2, x, y));
    }

}
