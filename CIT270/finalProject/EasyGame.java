package finalProject;
public class EasyGame extends Game {

    public EasyGame(GameGUI flip, int rows, int columns) {
        super("Easy Memory Card Game", flip, rows, columns);
        delay = 2000;
        initializeBoardPanel();
        initializeBoardWithCards();
        //flip.updateFrame();
    }

}
