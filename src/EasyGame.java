package src;
public class EasyGame extends GameBoardInitialization{

    private static final int DELAY = 2000;

    public EasyGame(GameGUI flip, int rows, int columns) {
        super("Easy Memory Match Card Game", flip, rows, columns, DELAY);
        super.addCardsToGame();
    }    
}
