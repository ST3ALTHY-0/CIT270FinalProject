package src;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;//why this isnt included in above important IDK, java is weird

//This class need implemented so that on main panel you see a button that will lead you to a screen with high scores displayed

public class HighScore extends JPanel implements CenterButtonPanel {
    private GameGUI flip;
    private Data data;
    private JTable highScoreTable;// for showing top 10 high scores
    private JToggleButton toggleButtonForDB;// for choosing local or remote db
    private JTextField nameField;// for entering the name that the user wants to associate with their highScore
    private JButton submitButton;
    private Score score;// the score the player achieved when playing the game
    private Object[][] dataArray; // array for adding data to Table
    private String[] JTableColumNames = { "Name", "Score", "GameType" };
    private JPanel componentPanel;
    private JPanel componentPanel2;
    private JTextArea dbText;
    private JTextArea scoreText;
    private boolean didSubmit = false;
    private JButton homeButton;

    private DefaultTableModel tableModel;// A handy in between for adding the 2d data array
                                         // into JTable, important for updating JTable when switching between DB

    public HighScore(GameGUI flip, Score score) {
        this.flip = flip;
        this.score = score;
        setUpAndAddComponents();
    }

    private void setUpAndAddComponents() {
        initComponents();
        addComponents();
    }

    private void updateDBtext() {
        if (GameGUI.db.getSoup() == true) {
            dbText.setText("local DB ");
        } else {
            dbText.setText("remote DB");
        }
    }

    public void initComponents() {
        // initialize the JButtons, JTable, and any other components you want here
        initializeGridBagLayout(this);
        componentPanel = new JPanel(new FlowLayout());
        componentPanel2 = new JPanel(new FlowLayout());
        componentPanel.setBackground(new Color(0, 0, 0, 255));
        componentPanel.setOpaque(false);
        componentPanel2.setBackground(new Color(0, 0, 0, 255));
        componentPanel2.setOpaque(false);

        dbText = new JTextArea();
        dbText.setEditable(false);
        updateDBtext();
        scoreText = new JTextArea("Your score: " + String.valueOf(score.getScore()));
        scoreText.setEditable(false);
        scoreText.setPreferredSize(new Dimension(100, 20));
        homeButton = new JButton("To Menu");
        homeButton.addActionListener(e -> flip.toGameMenu(flip));
        initToggleButton();
        initTextField();
        initSubmitButton();
        initHighScoreTable();
    }

    public void addComponents() {
        // add all components to the screen
        // add(Box.createVerticalGlue());
        // add(Box.createVerticalStrut(20));

        scoreText.setAlignmentY(Component.CENTER_ALIGNMENT);
        componentPanel.add(scoreText);

        componentPanel.add(nameField);
        componentPanel.add(Box.createVerticalGlue());
        componentPanel.add(Box.createVerticalStrut(20));
        nameField.setAlignmentY(Component.CENTER_ALIGNMENT);

        
        componentPanel2.add(toggleButtonForDB);
        componentPanel2.add(Box.createVerticalGlue());
        componentPanel2.add(Box.createVerticalStrut(20));
        toggleButtonForDB.setAlignmentY(Component.CENTER_ALIGNMENT);
        

        componentPanel2.add(submitButton);
        componentPanel2.add(Box.createVerticalGlue());
        componentPanel2.add(Box.createVerticalStrut(20));
        submitButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        componentPanel.add(dbText);
        componentPanel.add(Box.createVerticalGlue());
        componentPanel.add(Box.createVerticalStrut(20));
        dbText.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(componentPanel);
        add(componentPanel2);
        initializeCenteredPanel(this, highScoreTable);
        add(Box.createVerticalGlue());
        add(Box.createVerticalStrut(20));
        homeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(homeButton);
        
    }

    // this is a separate method from init Components bc initializing the table will
    // likely take up a good few lines of code and we want to separate logic and
    // make things readable
    private void initHighScoreTable() {
        tableModel = new DefaultTableModel(dataArray, JTableColumNames);
        highScoreTable = new JTable(tableModel);
        getDataAndAddToArray();
        highScoreTable.setEnabled(false);
        highScoreTable.setPreferredSize(new Dimension(400, 160));
        highScoreTable.setMaximumSize(new Dimension(400, 160));
    }

    private void initToggleButton() {
        toggleButtonForDB = new JToggleButton("Toggle DB");
        toggleButtonForDB.addActionListener(e -> {
            GameGUI.db.swapDBConnection();
            // initHighScoreTable();
            getDataAndAddToArray();
            updateDBtext();
            repaint();
            revalidate();
        });
    }

    private void initTextField() {
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 20));
        nameField.setText("Enter your name here (must change to save)");
    }

    private void initSubmitButton() {
        submitButton = new JButton("Submit score");
        submitButton.addActionListener(e -> {
            if (nameField.getText() != null && !nameField.getText().equals("") && !nameField.getText().equals("Enter your name here (must change to save)") && didSubmit == false) {               
                 setAndSendData(nameField.getText());
                getDataAndAddToArray();
                didSubmit = true;
                //System.out.println("hey");
            }else{
                //System.out.println("nope");

            }
        });
    }

    private void getDataAndAddToArray() {
        ArrayList<Data> highScoresData;
        dataArray = new Object[10][JTableColumNames.length];
        highScoresData = GameGUI.db.selectDataFromScores(10);
        for (int index = 0; index < highScoresData.size(); index++) {
            dataArray[index][0] = highScoresData.get(index).getName();
            dataArray[index][1] = highScoresData.get(index).getScore();
            dataArray[index][2] = highScoresData.get(index).getGameType();
        }
        tableModel.setDataVector(dataArray, JTableColumNames);
        // for (Object[] row : dataArray) {
        // System.out.println("Name: " + row[0] + ", Score: " + row[1] + ", GameType: "
        // + row[2]);
        // }
        highScoreTable.repaint();
        highScoreTable.revalidate();
        repaint();
        revalidate();
    }

    private void setAndSendData(String name) {
        data = new Data(score.getScore(), score.getGameType(), name);
        GameGUI.db.insertDataIntoUsers(data);
        GameGUI.db.insertDataIntoScores(data);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BACKGROUND_IMAGE, 0, 0, getWidth(), getHeight(), this);
        
    }
}
