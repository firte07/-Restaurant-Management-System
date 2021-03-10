package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Chef extends JFrame  implements Observer {
    private JLabel cook = new JLabel("You don't need to cook");
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton button = new JButton("Do I need to cook?");
    private boolean cookingTime = false;

    public Chef(){
        c.gridx = 0;
        c.gridy = 1;
        pane.add(cook, c);
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                if(cookingTime)
                    cook.setText("Time to cook");
                else{
                    cook.setText("You don't need to cook");
                }
            }
        });
       this.add(pane);
    }

    @Override
    public void update(Observable o, Object arg) {
        cookingTime=(boolean)arg;
    }

    public void setCook(){
        cookingTime=false;
    }
}
