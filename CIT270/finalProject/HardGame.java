package finalProject;
public class HardGame extends Game{

    //TODO: add extra stuff here that will make the game more difficult
    public HardGame(GameGUI flip){
        super("title", flip, 6, 4);
        delay = 1000;
        initializeBoardPanel();
        initializeBoardWithCards();
    }
    @Override
    protected void isGameOver() {
        if (Card.numOfFaceUpCards == 24) {
            // TODO: send to end game sceen(that shows score, asks if you want to upload
            // TODO: score), which will then send you back to menu, after you click 'ok'
        }
    }
}
