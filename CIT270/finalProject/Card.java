package finalProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Card extends JButton implements ActionListener {
    protected boolean isFaceUp;
    protected ImageIcon icon;
    protected static int actionPerformedCounter = 0;//keeps track of how many cards have been flipped
    protected static Icon clickedIcon;
    protected static Card clickedCard;
    protected Game game;

    public Card(String imagePath, Game game) {
        icon = ImageCache.getImage(imagePath);
        setIcon(null);
        isFaceUp = false;
        this.game = game;
        setDisabledIcon(icon);
        addActionListener(this);
        //revalidate();
        //repaint();
    }

    // click one card
    // click another card
    // check if they match
    // if they do match leave them face up and disable their buttons
    // if they do not match have them flip back down after a few seconds
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isFaceUp) {// if face down
            setIcon(icon);
            isFaceUp = true;
        }
        if (actionPerformedCounter % 2 == 0) {// if first card is selected
            clickedCard = this;
            clickedIcon = this.icon;
        } else {// if second card is selected
            if (clickedIcon.equals(this.icon)) {// if the clicked card in previous turn equals the card clicked this
                                                // turn
                clickedCard.setEnabled(false);
                setEnabled(false);
                isFaceUp = true;
                clickedCard.isFaceUp = true;
                game.isGameOver();
            } else {//else if the 2 clicked cards don't match
                game.disableAllCards();
                game.waitIfNoMatch(clickedCard, this);
            }
        }
        actionPerformedCounter++;
    }

    public void setFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

}
