package finalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

    public class GameGUI extends JFrame{
    protected JPanel mainPanel;
    protected static final int DEFAULT_WIDTH = 600;
    protected static final int DEFAULT_HEIGHT = 600;
    protected EasyGame easyGame;
    protected GameMenu gameMenu;
    protected JFrame mainFrame;

    public GameGUI(){
        //initialize frame
        initializeFrame();
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
        toGameMenu(this);
}

    public void toGameMenu(GameGUI flip){
        mainPanel.add(new GameMenu(flip));
    }
    public void toEasyGame(GameGUI flip){
       // exitPanel();
        mainPanel.add(new EasyGame(flip));
        updatePanel(mainPanel);
    }
    public void toMediumGame(GameGUI flip){
        mainFrame.add(new MediumGame(flip), BorderLayout.CENTER);
    }
    public void toHardGame(GameGUI flip){
        mainFrame.add(new HardGame(flip), BorderLayout.CENTER);
    }
    public void exitPanel(){
        mainFrame.removeAll();
    }
    public void updatePanel(JPanel panel){
        panel.revalidate(); // Refresh the main panel
        panel.repaint();
    }
}