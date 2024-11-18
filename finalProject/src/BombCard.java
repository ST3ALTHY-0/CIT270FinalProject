package finalProject.src;
//Maybe have 2 Decoy cards i.e bombs, if you click both bombs, you lose
import java.io.File;
//TODO: stuff
//also i dont have a bomb card image rn, 
public class BombCard extends Card{
    private static File imagePathFile = ImageCache.getImageFile("bomb", "jpg");
    
    public BombCard(GameManager game){
        super(imagePathFile, game);
        addActionListener(e -> game.new CardClickHandler(this));
    }
    
}
