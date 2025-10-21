package core;

import gui.CoinPanel;

public class Turn {

    static public int turn;

    public Turn(CoinPanel panel)
    {
        turn = panel.getTurnkimentes();
    }
}
