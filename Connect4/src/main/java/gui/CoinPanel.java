package gui;

import core.AI;
import core.Game;
import core.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class CoinPanel extends JPanel {

    Game game;
    private final List<Coin> coins = new LinkedList<>();
    private int turn;
    private AI ai = null;

    public void addCoin(Coin coin) {
        coins.add(coin);
        ++turn;
        this.repaint();
    }

    public AI getAI() { return ai; }

    CoinPanel(Game game, boolean ai) {
        super();
        this.game = game;
        if(ai){
            this.ai = new AI(game);
        } else {
            this.ai = null;
        }

    }

    public void resetGame(){
        this.game = new Game();
        coins.clear();
        turn = 0;
        recalcPos();
        repaint();
        if(ai != null){
            ai = new AI(game);
        }
    }

    public Game getGame() { return game; }

    public void triggerAIMove() {
        if (game.getWon() || !game.isYellow(game.getCurrentPlayer()) || game.isAIThinking()) {
            return;
        }

        game.setAIThinking(true);

        try {
            int bestColumn = ai.getBestMove(game.getBoard(), 6);

            int y = game.getCurrentY(game.getBoard(), bestColumn);
            if (y != -1) {
                applyMove(bestColumn, y);
            }
        } finally {
            game.setAIThinking(false);
        }
    }

    public java.util.List<Coin> getCoins() { return new ArrayList<>(coins); }

    public void applyMove(int x, int y) {
        int cellWidth = Board.width / 7;
        int cellHeight = Board.height / 6;
        int pauseWidth = cellWidth / 10;
        int pauseHeight = cellHeight / 10;

        game.addToBoard(x, y, Color.YELLOW);
        addCoin(new Coin.YellowCoin(x * cellWidth + pauseWidth, (5 - y) * cellHeight + pauseHeight, cellHeight - pauseHeight * 2, cellWidth - pauseWidth * 2, x, y));
    }


    public int getTurn() { return turn; }

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

    public void loadFromState(GameState state) {
        this.coins.clear();
        this.coins.addAll(state.getCoins());
        this.turn = state.getTurn();
        this.game.loadPreviousGame(state);
        recalcPos();
        repaint();
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
        int w2 = w / 10; // to position the coin in the middle
        int h2 = h / 10;
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
    }
}
