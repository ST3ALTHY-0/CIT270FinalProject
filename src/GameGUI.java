package src;

import javax.swing.*;

public class GameGUI extends JFrame {// could make it not extend JFrame by declaring and initializing a JFrame in the
                                     // initializeFrame method
    protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 800;
    private JPanel mainPanel;
    static Database db; // is default so that only package classes have access
    private static final boolean isUsingLocalDB = true;

    public GameGUI() {
        ImageCache.preloadImages();
        setupDB(isUsingLocalDB);
        initializeFrame();
    }

    void setupDB(boolean isUsingLocalDB) {
        db = new Database(isUsingLocalDB);
        //new Thread(() -> db = new Database(isUsingLocalDB)).start();//might need to be careful with synchronization/race cases doing this
    }

    void setupImages() {
        new Thread(() -> ImageCache.preloadImages()).start();
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        // new GameGUI();
        // }
        // });

        // starts code by creating new GameGUI obj
        // this code is exactly the same as the above commented out code
        // this line just uses a lambda in place of an anonymous class
        SwingUtilities.invokeLater(() -> new GameGUI());

    }

    // initializes the frame and adds a blank panel
    public void initializeFrame() {
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

    // TODO: add class and method for highscore screen

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

    public void clearPanel() {
        mainPanel.removeAll();
    }

    public void updatePanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

}
