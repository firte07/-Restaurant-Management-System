package PresentationLayer;
import BusinessLayer.*;
import BusinessLayer.MenuItem;
import Main.FileWriterC;
import Main.MainClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class WaiterGUI extends JFrame implements Waiter {
    private static final long serialVersionUID = 1L;
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton buttonOrder = new JButton("add new menuitem in order");
    private JButton buttonView = new JButton("view all orders");
    private JButton buttonCompute = new JButton("compute bill");
    private JButton buttonViewMenu = new JButton("view menu");
    private JTextField textProdusAdauga = new JTextField(20);
    private JTextField textNrMasa = new JTextField(20);
    private JLabel inex = new JLabel("");
    private Restaurant res;
    private List<MenuItem> listaComanda ;
    private static int nrBill=0;
    private List<Order> allOrders = new ArrayList<Order>();
    private List<Integer> preturi = new ArrayList<Integer>();
    private CompositeProduct compositeProduct =new CompositeProduct(""); //pt cook
    private JLabel bucatar = new JLabel("");
    private int newList=0;
    private FileWriterC fw = new FileWriterC();
    public WaiterGUI(Restaurant r) {
        nrBill=r.getNrBill();
        res=r;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(buttonOrder, c);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(textProdusAdauga, c);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(textNrMasa, c);
        buttonOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String denumire = textProdusAdauga.getText();
                add(denumire);
                System.out.println(listaComanda);
            }
        });


        c.gridx = 1;
        c.gridy = 0;
        pane.add(buttonView, c);

        c.gridx = 1;
        c.gridy = 1;
        pane.add(inex, c);
        buttonView.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                allOrders();
            }
        });

        c.gridx = 2;
        c.gridy = 0;
        pane.add(buttonCompute, c);
        buttonCompute.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                res.mustMenuItem(listaComanda);
                bill(textNrMasa.getText());
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        pane.add(bucatar, c);

        c.gridx = 3;
        c.gridy = 0;
        pane.add(buttonViewMenu, c);
        buttonViewMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                    vizualizare();
            }
        });

        this.add(pane);
    }

    @Override
    public void add(String denumire) {
        if(newList==0){
            newList=5;
            listaComanda=new ArrayList<>();
        }
        int i=-1;
        int ok=0;
        List<MenuItem> toateProdusele = res.getMenu();
        for(MenuItem produs: toateProdusele) {
            i++;
            if (produs.getName().equals(denumire)){
                listaComanda.add(toateProdusele.get(i));
                ok++;
                inex.setText("");
                bucatar.setText("");
                if(produs.getClass()==compositeProduct.getClass()) {
                    res.cook();
                }
            }
        }
        if(ok==0){
            inex.setText("Produs inexistent");
        }
    }

    @Override
    public void vizualizare(){
        JFrame tableFrame = new JFrame();
        String data[][] = new String[100][2];
        int i=0;
        for(MenuItem produs:res.getMenu()) {
            data[i][1] = produs.computePrice()+"";
            data[i][0] = produs.getName();
            i++;
        }
        String column[]={"DENUMIRE","PRET"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        tableFrame.setSize(400,400);
        tableFrame.add(sp);
        tableFrame.setVisible(true);
    }

    @Override
    public void bill(String tableNr) {
        nrBill++;
        int pretTotal=0;
        String rezultat="";
        for(MenuItem produs: listaComanda) {
            pretTotal += produs.computePrice();
            rezultat += produs.getName();
            rezultat += " ";
        }
        Order o = new Order(nrBill,Integer.parseInt(tableNr));
        res.addOrder(o,listaComanda);
        allOrders.add(o);
        preturi.add(pretTotal);
        fw.write(pretTotal,Integer.parseInt(tableNr),rezultat,nrBill);
    }

    public void allOrders(){
        JFrame tableFrame = new JFrame();
        String data[][] = new String[100][3];
        Map<Order, List<MenuItem>> buff = res.getOrders();
        int i=0;
            for (List<MenuItem> comanda : buff.values()) {
                String r = "";
                for (MenuItem m : comanda) {
                    r += m.getName();
                    r += " ";
                }
                data[i][2] = r;
                i++;
            }
        i=0;
        for (Order key : res.getOrders().keySet() )
        {
            data[i][0] = key.getID()+"";
            data[i][1] = key.getNrTable()+"";
            i++;
        }
        newList=0;
        String column[]={"ID Order","Nr. Table","MenuItems"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        tableFrame.setSize(400,400);
        tableFrame.add(sp);
        tableFrame.setVisible(true);
    }

    @Override
    public void orders() {
        JFrame tableFrame = new JFrame();
        String data[][] = new String[100][3];
        int i=0;
        for(Order order: allOrders) {
            data[i][0] = order.getID()+"";
            data[i][1] = order.getNrTable()+"";
            i++;
        }
        i=0;
        for(int pret: preturi) {
            data[i][2] = pret+"";
            i++;
        }
        String column[]={"ID Order","Nr. Table", "Price"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        tableFrame.setSize(400,400);
        tableFrame.add(sp);
        tableFrame.setVisible(true);
    }
}
