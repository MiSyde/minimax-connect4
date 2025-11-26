package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardClickListener extends MouseAdapter {

    private final CoinPanel panel;

    public BoardClickListener(CoinPanel panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        int x = -1;
        int y = -1;
        int cellWidth = Board.width / 7;
        int cellHight = Board.height / 6;
        int pauseWidth = cellWidth / 10; // to position the coin in the middle
        int pauseHight = cellHight / 10;
        if(e.getX() <= cellWidth)
            x = 0;
        else if(e.getX() <= cellWidth * 2)
            x = 1;
        else if(e.getX() <=  cellWidth * 3)
            x = 2;
        else if(e.getX() <=  cellWidth * 4)
            x = 3;
        else if(e.getX() <= cellWidth * 5)
            x = 4;
        else if(e.getX() <= cellWidth * 6)
            x = 5;
        else if (e.getX() <= Board.width)
            x = 6;
        if(x != -1){
            y = panel.game.getCurrentY(x);
        }
        if(y != -1){
            if(panel.getTurn() % 2 == 0){
                panel.game.addToBoard(x, y, Color.RED);
                panel.addCoin(new Coin.RedCoin(x * cellWidth + pauseWidth, (5 - y) * cellHight + pauseHight, cellHight - pauseHight * 2, cellWidth - pauseWidth * 2, x, y));
            } else{
                panel.game.addToBoard(x, y, Color.YELLOW);
                panel.addCoin(new Coin.YellowCoin(x * cellWidth + pauseWidth, (5 - y) * cellHight + pauseHight, cellHight - pauseHight * 2, cellWidth - pauseWidth * 2, x, y));
            }
        }

    }
}
