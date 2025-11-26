package gui;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

public class Board {

    static int width = 700;
    static int height = 600;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static CoinPanel coinPanel;
    private static String current;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAllGUIs();
            }
        });
    }

    private static void createAllGUIs() {
        JFrame window = new JFrame("Connect 4");
        JFrame.setDefaultLookAndFeelDecorated(true);
        URL icon = Board.class.getResource("/gui/icon.png");
        if(icon != null){
            window.setIconImage(new ImageIcon(icon).getImage());
        } else{
            System.err.println("⚠️ Icon not found: /gui/icon.png");
        }
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        JPanel startScreen = createStartScreen();
        coinPanel = createGameScreen();
        JPanel endScreen = createEndScreen();

        mainPanel.add(startScreen, "START");
        mainPanel.add(coinPanel, "GAME");
        mainPanel.add(endScreen, "END");

        window.setContentPane(mainPanel);
        window.setSize(710,635);

        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = window.getWidth()-10;
                height = window.getHeight()-35;
                if (coinPanel != null) {
                    coinPanel.recalcPos();
                    coinPanel.repaint();
                }
            }
        });
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        showStartScreen();
    }

    private static JPanel createStartScreen() {
        JPanel startPanel = new JPanel(new BorderLayout());
        startPanel.setBackground(new Color(27,60,83));

        JLabel title = new JLabel("Connect 4");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(227, 227, 227));
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        JPanel modePanel = new JPanel(new GridLayout(2, 1));
        modePanel.setBackground(new Color(27,60,83));
        modePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 100, 100));

        JButton pvp = createMenuButton("Player versus Player");
        JButton pve = createMenuButton("Player versus AI");

        pvp.addActionListener(_ -> startGame("PVP"));
        pve.addActionListener(_ -> startGame("PVE"));

        modePanel.add(pvp);
        modePanel.add(pve);

        startPanel.add(title, BorderLayout.NORTH);
        startPanel.add(modePanel, BorderLayout.CENTER);

        return startPanel;
    }

    private static CoinPanel createGameScreen() {
        Game game = new Game();

        CoinPanel coinPanel = new CoinPanel(game);
        coinPanel.addMouseListener(new BoardClickListener(coinPanel));

        return coinPanel;
    }

    private static JPanel createEndScreen() {
        JPanel endScreen = new JPanel(new BorderLayout());
        endScreen.setBackground(new Color(27,60,83));

        JLabel result = new JLabel("", JLabel.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 40));
        result.setForeground(new Color(227, 227, 227));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(27,60,83));

        JButton restartButton = new JButton("RESTART");
        JButton newGameButton = new JButton("NEW GAME");
        JButton exitButton = new JButton("EXIT");

        restartButton.addActionListener(_ -> restartGame());
        newGameButton.addActionListener(_ -> showStartScreen());
        exitButton.addActionListener(_ -> System.exit(0));

        buttonPanel.add(restartButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        endScreen.add(result, BorderLayout.CENTER);
        endScreen.add(buttonPanel, BorderLayout.SOUTH);

        endScreen.putClientProperty("results", result);

        return endScreen;
    }

    private static JButton createMenuButton(String title) {
        JButton button = new JButton(title);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBackground(new Color(35,76,106));
        button.setForeground(new Color(227, 227, 227));
        button.setFocusPainted(false);
        return button;
    }

    public static void showStartScreen() {
        cardLayout.show(mainPanel, "START");
    }

    public static void showGameScreen() {
        reset();
        cardLayout.show(mainPanel, "GAME");
        if(coinPanel != null) {
            coinPanel.requestFocusInWindow();
        }
    }

    public static void showEndScreen(String winnerMessage){
        JPanel endScreen = (JPanel) mainPanel.getComponent(2);
        JLabel result = (JLabel) endScreen.getClientProperty("results");
        if(result != null) { result.setText(winnerMessage); }
        cardLayout.show(mainPanel, "END");
    }

    private static void startGame(String gameMode) {
        current = gameMode;
        if(gameMode.equals("PVP")) {
            showGameScreen();
        } else {

        }
    }

    private static void restartGame() {
        if(current.equals("PVP")){
            showGameScreen();
        } else {

        }

    }

    private static void reset(){
        coinPanel.getCoins().clear();
        coinPanel.setTurn(0);
        coinPanel.game.restartGame();
    }

}
