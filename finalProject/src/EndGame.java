package finalProject.src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

//this class should be called after a game is over and should show score/time and have a option to save score to DB along with a username/gameType/timestamp. should also have ok button that sends you back to main menu
public class EndGame extends JPanel implements ActionListener {
//maybe make the screen just overlap with then end game screen instead of replacing it?
//need to use card layout or JLayeredPane on GameGUI class
    private JButton menu;
    private JButton addHighScore;
    private JButton exit;
    private GameGUI flip;

    public EndGame(GameGUI flip){
        this.flip = flip;
        initializeMenu();
    }
    //add buttons to panel
    private void initializeMenu(){
        menu = new JButton("To Menu");
        addHighScore = new JButton("Add your score to high score leader board.");
        exit = new JButton("exit");

        add(menu);
        add(addHighScore);
        add(exit);
        setVisible(true);

        menu.addActionListener(this);
        addHighScore.addActionListener(this); 
        exit.addActionListener(this);
    }

    @Override
public void actionPerformed(ActionEvent e){
    flip.clearPanel(); // Clear the main panel
    if(e.getSource() == menu){
        flip.toGameMenu(flip);
    }else if(e.getSource() == addHighScore){
    //show pop up screen to add high score
    }else if (e.getSource() == exit){
        System.exit(0);
    }
}
}
