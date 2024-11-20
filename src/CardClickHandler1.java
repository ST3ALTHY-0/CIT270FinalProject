package src;
//this class has been moved to inner class inside GameManager
//may need to move back here depending on how complex cardClicks can get based on additional card types added later
public class CardClickHandler1 {
    private GameManager game;

    public CardClickHandler1(GameManager game, Card card) {
        this.game = game;
        handleCardClick(card);
    }

    public void handleCardClick(Card card) {
        card.setFaceUp(true);
        if (isFirstCardClick()) {
            handleFirstCardClick(card);
        } else {
            handleSecondCardClick(card);
        }
        game.incrementActionPerformedCounter();
    }

    private boolean isFirstCardClick() {
        return game.getActionPerformedCounter() % 2 == 0;
    }

    private void handleFirstCardClick(Card card) {
        game.setClickedCard(card);
        game.setClickedIcon(card.getIcony());
    }

    private void handleSecondCardClick(Card card) {
        if (isMatch(card)) {
            game.markCardsAsMatched(card);
            game.isGameOver();
        } else {
            game.waitIfNoMatch(game.getClickedCard(), card);
        }
    }


    private boolean isMatch(Card card) {
        return game.getClickedIcon().equals(card.getIcony());
    }
}