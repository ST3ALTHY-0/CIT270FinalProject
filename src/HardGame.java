package src;
public class HardGame extends GameBoardInitialization{
    private static final int DELAY = 1000;

    public HardGame(GameGUI flip, int rows, int columns) {
        super("Hard Memory Match Card Game", flip, rows, columns, DELAY);
        addCardsToGame();
    }
    
    @Override //would be best to throw an exception if the right amount of cards arnt created, but im not going to create my own exception right now
    public void addCardsToGame(){
        createAndAddCardsToArrayList(20);
        createAndAddBombCards(4);
        shuffleCards();
    }
}
