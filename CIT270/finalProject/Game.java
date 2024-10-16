package finalProject;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Collections;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public abstract class Game extends JPanel {
    protected GameGUI flip;
    protected GridLayout gridLayout;
    protected int rows;
    protected int columns;
    protected ArrayList<Component> boardArrayList;
    protected int score; //TODO: score is unimplemented for now, can maybe make score based on gameType, like
                         //TODO: you get more points per card match in HardGame than in EasyGame
                         //TODO: and make it so you get more points if you take less time to finish the
                         //TODO: game, and also more points for the less amount of card flips it takes you to
                         //TODO: finish the game.
    protected int delay;

    public Game(String title, GameGUI flip, int rows, int columns) {
        this.flip = flip;
        this.rows = rows;
        this.columns = columns;
        flip.setTitle(title);
        boardArrayList = new ArrayList<>();
        setLayout(new GridLayout(rows, columns));
    }
//this method sets up some basic info for the board
    public void initializeBoardPanel() {
        setLocation((GameGUI.DEFAULT_WIDTH / 2), (GameGUI.DEFAULT_HEIGHT));
        setPreferredSize(new Dimension((GameGUI.DEFAULT_WIDTH - 50), (GameGUI.DEFAULT_HEIGHT - 50)));
        setVisible(true);
        revalidate();
        repaint();
    }
//this method shuffles the cards and adds them to panel
    protected void initializeBoardWithCards() {
        createAndAddCardsToArrayList();
        Collections.shuffle(boardArrayList);
        for (Component card : boardArrayList) {
            add(card);// may need to change to just add(card)
        }
    }

    // this method creates card objects and adds them to an ArrayList
    protected void createAndAddCardsToArrayList() {
        // TODO: this method leads to an error when creating hard or medium game because
        // there are not enough sprite files
        // probably a better way of doing this method

        // counters here are for logic so that there can be 16 cards created with 8 sets
        // of 2 cards
        // right now this code assumes that there are enough CardSprites to put on all
        // the cards
        int counter = 0;
        int counter2 = 0;
        for (int index = 0; index < rows; index++) {
            for (int index2 = 0; index2 < columns; index2++) {
                if (counter % 2 == 0) {
                    // there are two Cards being created here so that there can be two cards with
                    // the same picture
                    Component card = new Card(flip.getImagePathArrayElement(counter2), this);
                    Component card2 = new Card(flip.getImagePathArrayElement(counter2), this);
                    boardArrayList.add(card);
                    boardArrayList.add(card2);
                    counter2++;
                }
                counter++;
            }

        }
    }

    // this method turns 2 selected cards face down after a set time 'delay'
    // this is so that when 2 non matching cards are selected there is a short delay
    // where you can see the cards for some time before they are they flipped back
    // face down
    protected void waitIfNoMatch(Card card1, Card card2) {
        disableAllCards();
        Timer timer = new Timer(delay, new ActionListener() {// had to look up some information about Timers and how to
                                                             // add actionListener to them
            @Override
            public void actionPerformed(ActionEvent event) {
                card1.setIcon(null);
                card1.setFaceUp(false);
                card2.setIcon(null);
                card2.setFaceUp(false);
                card1.revalidate();
                card1.repaint();
                card2.revalidate();
                card2.repaint();
                enableAllCardsNotMatched();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // disables all cards from being pressed
    // this is done so that the person must wait until the 2 incorrectly selected
    // cards turn back face down before any other cards can be pressed
    protected void disableAllCards() {
        for (Component card : boardArrayList) {
            card.setEnabled(false);
        }
    }

    protected void enableAllCardsNotMatched() {
        for (Component card : boardArrayList) {
            if (!((Card) card).isFaceUp) { // Only enables cards that are face down because if they are face up it means
                                           // they have been matched so we want them to stay disabled//have to downcast
                                           // to get access to
                                           // isFaceUp field, should be safe because only Card types are being stored
                                           // boardArrayList
                card.setEnabled(true);
            }
        }
    }

    // checks if all cards are face up, if they all are then Game is over
    protected abstract void isGameOver();

}
