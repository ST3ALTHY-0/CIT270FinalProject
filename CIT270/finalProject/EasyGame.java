package finalProject;

public class EasyGame extends Game {

    public EasyGame(GameGUI flip, int rows, int columns) {
        super("Easy Memory Card Game", flip, rows, columns);
        delay = 2000;
        initializeBoardPanel();
        initializeBoardWithCards();
        // flip.updateFrame();
    }

    @Override
    protected void isGameOver() {
        if (Card.numOfFaceUpCards == 16) {
            flip.clearPanel();
            flip.toGameMenu(flip);
            // TODO: send to end game sceen(that shows score, asks if you want to upload
            // TODO: score), which will then send you back to menu, after you click 'ok'
        }
    }

}
