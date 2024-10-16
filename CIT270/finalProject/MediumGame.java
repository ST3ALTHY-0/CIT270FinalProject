package finalProject;

    //TODO: add extra stuff here that will make the game more difficult

public class MediumGame extends Game{

    
    public MediumGame(GameGUI flip){
        super("title", flip, 5, 4);
        delay = 1500;
        initializeBoardPanel();
        initializeBoardWithCards();
    }


  
    
}
