package Window;

import bd.dbos.*;
import bd.daos.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.*;

public class LaunchPage implements ActionListener{
    JFrame frame = new JFrame("Menu");
    JPanel panel = new JPanel(new GridLayout(2,2,20,20));
    JLabel label = new JLabel("Menu", SwingConstants.CENTER);
    JButton shopping = new JButton("CRUD - Shopping");                                                                                                                             
    JButton sair = new JButton("Sair");
    
    
    public LaunchPage(){

        label.setFont(new Font("Bahnschrift",Font.BOLD,30));
        label.setFocusable(false);

        shopping.setFont(new Font("Bahnschrift",Font.BOLD,20));
        shopping.setFocusable(false);
        shopping.addActionListener(this);

        sair.setFont(new Font("Bahnschrift",Font.BOLD,20));
        sair.setFocusable(false);
        sair.addActionListener(this);
       
        panel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        panel.add(shopping);
        panel.add(sair);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==shopping)
        {
            
            cadShopping janela = new cadShopping();      
            frame.dispose();      
        }

        if(e.getSource()==sair)
        {
            System.exit(0);
        }
    }
}



// package Window;

// import bd.dbos.*;
// import bd.daos.*;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.*;

// import java.awt.*;

// public class LaunchPage implements ActionListener{
//     JFrame frame = new JFrame("Menu");
//     JPanel panel = new JPanel(new GridLayout(2,2));
//     JLabel label = new JLabel("Shop");
//     JButton shopping = new JButton("Shopping");                                                                                                                             
//     JButton sair = new JButton("Sair");
    
//     public LaunchPage(){

//         label.setFont(new Font("Bahnschrift",Font.PLAIN,20));
//         label.setFocusable(false);


//         shopping.setFont(new Font("Bahnschrift",Font.PLAIN,20));
//         shopping.setFocusable(false);
//         shopping.addActionListener(this);

//         sair.setFont(new Font("Bahnschrift",Font.PLAIN,20));
//         sair.setFocusable(false);
//         sair.addActionListener(this);
       
//         panel.add(shopping);
//         panel.add(sair);

//         frame.add(panel);

//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(1250,700);
//         frame.setVisible(true);

//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if(e.getSource()==shopping)
//         {
            
//             cadShopping janela = new cadShopping(); 
            
//         }

//         if(e.getSource()==sair)
//         {
//             System.exit(0);
//         }
//     }
// }