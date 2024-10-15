package finalProject;

import java.awt.*;


import javax.swing.*;

    public class GameGUI extends JFrame{
    protected JPanel mainPanel;
    protected static final int DEFAULT_WIDTH = 800;
    protected static final int DEFAULT_HEIGHT = 800;
    protected EasyGame easyGame;
    protected GameMenu gameMenu;
    protected JFrame mainFrame;
    protected String[] imagePathArray = {"\\CardSprites\\star.jpg"};

    public GameGUI(){
        //initialize frame
        initializeFrame();
        ImageCache.preloadImages(imagePathArray);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI();
            }
        });
    }

    public void initializeFrame(){
        //create panel and frame
        mainFrame = new JFrame();
        mainPanel = new JPanel();

        mainFrame.add(mainPanel);
        // set frame properties
        mainFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        toGameMenu(this);
}

    public void toGameMenu(GameGUI flip){
        mainPanel.add(new GameMenu(flip));
    }
    public void toEasyGame(GameGUI flip){
       // exitPanel();
        mainPanel.add(new EasyGame(flip, 4, 4));
        updatePanel(mainPanel);
    }
    public void toMediumGame(GameGUI flip){
        mainFrame.add(new MediumGame(flip), BorderLayout.CENTER);
    }
    public void toHardGame(GameGUI flip){
        mainFrame.add(new HardGame(flip), BorderLayout.CENTER);
    }
    public void clearPanel(){
        mainPanel.removeAll();
    }
    public void updatePanel(JPanel panel){
        panel.revalidate(); // Refresh the main panel
        panel.repaint();
    }
}