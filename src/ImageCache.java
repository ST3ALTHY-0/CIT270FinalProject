package src;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

//helper class to preload and retrieve images so the game runs without freezing
public class ImageCache {

    // we create a hashmap here bc its a really quick data structure
    private static final HashMap<File, ImageIcon> imageCache = new HashMap<>();
    private static ArrayList<File> imagePathArray;

    // this method retrieves an image from the cache or loads it if not present
    public static ImageIcon getImageIcon(File imagePath) {
        if (!imageCache.containsKey(imagePath)) {
            ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(imagePath.toString()));
            imageCache.put(imagePath, imageIcon);
        }
        return imageCache.get(imagePath);
    }

    // this method preloads sprites so that there is not a freeze when loading into
    // a game
    public static void preloadImages() {
        addFileNamesToArrayList();
        ImageIcon resizedImageIcon; 

        for (File path : imagePathArray) {
            if (!imageCache.containsKey(path)) {
                ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(path.toString()));
                    resizedImageIcon = resizeImageIcon(imageIcon, 200, 200);//could change this for hard mode if really needed
                    imageCache.put(path, resizedImageIcon);
            }
        }
    }

    // resizes images as needed, so far only the star.png needs resized
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    //we add all image files to list so that they can be inserted into the hashmap later.
    //the created arrayList is also used to grab the different Files when creating Cards
    private static void addFileNamesToArrayList() {
        imagePathArray = new ArrayList<>();
        imagePathArray.add(new File("../resources/CardSprites/apple.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/duck.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/frog.png"));
        imagePathArray.add(new File("../resources/CardSprites/star.png"));
        imagePathArray.add(new File("../resources/CardSprites/heart.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/musicNote.png"));
        imagePathArray.add(new File("../resources/CardSprites/smiley.png"));
        imagePathArray.add(new File("../resources/CardSprites/turtle.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/cow.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/soccerBall.png"));
        imagePathArray.add(new File("../resources/CardSprites/back.jpg"));
        imagePathArray.add(new File("../resources/Other/trees.jpg"));
        imagePathArray.add(new File("../resources/CardSprites/bomb.png"));

    }

    public static File getImageFile(int index) {
        return imagePathArray.get(index);
    }

    //we use this method to grab a certin File based on its name and path
    public static File getImageFile(String dir, String name, String fileType) {
        String targetFilePath = String.format("../resources/%s/%s.%s", dir, name, fileType);
        return new File(targetFilePath);
    }

}