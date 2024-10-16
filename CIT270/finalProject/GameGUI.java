package finalProject;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

//TODO: right now in many of the classes I have just set the fields and methods to protected,
//TODO: but many of these should be changed to private or public as needed. [abstraction is an important part of java]

//TODO: I also like the idea of making a highscore board, can maybe get practice implimenting an API that keeps track
//TODO: of all the scores gotten by anyone who has played the game and maybe display like the top 10 somewhere in the game 

//TODO: Add some sounds, when you flip over matching pair, non matching pair, you finish the game, etc

public class GameGUI extends JFrame {// could make it not extend JFrame by declaring and initializing a JFrame in the
                                     // initializeFrame()
    protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 800;
    protected EasyGame easyGame;
    protected GameMenu gameMenu;
    protected JPanel mainPanel;
    protected static ArrayList<String> imagePathArray;

    public GameGUI() {
        addFileNamesToArrayList();
        initializeFrame();
        ImageCache.preloadImages(imagePathArray);
    }

    public static void main(String[] args) {
        // this way of starting the game is somewhat unnecessary as it is more for
        // helping create multiple GameGUI's if you want to multithread this
        // application, however rn it is basically just creates a new GameGUI object
        // which kickstarts the rest of the code
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI();
            }
        });
    }

    // this method adds a list of sprite file names at an ArrayList
    // TODO: add more sprites to CardSprite file and add their file names here
    // TODO: if you want to get fancy iterate over all the files in CardSprites and
    // put them in ArrayList
    // could put this and getImagePathArrayElement in a different class like
    // ImageCache
    private void addFileNamesToArrayList() {
        imagePathArray = new ArrayList<>();
        imagePathArray.add("\\CardSprites\\star.png");
        imagePathArray.add("\\CardSprites\\apple.jpg");
        imagePathArray.add("\\CardSprites\\duck.jpg");
        imagePathArray.add("\\CardSprites\\frog.png");
        imagePathArray.add("\\CardSprites\\heart.jpg");
        imagePathArray.add("\\CardSprites\\musicNote.png");
        imagePathArray.add("\\CardSprites\\smiley.png");
        imagePathArray.add("\\CardSprites\\turtle.jpg");

    }

    // returns fileName of a sprite at specified index
    public String getImagePathArrayElement(int index) {
        return imagePathArray.get(index);
    }

    // initializes the frame and adds a blank panel
    public void initializeFrame() {
        // create panel
        mainPanel = new JPanel();
        add(mainPanel);

        // set frame properties
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        toGameMenu(this);
    }

    // TODO: add class and method for highscore screen(will also need a server class
    // to run on a local machine, and some type of Database class that uses a API to
    // communicate with that server)

    // TODO: add class and method for end game screen

    public void toGameMenu(GameGUI flip) {
        mainPanel.add(new GameMenu(flip));
        updatePanel(mainPanel);
    }

    public void toEasyGame(GameGUI flip) {
        mainPanel.add(new EasyGame(flip, 4, 4));
        updatePanel(mainPanel);
    }

    public void toMediumGame(GameGUI flip) {
        mainPanel.add(new MediumGame(flip), BorderLayout.CENTER);
        updatePanel(mainPanel);
    }

    public void toHardGame(GameGUI flip) {
        mainPanel.add(new HardGame(flip), BorderLayout.CENTER);
        updatePanel(mainPanel);
    }

    public void clearPanel() {
        mainPanel.removeAll();
    }

    public void updatePanel(JPanel panel) {
        panel.revalidate(); // Refresh the main panel
        panel.repaint();
    }

}