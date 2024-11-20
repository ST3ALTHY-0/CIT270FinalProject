package src;

    //TODO: add extra stuff here that will make the game more difficult

public class MediumGame extends GameManager{
    private static final int TWO_SECOND_DELAY = 2000;

    public MediumGame(GameGUI flip, int rows, int columns) {
        super("Medium", flip, rows, columns, TWO_SECOND_DELAY);
    }
}
