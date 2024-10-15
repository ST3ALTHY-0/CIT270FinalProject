package finalProject;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Card extends JButton implements ActionListener{
    protected Image image;


    public Card(String imagePath) {
        ImageIcon icon = ImageCache.getImage(imagePath);
        setPreferredSize(new Dimension(200, 200));
        setIcon(icon);
        setPreferredSize(new Dimension(200, 200));
    }

    public Image getImage(String fileName){
        try{
            Image img = ImageIO.read(getClass().getResource(fileName));
        return img;
        }catch (Exception e){
            System.out.println("error");     
          }
        //image.setImage()
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        
    }

}

    

    //need helper funtion that will iterate over each file in CardSprites and return the fileName as a String

    

