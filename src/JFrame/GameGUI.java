package src.JFrame;


import javax.swing.*;

import src.JPanels.*;
import src.DTOs.Score;


public class GameGUI extends JFrame {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 800;
    private JPanel mainPanel;

    public GameGUI() {
        //flip = gl.getGameGUI(); //gets the instance of this class created in GameLauncher
        initializeFrame();
    }

    // initializes the frame and adds a blank panel
    private void initializeFrame() {
        // create and add panel 
        mainPanel = new JPanel();
        add(mainPanel);

        // set frame properties
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        toGameMenu(this);
    }


    //Following methods are used to change the JPanel shown on Frame
    public void toGameMenu(GameGUI flip) {
        clearPanel();

        GameMenu gameMenuScreen = new GameMenu(flip);
        mainPanel.add(gameMenuScreen);
        updatePanel(mainPanel);
    }

    public void toEasyGame(GameGUI flip) {
        clearPanel();
        EasyGame egScreen = new EasyGame(flip, 4, 4);// to make this better there should be an exception thrown if eg does not
                                               // make 16 cards
        mainPanel.add(egScreen);
        updatePanel(mainPanel);
    }

    public void toMediumGame(GameGUI flip) {
        clearPanel();
        MediumGame mgScreen = new MediumGame(flip, 5, 4);
        mainPanel.add(mgScreen);
        updatePanel(mainPanel);
    }

    public void toHardGame(GameGUI flip) {
        clearPanel();
        HardGame hgScreen = new HardGame(flip, 4, 6);
        mainPanel.add(hgScreen);
        updatePanel(mainPanel);
    }

    public void toEndGame(GameGUI flip, Score score) {
        clearPanel();
        EndGame endGameScreen = new EndGame(flip, score);
        mainPanel.add(endGameScreen);
        updatePanel(mainPanel);
    }

    public void toHighScore(GameGUI flip, Score score) {
        clearPanel();
        HighScore highScoreScreen = new HighScore(flip, score);
        mainPanel.add(highScoreScreen);
        updatePanel(mainPanel);
    }

    private void clearPanel() {
        mainPanel.removeAll();
    }

    private void updatePanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

}
