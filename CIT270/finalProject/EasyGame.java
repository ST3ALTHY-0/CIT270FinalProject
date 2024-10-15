package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EasyGame extends Game{
    private int rows;
    private int columns;

    
    public EasyGame(GameGUI filp, int rows, int columns){
        super("Easy Memory Card Game", filp);
        this.rows = rows;
        this.columns = columns;
        boardPanel.setLayout(new GridLayout(rows, columns));
        initializeBoardPanel();
        initializeBoardWithCards();
        //filp.pack();
        System.out.println("easyGame");
    }

    public void actionPerformed(ActionEvent e){

    }

    @Override
    void initializeBoardWithCards() {
        for (int index = 0; index < rows; index++) {
            for (int index2 = 0; index2 < columns; index2++) {
                Component card = new Card("CardSprites//star.png");// I feel like logic for creating two of the same cards and connecting them somehow should go here maybe
                boardPanel.add(card);
            }
            
        }



    }
    
}
