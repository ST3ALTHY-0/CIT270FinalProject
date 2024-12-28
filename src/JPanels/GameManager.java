package src.JPanels;

import java.awt.*;
import java.util.Stack;
import javax.swing.Timer;

import src.DTOs.Score;
import src.JComponents.BombCard;
import src.JComponents.Card;
import src.JFrame.GameGUI;

public class GameManager {
    private GameGUI flip;
    private Score score; 
    private final int delay;
    private int actionPerformedCounter;
    private final GameBoardInitialization init;
    final Stack<Card> cardStack;

    public GameManager(GameGUI flip, int delay, GameBoardInitialization init) {
        this.flip = flip;
        this.delay = delay;
        this.init = init;
        cardStack = new Stack<>();
    }

    public void startGame(){
        startTrackingScore();
    }

    public Score getScoreForGame(){
        return score;
    }

    private void startTrackingScore() {
        score = new Score(init);
        score.startGame();
    }

    // this method turns 2 selected cards face down after a set time 'delay'
    // this is so that when 2 non matching cards are selected there is a short delay
    // where you can see the cards for some time before they are they flipped back
    // face down
    private void waitIfNoMatch(Card card1, Card card2) {
        disableAllCards(card1, card2);
        Timer timer = new Timer(delay, (event) -> {
            card1.setFaceUp(false);
            card2.setFaceUp(false);
            enableAllCardsNotMatched();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void disableAllCards(Card card1, Card card2) {
        for (Component card : init.getCardArrayList()) {
            Card cardy = (Card) card;
            if (!cardy.getFaceUp()) {
                if (cardy != card1 && cardy != card2) {
                    //we need to change the disabled icon here because usually the disabled Icon is the face up picture,
                    //but we dont want to reveal that here so we have to change it.[this is not great and should be redesigned]
                    cardy.setDisabledIcon(cardy.getBackIcon());
                }
                cardy.setEnabled(false);
            }
            
        }
    }

    private void enableAllCardsNotMatched() {
        for (Component card : init.getCardArrayList()) {
            Card cardy = (Card) card;
            if (!cardy.getFaceUp()) {
                cardy.setEnabled(true);
                //we change the disabled icon back to the face up picture (again, poor design)
                cardy.setDisabledIcon(cardy.getIcony());
            }
        }
    }

    public void isGameOver(Boolean didWin) {
        //we check the cardStack size, which contains one out of the two versions of the correctly picked cards 
        //and we compare that to half the size of the win condition card list. Half because cardStack only contains one version of the correctly picked set of cards
        if (cardStack.size() == (init.getWinConditionCardsList().size() / 2)) {
            gameOver(didWin);
        }
    }

    public void gameOver(Boolean didWin) {
        score.endGame();
        if (didWin){
            score.doMathForScore();
        }
        //flip.clearPanel();
        flip.toEndGame(flip, score);
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

    public class CardClickHandler {
        public CardClickHandler(Card card) {
            handleCardClick(card);
        }

        private void handleCardClick(Card card) {
            card.setFaceUp(true);
            if (isFirstCardClick()) {
                handleFirstCardClick(card);
            } else {
                handleSecondCardClick(card);
            }
            incrementActionPerformedCounter();
        }

        private boolean isFirstCardClick() {
            return getActionPerformedCounter() % 2 == 0;
        }

        private void handleFirstCardClick(Card card) {
            cardStack.push(card);
        }

        private void handleSecondCardClick(Card card) {
            if (isMatch(card)) {
                checkCardIsBombCard(card);
                isGameOver(true);
            } else {
                waitIfNoMatch(cardStack.peek(), card);
                cardStack.pop();
            }
        }

        private void checkCardIsBombCard(Card currentCard) {
            Card previousCard = cardStack.peek();
            if (previousCard instanceof BombCard && currentCard instanceof BombCard) {
                gameOver(false);
            }
        }

        private boolean isMatch(Card currentCard) {
            Card previousCard = cardStack.peek();
            return previousCard.getIcony().equals(currentCard.getIcony());
        }

    }

}