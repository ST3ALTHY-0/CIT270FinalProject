package src;

import javax.swing.*;
import java.awt.*;

public class EndGame extends JPanel implements CenterButtonPanel {
    private JButton menu;
    private JButton addHighScore;
    private JButton exit;
    private GameGUI flip;
    private Score score;

    public EndGame(GameGUI flip, Score score) {
        this.flip = flip;
        this.score = score;
        initializeGridBagLayout(this);
        initComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        add(Box.createVerticalGlue());
        initializeCenteredPanel(this, menu, addHighScore, exit); // Add buttons
        add(Box.createVerticalGlue());
    }

    @Override
    public void initComponents() {
        menu = new JButton("To Menu");
        addHighScore = new JButton("Add your score to high score leader board.");
        exit = new JButton("Exit");

        menu.addActionListener(e -> flip.toGameMenu(flip));
        addHighScore.addActionListener(e -> flip.toHighScore(flip, score));
        exit.addActionListener(e -> System.exit(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);

    }
}