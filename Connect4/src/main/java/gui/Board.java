package gui;

import core.Game;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

public class Board {

    static int width = 700;
    static int height = 600;

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
        panel.addMouseListener(new BoardClickListener(panel));
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

}
