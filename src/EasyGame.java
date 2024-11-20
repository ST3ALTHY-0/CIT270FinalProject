package src;
//easyGame extends game
//game calls GameInit
public class EasyGame extends GameManager {
    private static final int TWO_SECOND_DELAY = 2000;

    public EasyGame(GameGUI flip, int rows, int columns) {
        super("Easy", flip, rows, columns, TWO_SECOND_DELAY);
    }

    

    //TODO: method to update score

}
