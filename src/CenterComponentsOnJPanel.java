package src;

import java.awt.*;
import javax.swing.*;

/*
 * CenterButtonPanel is an interface for centering some amount of buttons on a given panel
 * the background image is a image used by the classes that implement this class to change the background.
 * (could not put the method to change the background in this interface because it has to be overridden by
 *  the specific subclass that wants to change the background)
 */
public interface CenterComponentsOnJPanel {

    ImageIcon BACKGROUND_ICON = ImageCache.getImageIcon(ImageCache.getImageFile("Other", "trees", "jpg"));
    Image BACKGROUND_IMAGE = BACKGROUND_ICON.getImage();

    // adds buttons/components to the center of a BoxLayout Panel and makes the panel
    // transparent and then returns the panel. TODO: Maybe change JButton to Component
    default JPanel initializeCenteredPanel(JPanel panel, JComponent... buttons) {// JButton... allows for any amount of
                                                                            // JButtons to be passed through as args
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));// X_axis makes the buttons be centered left
                                                                            // to right rather than up to down
        buttonPanel.add(Box.createHorizontalGlue());

        for (JComponent button : buttons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createHorizontalStrut(10));

            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.setBackground(new Color(0, 0, 0, 255));
        buttonPanel.setOpaque(false);

        panel.add(buttonPanel);
        return panel;
    }

    // Im not 100% sure why this was needed to center elements on a panel but it is,
    // and deleting it messes things up
    default void initializeGridBagLayout(JPanel panel) {
        panel.setPreferredSize(new Dimension(GameGUI.DEFAULT_WIDTH, GameGUI.DEFAULT_HEIGHT));
        panel.setBounds(0, 0, GameGUI.DEFAULT_WIDTH, GameGUI.DEFAULT_HEIGHT);
        panel.setLayout(new GridBagLayout());
    }

    // unimplemented methods that classes will need to implement, addButtons is for
    // putting the buttons on the screen after calling 'initializeButtonPanel' and
    // initButtons is for setting up the buttons before calling
    // 'initializeButtonPanel'
    void addComponents();

    void initComponents();

}