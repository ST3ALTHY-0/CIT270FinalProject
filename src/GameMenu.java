package src;

import java.io.File;
import java.awt.*;
import javax.swing.*;

public class GameMenu extends JPanel implements CenterButtonPanel {
   
    private static final int INTRO_PANEL_WIDTH = 500;
    private static final int INTRO_PANEL_HEIGHT = 200;

    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private GameGUI flip;
    private IntroScreen intro;
    private ButtonBox buttonBoxPanel;
    private JButton infoButton;


    public GameMenu(GameGUI flip) {
        this.flip = flip;
        setUpAndAddComponents();
        if (RunChecker.createCheckFile()) {
            toInfoPanel();
        } else {
            toButtonBox();
        }
    }

    private void setUpAndAddComponents(){
        initializeGridBagLayout(this);
        initComponents();
        buttonBoxPanel = new ButtonBox();
        addComponents();
        intro = new IntroScreen();
    }

    public GameMenu getGameMenu() {
        return this;
    }

    private void toInfoPanel() {
        removeAll();
        add(intro);
        revalidate();
        repaint();
    }

    private void toButtonBox() {
        removeAll();
        add(buttonBoxPanel);
        buttonBoxPanel.setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void initComponents() {
        //could make the text, or really any button properties like color, a constant and have them defined at the top of the file
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        infoButton = new JButton("See Game Info");

        easyButton.addActionListener(e -> flip.toEasyGame(flip));
        mediumButton.addActionListener(e -> flip.toMediumGame(flip));
        hardButton.addActionListener(e -> flip.toHardGame(flip));
        infoButton.addActionListener(e -> toInfoPanel());

        easyButton.setBackground(Color.GREEN);
        mediumButton.setBackground(Color.YELLOW);
        hardButton.setBackground(Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);
        
    }

    @Override
    public void addComponents() {
        buttonBoxPanel.addButtons();
    }

    class ButtonBox extends JPanel {
        public void addButtons(){
            initializeCenteredPanel(this, easyButton, mediumButton, hardButton);
            add(Box.createVerticalGlue());
            add(Box.createVerticalStrut(20));
            add(infoButton);
            infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            setBackground(new Color(0, 0, 0, 128));
            setOpaque(false);
        }
    }

    class IntroScreen extends JPanel {
        private JButton okButton;
        private JLabel info;

        public IntroScreen() {
            initializeIntroScreen();
        }

        public void initializeIntroScreen() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            setPreferredSize(new Dimension(INTRO_PANEL_WIDTH, INTRO_PANEL_HEIGHT));
            setLayout(new BorderLayout());

            okButton = new JButton("OK");
            okButton.addActionListener(e -> toButtonBox());
            info = new JLabel();
            info.setText("<html><body style='width:" + (INTRO_PANEL_WIDTH-125) +"px; text-align: center;'>Intro Screen: This is a memory match game. All you need to do is match the cards with the same picture. However, the higher the difficulty, the shorter the amount of time you can see the face-up cards, but you will also earn more score. There will also be an additional type of card added in the medium and hard difficulties. This type of card is a bombCard that has a picture of a bomb. If you match two bombCards, your game will end and you will earn a score of 0.</body></html>");
            add(info);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            buttonPanel.add(okButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    static class RunChecker {
        private static final File CHECK_FILE = new File("./private/check.txt");
        public static boolean createCheckFile() {
            try {
                return CHECK_FILE.createNewFile();
            } catch (Exception e) {
                //e.printStackTrace();
                return false;
            }
        }
    }
}
