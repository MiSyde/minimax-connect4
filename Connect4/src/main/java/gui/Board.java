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
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static CoinPanel coinPanel;

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

    /*
    public static void main(String[] args) {
        JFrame window = new JFrame("Connect 4");
        JFrame.setDefaultLookAndFeelDecorated(true);
        URL icon = Board.class.getResource("/gui/icon.png");
        if(icon != null){
            window.setIconImage(new ImageIcon(icon).getImage());
        } else{
            System.err.println("⚠️ Icon not found: /gui/icon.png");
        }
        CoinPanel panel = new CoinPanel(new Game());
        window.setContentPane(panel);
        panel.addMouseListener(new BoardClickListener(panel, window));
        window.setSize(710,635);
        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = window.getWidth()-10;
                height = window.getHeight()-35;
                panel.recalcPos();
                panel.repaint();
            }
        });
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
*/
}
