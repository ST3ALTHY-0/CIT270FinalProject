package src.Helper;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

//helper class to preload and retrieve images so the game runs without freezing
public class ImageCache {

    // we create a hashmap here bc its a really quick data structure
    private static final HashMap<String, ImageIcon> imageCache = new HashMap<>();
    private static ArrayList<String> imagePathArray;



    // this method retrieves an image from the cache or loads it if not present
    public static ImageIcon getImageIcon(String imagePath) {
        if (!imageCache.containsKey(imagePath)) {
            System.out.println(imagePath);
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

        for (String path : imagePathArray) {
            try {
            if (!imageCache.containsKey(path)) {
                ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(path.toString()));
                resizedImageIcon = resizeImageIcon(imageIcon, 200, 200); // could change this for hard mode if really needed
                //width:125 height: 200 for hard mode
                //width:185 height:175 for medium
                // 200x200 for easy
                imageCache.put(path, resizedImageIcon);

            }
            } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            }
        }
    }

    // resizes images as needed, so far only the star.png needs resized
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) { //maybe resize the images for higher difficulty games, because the amount of cards on screen increases meaning the pictures appear smaller
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    //we add all image files to list so that they can be inserted into the hashmap later.
    //the created arrayList is also used to grab the different Files when creating Cards.
    //bad implementation because the order files are added is very important here
    private static void addFileNamesToArrayList() {
        imagePathArray = new ArrayList<>();
        imagePathArray.add("./../../resources/CardSprites/apple.jpg");
        imagePathArray.add("./../../resources/CardSprites/duck.jpg");
        imagePathArray.add("./../../resources/CardSprites/frog.png");
        imagePathArray.add("./../../resources/CardSprites/star.png");
        imagePathArray.add("./../../resources/CardSprites/heart.jpg");
        imagePathArray.add("./../../resources/CardSprites/musicNote.png");
        imagePathArray.add("./../../resources/CardSprites/smiley.png");
        imagePathArray.add("./../../resources/CardSprites/turtle.jpg");
        imagePathArray.add("./../../resources/CardSprites/cow.jpg");
        imagePathArray.add("./../../resources/CardSprites/soccerBall.png");
        imagePathArray.add("./../../resources/CardSprites/back.jpg");
        imagePathArray.add("./../../resources/Other/trees.jpg");
        imagePathArray.add("./../../resources/CardSprites/bomb.png");

    }

    public static String getImageFile(int index) {
        return imagePathArray.get(index);
    }

    //we use this method to grab a certin File based on its name and path
    public static String getImageFile(String dir, String name, String fileType) {
        String targetFilePath = String.format("./../../resources/%s/%s.%s", dir, name, fileType);
        return targetFilePath;
    }

}