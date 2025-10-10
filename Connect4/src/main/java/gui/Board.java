package gui;

import javax.swing.JFrame;
import java.awt.*;

public class Board {

    public static void main(String[] args) {
        JFrame window = new JFrame("Board");
        CoinPanel panel = new CoinPanel();
        window.setContentPane(panel);
        panel.addMouseListener(new BoardClickListener(panel));
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(false);
        window.setBackground(Color.gray);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }

}
