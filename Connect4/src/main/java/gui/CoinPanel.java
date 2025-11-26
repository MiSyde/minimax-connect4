package gui;

import core.Game;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class CoinPanel extends JPanel {

    Game game;
    private final List<Coin> coins = new LinkedList<>();
    private int turn; // <- !! can't stay 0 if we use a loaded game
    public void addCoin(Coin coin) {
        coins.add(coin);
        ++turn;
        this.repaint();
    }

    CoinPanel(Game game) {
        super();
        this.game = game;
    }

    public int getTurn() { return turn; }

    public void setTurn(int turn) { this.turn = turn; }

    public List<Coin> getCoins() { return coins; }

    private void boardLineSetup(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color linecolor = new Color(96, 95, 95);
        g.setColor(linecolor);
        int w = Board.width / 7;
        int h = Board.height / 6;
        // Vertical
        for(int i = 1; i <= 7; ++ i){
            g.drawLine(i*w, 0, i*w, Board.height);
        }
        // Horizontal
        for(int i = 1; i <= 6; ++ i) {
            g.drawLine(0, h * i, Board.width, h * i);
        }
    }

    public void recalcPos(){
        int cellWidth = Board.width / 7;
        int cellHight = Board.height / 6;
        int pauseWidth = cellWidth / 10;
        int pauseHight = cellHight / 10;

        for(Coin coin : coins){
            coin.setNewData(cellWidth, cellHight, pauseWidth, pauseHight);
        }
    }

    private void boardCircleSetup(Graphics g) {
        int w = Board.width / 7;
        int h = Board.height / 6;
        int w2 = w/10; // to position the coin in the middle
        int h2 = h/10;
        Color circles = new Color(92, 92, 92);
        g.setColor(circles);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(int i = 0; i<7; i++){
            for(int j = 0; j<6; j++){
                g.drawOval(i*w+w2, j*h+h2, w-w2*2, h-h2*2);
                g.fillOval(i*w+w2, j*h+h2, w-w2*2, h-h2*2);
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color bg = new Color(142, 142, 142);
        setBackground(bg);
        boardCircleSetup(g);
        boardLineSetup(g);
        for(Coin coin : coins){
            coin.draw(g);
        }
        if(game.getWon()){

        }
    }
}
