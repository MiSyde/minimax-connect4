package gui;

import core.Game;

import javax.swing.*;
import java.awt.*;

public class Board {

    public static void main(String[] args) {
        JFrame window = new JFrame("Connect 4");
        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setIconImage(new ImageIcon("C:\\Users\\batta\\Java\\Connect4\\icon.png").getImage());
        CoinPanel panel = new CoinPanel();
        window.setContentPane(panel);
        Game a = new Game();
        panel.addMouseListener(new BoardClickListener(panel, a));
        window.setSize(700,600);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
