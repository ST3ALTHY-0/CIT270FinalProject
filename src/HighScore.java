package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

//This class need implemented so that on main panel you see a button that will lead you to a screen with high scores displayed

public class HighScore extends JPanel implements ActionListener{
    protected static String name;

    public HighScore(GameGUI flip){
        initializeHighScorePanel();
    }

    protected void initializeHighScorePanel(){

    }

    //other methods to create like maybe a pop up form to enter name and such

    @Override//add actions for buttons like save Highscore, add name(save name to), so on
    public void actionPerformed(ActionEvent e) {
        
    }

}
