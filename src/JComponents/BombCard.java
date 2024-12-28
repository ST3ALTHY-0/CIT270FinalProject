package src.JComponents;

import src.Helper.ImageCache;
import src.JPanels.GameManager;
public final class BombCard extends Card{
    private static final String BOMB_IMAGE_FILE = ImageCache.getImageFile("CardSprites", "bomb", "png");

    public BombCard(GameManager game){
        super(BOMB_IMAGE_FILE, game);
    }
}
 