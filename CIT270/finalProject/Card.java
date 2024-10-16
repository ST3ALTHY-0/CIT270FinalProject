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
    protected static int numOfFaceUpCards;

    public Card(String imagePath, Game game) {
        icon = ImageCache.getImage(imagePath);
        setIcon(null);//this sets the face down icon
        isFaceUp = false;
        this.game = game;
        setDisabledIcon(icon);//this sets the face up icon
        addActionListener(this);
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
            if (clickedIcon.equals(this.icon)) {// if the clicked card icon in previous turn equals the card clicked icon this
                                                // turn
                clickedCard.setEnabled(false);
                setEnabled(false);
                isFaceUp = true;
                clickedCard.isFaceUp = true;
                numOfFaceUpCards += 2;
                checkIfGameIsOver();
            } else {//else if the 2 clicked cards don't match
                game.waitIfNoMatch(clickedCard, this);
            }
        }
        actionPerformedCounter++;
    }

    private void checkIfGameIsOver(){
        if(game instanceof EasyGame){
            EasyGame easyGame = (EasyGame) game;
            easyGame.isGameOver();
        }else if (game instanceof MediumGame){
            MediumGame mediumGame = (MediumGame) game;
            mediumGame.isGameOver();
        }else if(game instanceof HardGame){
            HardGame hardGame = (HardGame) game;
            hardGame.isGameOver();
        }
    }

    public void setFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

}
