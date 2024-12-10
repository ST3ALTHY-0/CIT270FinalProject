package src;
public class MediumGame extends GameBoardInitialization{
    private static final int DELAY = 1500;

    public MediumGame(GameGUI flip, int rows, int columns) {
        super("Medium Memory Match Card Game", flip, rows, columns, DELAY);
        addCardsToGame();
    }
    
    @Override
    public final void addCardsToGame(){
        super.createAndAddCardsToArrayList(16);
        super.createAndAddBombCards(4);
        super.shuffleCards();
    }
}
