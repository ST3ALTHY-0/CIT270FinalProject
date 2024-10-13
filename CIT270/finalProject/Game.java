package finalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class Game extends JPanel {
    protected JPanel boardPanel;
    protected GameGUI flip;
    protected JButton cardButton;

    public Game(String title, int columns, int rows, GameGUI flip){
        setLayout(new GridLayout(rows, columns));
        this.flip = flip; 
    }

    public void initializeBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLocation((GameGUI.DEFAULT_WIDTH/2), (GameGUI.DEFAULT_HEIGHT));
      //  boardPanel.setSize((550), (550));
        boardPanel.setPreferredSize(new Dimension((GameGUI.DEFAULT_WIDTH-50), (GameGUI.DEFAULT_HEIGHT-50)));
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setVisible(true);
        add(boardPanel);
        boardPanel.revalidate();
        boardPanel.repaint();
        System.out.println("Game");

    }

    abstract void initializeBoardWithCards();//might need to be abstract

    
    
}
