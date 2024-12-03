package src;
public class MediumGame extends GameBoardInitialization{
    private static final int DELAY = 1500;

    public MediumGame(GameGUI flip, int rows, int columns) {
        super("Medium Memory Match Card Game", flip, rows, columns, DELAY);
        addCardsToGame();
    }
    
    @Override
    public void addCardsToGame(){
        createAndAddCardsToArrayList(16);
        createAndAddBombCards(4);
        shuffleCards();
    }
}
