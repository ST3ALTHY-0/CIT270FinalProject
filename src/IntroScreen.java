// package finalProject.src;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.FlowLayout;
// import java.awt.LayoutManager;
// import java.awt.event.ActionListener;

// import javax.swing.*;


// public class IntroScreen extends JPanel{
//     GameGUI flip;
//     JLabel info;
//     private JButton okButton;

//     public IntroScreen(GameGUI flip){
//         this.flip = flip;
//         initializeIntroScreen();
//     }

//     public void initializeIntroScreen(){
//         setBackground(Color.LIGHT_GRAY);
//         setBounds(200, 200, 400, 300);
//         setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
//         setLayout(new BorderLayout());

//         okButton = new JButton("OK");
//         okButton.addActionListener(e -> flip.showGameMenu());
//         info = new JLabel("Intro Screen: Give some info about the game here");
//         add(info);
       
        
//        JPanel buttonPanel = new JPanel();
//         buttonPanel.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER));
//         buttonPanel.setBackground(Color.LIGHT_GRAY);
//         buttonPanel.add(okButton);


//         add(buttonPanel, BorderLayout.SOUTH);

//     }
    
// }
