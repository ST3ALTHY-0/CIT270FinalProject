package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private GameGUI flip;
    private Image backgroundImage;
    private ImageIcon backgroundIcon;
    private IntroScreen intro;
    private ButtonBox buttonBox;

    public GameMenu(GameGUI flip) {
        this.flip = flip;

        backgroundIcon = ImageCache.getImageIcon(ImageCache.getImageFile("Other", "trees", "jpg"));
        backgroundImage = backgroundIcon.getImage();

        initializeGameMenuPanel();
        initButtons();
        buttonBox = new ButtonBox();
        intro = new IntroScreen();
        intro.initializeIntroScreen();
        buttonBox.initializeButtonBox();

        toInfoPanel();
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
        add(buttonBox);
        buttonBox.setVisible(true);
        revalidate();
        repaint();
    }

    // add buttons to panel
    private void initializeGameMenuPanel() {
        setPreferredSize(new Dimension(GameGUI.DEFAULT_WIDTH, GameGUI.DEFAULT_HEIGHT));
        setBounds(0, 0, GameGUI.DEFAULT_WIDTH, GameGUI.DEFAULT_HEIGHT);
        setLayout(new GridBagLayout()); // Use FlowLayout to respect preferred size

        GridBagConstraints gbc = new GridBagConstraints();//looked up the next few lines of code to center an element
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Optional: Add padding
    }

    protected void initButtons() {
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");

        easyButton.addActionListener(e -> flip.toEasyGame(flip));
        mediumButton.addActionListener(e -> flip.toMediumGame(flip));
        hardButton.addActionListener(e -> flip.toHardGame(flip));

        easyButton.setBackground(Color.GREEN);
        mediumButton.setBackground(Color.YELLOW);
        hardButton.setBackground(Color.RED);
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    class ButtonBox extends JPanel {

        public void initializeButtonBox() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(Box.createHorizontalGlue());
            add(easyButton);
            // add(Box.createVerticalStrut(1));
            add(Box.createHorizontalStrut(10));
            add(mediumButton);
            add(Box.createHorizontalStrut(10));
            // add(Box.createVerticalStrut(1));
            add(hardButton);
            add(Box.createHorizontalGlue());

            // add(Box.createVerticalGlue());

            setAlignmentX(Component.CENTER_ALIGNMENT);
            setPreferredSize(new Dimension(400, 200));
            // setSize(200, 200);
            setBackground(new Color(0, 0, 0, 128));
            setOpaque(false); // Make the menuBox transparent
        }
    }

    class IntroScreen extends JPanel {
        private JButton okButton;
        private JLabel info;

        public void initializeIntroScreen() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            setPreferredSize(new Dimension(400, 200));
            setLayout(new BorderLayout());

            okButton = new JButton("OK");
            okButton.addActionListener(e -> toButtonBox());
            info = new JLabel("Intro Screen: Give some info about the game here");
            add(info);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            buttonPanel.add(okButton);

            add(buttonPanel, BorderLayout.SOUTH);

        }

    }
}
