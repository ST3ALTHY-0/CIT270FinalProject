package finalProject;

import javax.swing.*;

import java.awt.Image;
import java.util.HashMap;

public class ImageCache {
    // Create a static HashMap to store image paths and their corresponding ImageIcons
    private static HashMap<String, ImageIcon> imageCache = new HashMap<>();

    // Method to retrieve an image from the cache or load it if not present
    public static ImageIcon getImage(String imagePath) {
        // Check if the image is already cached
        if (!imageCache.containsKey(imagePath)) {
            // If not in cache, load it and put it in the cache
            ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(imagePath));
            imageCache.put(imagePath, imageIcon);
        }
        // Return the cached image
        return imageCache.get(imagePath);
    }

    // Optional: Method to pre-load multiple images into the cache (if needed)
    public static void preloadImages(String[] imagePaths) {
        ImageIcon resizedImageIcon = null;
        for (String path : imagePaths) {
            if (!imageCache.containsKey(path)) {//if imagePath does not already exist in the hashmap
                ImageIcon imageIcon = new ImageIcon(ImageCache.class.getResource(path));//turn the png FilePath into an ImageIcon Obj
                if(path.equals("\\CardSprites\\star.jpg")){
                    resizedImageIcon = resizeImageIcon(imageIcon, 10, 10);
                    imageCache.put(path, resizedImageIcon);
                    System.out.println("sssdsdsd");
                }else{
                imageCache.put(path, imageIcon); //put that imageIcon in hashmap with index being String filePath
                }
            }
            
        }
    }
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // transform it
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // scale it
        return new ImageIcon(newImage); // transform it back
    }
}