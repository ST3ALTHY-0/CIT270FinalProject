package src;

import java.io.File;
public final class BombCard extends Card{
    private static final File BOMB_IMAGE_FILE = ImageCache.getImageFile("CardSprites", "bomb", "png");

    public BombCard(GameManager game){
        super(BOMB_IMAGE_FILE, game);
    }
}
 