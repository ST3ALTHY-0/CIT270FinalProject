package finalProject.src;

import javax.swing.*;

import java.awt.Image;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

//helper class to preload and retrieve images so the game runs without freezing
public class ImageCache {

    // Hashmap, basically like a python dictionary. Its key, value pair and is
    // really fast
    private static HashMap<File, ImageIcon> imageCache = new HashMap<>();
    private static ArrayList<File> imagePathArray;

    // this method retrieves an image from the cache or loads it if not present
    public static ImageIcon getImageIcon(File imagePath) {
        // Check if the image is already cached
        if (!imageCache.containsKey(imagePath)) {// this shouldn't be needed but just in case
            ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(imagePath.toString()));
            imageCache.put(imagePath, imageIcon);
        }
        return imageCache.get(imagePath);
    }

    // this method preloads sprites so that there is not a freeze when loading into
    // a
    // game
    public static void preloadImages() {
        addFileNamesToArrayList();
        ImageIcon resizedImageIcon = null; 

        for (File path : imagePathArray) {
            if (!imageCache.containsKey(path)) {// if imagePath does not already exist in the hashmap
                ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(path.toString()));// turn the png
                                                                                                   // FilePath into an
                                                                                                   // ImageIcon Obj, ImageIcon constructor requires URL type, so thats whats with the ImageCache.class.getResource(path.toString()) 
                if (path.toString().equals("..\\Resources\\star.png")) {
                    resizedImageIcon = resizeImageIcon(imageIcon, 200, 200);
                    imageCache.put(path, resizedImageIcon);
                } else {
                    imageCache.put(path, imageIcon); // put that imageIcon in hashmap with index being String filePath
                }
            }

        }
    }

    // resizes images as needed, so far only the star.png needs resized
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private static void addFileNamesToArrayList() {
        imagePathArray = new ArrayList<>();
        imagePathArray.add(new File("..\\Resources\\star.png"));
        imagePathArray.add(new File("..\\Resources\\apple.jpg"));
        imagePathArray.add(new File("..\\Resources\\duck.jpg"));
        imagePathArray.add(new File("..\\Resources\\frog.png"));
        imagePathArray.add(new File("..\\Resources\\heart.jpg"));
        imagePathArray.add(new File("..\\Resources\\musicNote.png"));
        imagePathArray.add(new File("..\\Resources\\smiley.png"));
        imagePathArray.add(new File("..\\Resources\\turtle.jpg"));
        imagePathArray.add(new File("..\\Resources\\back.jpg"));

    }

    // returns fileName of a sprite at specified index
    public static File getImageFile(int index) {
        return imagePathArray.get(index);
    }

    public static File getImageFile(String name, String fileType) {//TODO: this is not working : need to probab
        String targetFilePath = String.format("..\\Resources\\%s.%s", name, fileType);
        //System.out.println(targetFilePath);
        return new File(targetFilePath);
        //return getImageIcon(new File(targetFilePath));
    }

}