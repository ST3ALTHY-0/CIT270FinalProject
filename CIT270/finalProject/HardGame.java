package finalProject;
public class HardGame extends Game{

    //TODO: add extra stuff here that will make the game more difficult
    public HardGame(GameGUI flip){
        super("title", flip, 6, 6);
        delay = 1000;
        initializeBoardPanel();
        initializeBoardWithCards();
    }
    
}
