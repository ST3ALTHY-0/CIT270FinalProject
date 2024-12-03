package src;

import javax.swing.*;
import java.io.File;


//note: don't need to implement actionListener bc Java knows to use the ActionListener functional interface because of the context in which the lambda expression is used.
public class Card extends JButton {
    private boolean isFaceUp;
    private ImageIcon icony;
    private ImageIcon backIcon;
// fetches the image from database
    public Card(File imagePath, GameManager game) {
        icony = ImageCache.getImageIcon(imagePath);
        backIcon = ImageCache.getImageIcon(ImageCache.getImageFile("CardSprites", "back", "jpg")); 
        setFaceUp(false);
        setIcon(backIcon);
        setDisabledIcon(icony);
        addActionListener(e -> game.new CardClickHandler(this));
        }
    //replaced this code with the above lambda expression
    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     //CardClickHandler1 click = new CardClickHandler1(game);
    //     //click.handleCardClick(this);
    //     game.new CardClickHandler(this);
    // }

    public void setFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
        updateIconBasedOnFaceUp();
    }
    public boolean getFaceUp(){
        return isFaceUp;
    }

    public void setCardMatched() {
        setEnabled(false);
    }

    public void updateIconBasedOnFaceUp() {
        if (isFaceUp) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }
    public Icon getIcony(){
        return icony;
    }
    public void setIcony(ImageIcon icon){
        this.icony = icon;
    }

    public Icon getBackIcon(){
        return backIcon;
    }

}
