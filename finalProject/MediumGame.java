package finalProject;

    //TODO: add extra stuff here that will make the game more difficult

public class MediumGame extends Game{

    
    public MediumGame(GameGUI flip){
        super("title", flip, 5, 4);
        delay = 1500;
        initializeBoardPanel();
        initializeBoardWithCards();
    }

    protected void isGameOver() {
        if (Card.numOfFaceUpCards == 20) {
            // TODO: send to end game sceen(that shows score, asks if you want to upload
            // TODO: score), which will then send you back to menu, after you click 'ok'
        }
    }
  
    
}
