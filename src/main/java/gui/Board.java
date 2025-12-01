package gui;

import core.Game;
import core.GameState;
import core.SaveSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;

public class Board {

    static int width = 700;
    static int height = 600;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static CoinPanel currentGamePanel;
    private static String current;
    private static JPanel endScreen;
    private static final ArrayList<String> thisSession = new ArrayList<>();

    public static void main(String[] args) { SwingUtilities.invokeLater(Board::createAllGUIs); }

    /**
     * Creates the main frame, and it's panel with a cardlayout.
     * It adds all the windows to said cardlayout, so the program can switch between them later on.
     * Sets some of the frame's default settings, it's starting size, starting position, etc.
     * The program needs to know when the frame gets resized (for relative sizing and positioning) / closed (for saving),
     * therefore these listeners also get added to the frame.
     * @see Board#createStartScreen()
     * @see Board#createEndScreen()
     * @see Board#createGamePanel(String)
     * @see Board#saveProgress()
     * @see Board#showStartScreen()
     */
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
        endScreen = createEndScreen();

        currentGamePanel = createGamePanel("");

        mainPanel.add(startScreen, "START");
        mainPanel.add(currentGamePanel, "GAME");
        mainPanel.add(endScreen, "END");
        mainPanel.putClientProperty("window", window);

        window.setContentPane(mainPanel);
        window.setSize(710,635);

        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                width = window.getWidth()-10;
                height = window.getHeight()-35;
                if (currentGamePanel != null) {
                    currentGamePanel.recalcPos();
                    currentGamePanel.repaint();
                }
            }
        });

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(!thisSession.isEmpty()){
                    saveProgress();
                }
            }

            public void windowClosed(WindowEvent e) {
                windowClosing(e);
            }
        });

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        showStartScreen();
    }

    /**
     * If the hasn't ended but the program still exited, the function saves the board's current state and remove the
     * current mode's name from a list, which keeps track of the gamemodes that have been played this session, so that way
     * it won't delete that gamemode's savefile.
     * @see SaveSystem#saveGame(GameState, String)
     * @see SaveSystem#deleteSavedGame(String)
     */
    private static void saveProgress() {
        if(!currentGamePanel.game.getWon() && !currentGamePanel.game.isBoardFull(currentGamePanel.game.getBoard())){
            GameState state = new GameState(currentGamePanel.getGame(), current, currentGamePanel.getTurn(), currentGamePanel.getCoins());
            SaveSystem.saveGame(state, current);
            thisSession.remove(current);
        }
        else{
            SaveSystem.deleteSavedGame(current);
        }
        for(String mode : thisSession){
            if(!mode.equals(current)){
                SaveSystem.deleteSavedGame(mode);
            }
        }
        thisSession.clear();
    }

    /**
     * If the saves directory contains a savefile for the given gamemode, it gets loaded.
     * @param filename the name of the gamemode - PVE or PVP
     * @see SaveSystem#loadGame(String)
     */
    private static GameState loadProgress(String filename){
        try{
            return SaveSystem.loadGame(filename);
        } catch(Exception e){
            System.err.println("Didn't find previous progress for: " + filename + " mode");
        }
        return null;
    }

    /**
     * Creates the screen where the user chooses which mode to play
     * The screen - startPanel - gets assigned a title and another panel - modePanel
     * The latter panel gets assigned buttons, and those buttons get actionlisteners
     * @see Board#createMenuButton(String)
     */
    private static JPanel createStartScreen() {
        JPanel startPanel = new JPanel(new BorderLayout());
        startPanel.setBackground(new Color(27,60,83));

        JLabel title = new JLabel("Connect 4");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(227, 227, 227));
        title.setBorder(BorderFactory.createEmptyBorder(50, 240, 50, 240));

        JPanel modePanel = new JPanel(new GridLayout(2, 1, 10, 20));
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

    /**
     * The screen where the game will be played gets created
     * The game panel gets the <code>BoardClickListener</code> that has been created in a seperate class
     * @param mode The selected gamemode
     */
    private static CoinPanel createGamePanel(String mode) {
        Game game = new Game();
        boolean ai = "PVE".equals(mode);
        CoinPanel panel = new CoinPanel(game, ai);
        panel.addMouseListener(new BoardClickListener(panel));
        return panel;
    }

    /**
     * Creates the screen that shows when the game ends
     * The panel gets buttons with actionlisteners added to it
     * The panel also has a panel, which receives a property so it can be manipulated later
     */
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
        exitButton.addActionListener(_ -> {
            JFrame window = (JFrame) mainPanel.getClientProperty("window");
            if(window != null) { window.dispose(); }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        endScreen.add(result, BorderLayout.CENTER);
        endScreen.add(buttonPanel, BorderLayout.SOUTH);

        endScreen.putClientProperty("results", result);

        return endScreen;
    }

    /**
     * Generic button creator for the gamemode selecting screen
     * @param title The button will be assigned this title
     */
    private static JButton createMenuButton(String title) {
        JButton button = new JButton(title);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBackground(new Color(35,76,106));
        button.setForeground(new Color(227, 227, 227));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Changes the frame's screen to the gamemode selection screen
     */
    public static void showStartScreen() {
        cardLayout.show(mainPanel, "START");
    }

    /**
     * Changes the frame's screen to the game's screen
     */
    public static void showGameScreen() {
        cardLayout.show(mainPanel, "GAME");
        if(currentGamePanel != null) {
            currentGamePanel.requestFocusInWindow();
        }
    }

    /**
     * @param time The delay which after the ending screen will be shown
     * @param winnerMessage The message that's going to be displayed on the ending screen
     */
    public static void endScreenDelay(int time, String winnerMessage){
        Timer timer = new Timer(time, _ -> showEndScreen(winnerMessage));
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Changes the main frame's screen to the ending screen
     * @param winnerMessage The message that's going to be displayed
     */
    public static void showEndScreen(String winnerMessage){
        JLabel result = (JLabel) endScreen.getClientProperty("results");
        if(result != null) { result.setText(winnerMessage); }
        cardLayout.show(mainPanel, "END");
    }

    /**
     * Starts up game screen with the selected gamemode
     * If the current session's list doesn't contain the selected gamemode, it gets added to the list
     * If there is a savefile in the saves directory to the selected gamemode, it gets loaded instead of an empty board
     * @param gameMode The selected gamemode - PVP/PVE
     * @see Board#loadProgress(String)
     * @see Board#createGamePanel(String)
     * @see Board#showGameScreen()
     * @see CoinPanel#loadFromState(GameState)
     */
    private static void startGame(String gameMode) {
        if(!thisSession.contains(gameMode)) {
            thisSession.add(gameMode);
        }

        current = gameMode;
        mainPanel.remove(currentGamePanel);
        currentGamePanel = createGamePanel(gameMode);
        mainPanel.add(currentGamePanel, "GAME");
        currentGamePanel.resetGame();
        GameState loadedState = loadProgress(gameMode);
        if(loadedState != null){
            currentGamePanel.loadFromState(loadedState);
        }
        showGameScreen();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Triggers the game starting function, with the last selected gamemode
     * @see Board#startGame(String)
     */
    private static void restartGame() {
        startGame(current);
    }
}
