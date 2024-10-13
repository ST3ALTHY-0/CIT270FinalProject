package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardGame extends Game{

    
    public HardGame(GameGUI filp){
        super("title", 4, 4, filp);
    }

    public void actionPerformed(ActionEvent e){

    }

    @Override
    void initializeBoardWithCards() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeBoardWithCards'");
    }
    
}
