package finalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenu extends JPanel implements ActionListener {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private GameGUI flip;

    public GameMenu(GameGUI flip){
        this.flip = flip;
        initializeMenu();
    }
    //add buttons to panel
    private void initializeMenu(){
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");


        easyButton.setBackground(Color.GREEN);
        mediumButton.setBackground(Color.YELLOW);
        hardButton.setBackground(Color.RED);

        add(easyButton);
        add(mediumButton);
        add(hardButton);
        setVisible(true);

        easyButton.addActionListener(this);
        mediumButton.addActionListener(this); 
        hardButton.addActionListener(this);
    }

    @Override
public void actionPerformed(ActionEvent e){
    flip.clearPanel(); // Clear the main panel
    if(e.getSource() == easyButton){
        flip.toEasyGame(flip);
    }else if(e.getSource() == mediumButton){
        flip.toMediumGame(flip);
    }else if (e.getSource() == hardButton){
        flip.toHardGame(flip);
    }
}


    
}
