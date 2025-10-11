package gui;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Board {

    public static void main(String[] args) {
        JFrame window = new JFrame("Connect 4");
        JFrame.setDefaultLookAndFeelDecorated(true);
        URL icon = Board.class.getResource("/gui/icon.png");
        if(icon != null){
            window.setIconImage(new ImageIcon(icon).getImage());
        } else{
            System.err.println("⚠️ Icon not found: /gui/icon.png");
        }
        CoinPanel panel = new CoinPanel();
        window.setContentPane(panel);
        Game a = new Game();
        panel.addMouseListener(new BoardClickListener(panel, a));
        window.setSize(717,640);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
