package finalProject.src;

import java.awt.*;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public abstract class GameManager {
    protected GameGUI flip;
    protected Score score; // TODO: score is unimplemented for now, can maybe make score based on gameType,
                         // like
                         // TODO: you get more points per card match in HardGame than in EasyGame
                         // TODO: and make it so you get more points if you take less time to finish the
                         // TODO: game, and also more points for the less amount of card flips it takes
                         // TODO: you to finish the game.
    private int delay;
    private int numOfFaceUpCards;
    private Card clickedCard;
    private Icon clickedIcon;
    private int actionPerformedCounter;
    private GameBoardInitialization init;

    public GameManager(String title, GameGUI flip, int rows, int columns, int delay) {
        this.flip = flip;
        this.delay = delay;
        init = new GameBoardInitialization(title, flip, rows, columns, this);
        //saveData();// TODO: remove later, should go in HighScores class when created
    }

    public GameBoardInitialization getInit() {
        return init;
    }

    // this method turns 2 selected cards face down after a set time 'delay'
    // this is so that when 2 non matching cards are selected there is a short delay
    // where you can see the cards for some time before they are they flipped back
    // face down
    protected void waitIfNoMatch(Card card1, Card card2) {
        disableAllCards(card1, card2);
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                card1.setFaceUp(false);
                card2.setFaceUp(false);
                enableAllCardsNotMatched();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Has to be a better way to do this
    protected void disableAllCards(Card card1, Card card2) {
        for (Component card : init.getBoardArrayList()) {
            Card cardy = (Card) card;
            if (!cardy.getFaceUp()) {
                if (cardy != card1 && cardy != card2) {
                    cardy.setDisabledIcon(cardy.getBackIcon());
                }
                cardy.setEnabled(false);
            }
        }
    }

    // Has to be a better way to do this
    protected void enableAllCardsNotMatched() {
        for (Component card : init.getBoardArrayList()) {
            Card cardy = (Card) card;
            if (!cardy.getFaceUp()) {
                cardy.setEnabled(true);
                cardy.setDisabledIcon(cardy.getIcony());
            }
        }
    }

    abstract void isGameOver();

    //just a different way of checking if game is over, idk if we should use this or the abstract method
    public void gameOver(){
        // if(init.getBoardArrayList().size() == getNumOfFaceUpCards()){
        //     System.out.println("Game Over");
        // }
        //TODO: send to endGame screen
        cleanup();
        flip.clearPanel(); 
        flip.toGameMenu(flip);
    }

    public void sendToEndGameScreen(){
        //TODO
    }

    public void cleanup() {

        init = null;
        clickedCard = null;
        clickedIcon = null;
    }

    // this method will create a new Data Obj, TODO: is more appropiate for
    // TODO: this method to go in HighScores, or another class
    protected void saveData() {
        Data data = new Data(3000, Data.getGameTypeToString(this), "Luke");// testing
        // data database obj
        Database db = new Database();
        db.insertDataIntoUsers(data);
        db.insertDataIntoScores(data);
        // db.printAllData();
        db.selectDataFromScores(10);
    }

    public int getNumOfFaceUpCards() {
        return numOfFaceUpCards;
    }

    public void setNumOfFaceUpCards(int numOfFaceUpCards) {
        this.numOfFaceUpCards = numOfFaceUpCards;
    }

    public Card getClickedCard() {
        return clickedCard;
    }

    public void setClickedCard(Card card) {
        clickedCard = card;
    }

    public Icon getClickedIcon() {
        return clickedIcon;
    }

    public void setClickedIcon(Icon icon) {
        clickedIcon = icon;
    }

    public int getActionPerformedCounter() {
        return actionPerformedCounter;
    }

    public void setActionPerformedCounter(int actionPerformedCounter) {
        this.actionPerformedCounter = actionPerformedCounter;
    }

    public void incrementActionPerformedCounter() {
        this.actionPerformedCounter++;
    }

    protected void markCardsAsMatched(Card card) {
        getClickedCard().setCardMatched();
        card.setCardMatched();
    }

    public class CardClickHandler {
        public CardClickHandler(Card card){
            handleCardClick(card);
         }

        public void handleCardClick(Card card) {
            card.setFaceUp(true);
            if (isFirstCardClick()) {
                handleFirstCardClick(card);
            } else {
                handleSecondCardClick(card);
            }
            incrementActionPerformedCounter();
        }
        public void handleCardClick(BombCard bombCard) {
            bombCard.setFaceUp(true);
            if (isFirstCardClick()) {
                handleFirstCardClick(bombCard);
            } else {
                handleSecondCardClick(bombCard);
            }
            incrementActionPerformedCounter();
        }

        private boolean isFirstCardClick() {
            return getActionPerformedCounter() % 2 == 0;
        }

        private void handleFirstCardClick(Card card) {
            setClickedCard(card);
            setClickedIcon(card.getIcony());
        }

        private void handleSecondCardClick(Card card) {
            if (isMatch(card)) {
                if(card instanceof BombCard){
                    gameOver();
                    return;
                }
                markCardsAsMatched(card);
                isGameOver();
                //gameOver();
            } else {
                waitIfNoMatch(getClickedCard(), card);
            }
        }


        private boolean isMatch(Card card) {
            return getClickedIcon().equals(card.getIcony());
        }

    }

}