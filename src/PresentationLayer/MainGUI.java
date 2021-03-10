package PresentationLayer;

import BusinessLayer.Restaurant;
import Main.MainClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainGUI extends JFrame {
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton buttonAdministrator = new JButton("Administrator");
    private JButton buttonWaiter = new JButton("Waiter");
    private JButton ok = new JButton("SAVE");
    private JLabel text = new JLabel("Alege functia");
    private Restaurant res;
    private static final long serialVersionUID = 1L;

    public MainGUI(Restaurant r){
        res=r;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(text, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(buttonAdministrator, c);
        buttonAdministrator.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame = new AdministratorGUI(res);
                frame.setTitle("Administrator");
                frame.pack();
                frame.setVisible(true);

            }
        });

        c.gridx = 0;
        c.gridy = 2;
        pane.add(buttonWaiter, c);
        buttonWaiter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JFrame frame = new WaiterGUI(res);
                frame.setTitle("Waiter");
                frame.pack();
                frame.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        pane.add(ok, c);
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                try {
                   MainClass.setChange(res,res.getNrOrders());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JFrame frameChef = new Chef();
        frameChef.setTitle("Chef");
        frameChef.setSize(200,200);
        frameChef.setVisible(true);
        res.addObserver((java.util.Observer) frameChef);

        this.add(pane);
    }
}
