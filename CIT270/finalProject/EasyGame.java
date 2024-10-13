package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyGame extends Game{

    
    public EasyGame(GameGUI filp){
        super("Easy Memory Card Game", 4, 4, filp);
        initializeBoardPanel();
        System.out.println("easyGame");
    }

    public void actionPerformed(ActionEvent e){

    }

    @Override
    void initializeBoardWithCards() {

    }
    
}
