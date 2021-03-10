package PresentationLayer;

import BusinessLayer.*;
import BusinessLayer.MenuItem;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorGUI extends JFrame implements Administrator {
    private static final long serialVersionUID = 1L;
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton buttonMenu = new JButton("add new menu");
    private JButton buttonProduct = new JButton("add new product");
    private JTextField textProdusInsert = new JTextField(20);
    private JTextField textProdusNume = new JTextField(20);
    private JTextField textProdusPretBase = new JTextField(20);    //daca e base product adaug pret, else adaug in composite
    private JTextField textProdusCompozitie = new JTextField(20);
    private JButton buttonView = new JButton("view all menu");
    private JButton buttonDelete = new JButton("delete menu");
    private JTextField textProdusDelete = new JTextField(20);
    private JButton buttonEdit = new JButton("edit menu");
    private JTextField textProdusEdit = new JTextField(20);
    private JTextField textProdusEditNume = new JTextField(20);
    private JTextField textProdusEditComposite = new JTextField(20);
    private JTextField textProdusInsertPret = new JTextField(20);
    private Restaurant res;
    private JLabel inexistent = new JLabel("");

    public AdministratorGUI(Restaurant res) {
        this.res=res;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(buttonMenu, c);

        c.gridx = 0;
        c.gridy = 1;
        pane.add(textProdusInsert, c);

        c.gridx = 0;
        c.gridy = 2;
        pane.add(inexistent, c);

        buttonMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String denumire = textProdusInsert.getText();
                create(denumire);
            }
        });

        c.gridx = 1;
        c.gridy = 0;
        pane.add(buttonView, c);
        buttonView.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                view();
            }
        });

        c.gridx = 2;
        c.gridy = 0;
        pane.add(buttonDelete, c);

        c.gridx = 2;
        c.gridy = 1;
        pane.add(textProdusDelete, c);
        buttonDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String denumire= textProdusDelete.getText();
                delete(denumire);
            }
        });


        c.gridx = 3;
        c.gridy = 0;
        pane.add(buttonEdit, c);

        c.gridx = 3;
        c.gridy = 1;
        pane.add(textProdusEdit, c);

        c.gridx = 3;
        c.gridy = 2;
        pane.add(textProdusEditNume, c);

        c.gridx = 3;
        c.gridy = 3;
        pane.add(textProdusEditComposite, c);
        buttonEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                edit();
            }
        });

        c.gridx = 4;
        c.gridy = 0;
        pane.add(buttonProduct, c);

        c.gridx = 4;
        c.gridy = 1;
        pane.add(textProdusNume, c);

        c.gridx = 4;
        c.gridy = 2;
        pane.add(textProdusPretBase, c);

        c.gridx = 4;
        c.gridy = 3;
        pane.add(textProdusCompozitie, c);

        buttonProduct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
               //caz 1 base product
                if(textProdusCompozitie.getText().isEmpty()){
                    addBaseProduct();
                }else{ //caz 2, composite product
                    addCCompositeProduct();
                }
            }
        });

        this.add(pane);
    }

    @Override
    public void create(String denumire) {
        int i=-1;
        int ok=0;
        List<MenuItem> toateProdusele = res.getListaProduse();
        for(MenuItem produs: toateProdusele) {
            i++;
            if (produs.getName().equals(denumire)) {
                res.addMenu(toateProdusele.get(i));
                ok++;
                inexistent.setText("");
            }
        }
        if(ok==0) {
            inexistent.setText("Produs inexistent");
        }        System.out.println(res.getMenu());
    }

    @Override
    public void delete(String denumire) {
        int i=-1;
        List<MenuItem> toateProdusele = res.getListaProduse();
        for(MenuItem produs: toateProdusele) {
            i++;
            if (produs.getName().equals(denumire))
                res.removeMenu(toateProdusele.get(i));
        }
        System.out.println(res.getMenu());
    }

    @Override
    public void edit() {
        //case 1, modificare nume
        if (textProdusEditComposite.getText().isEmpty()) {
            for (MenuItem produs : res.getListaProduse()) {
                if (produs.getName().equals(textProdusEdit.getText())) {
                    produs.setDenumire(textProdusEditNume.getText());
                }
            }
        } else { //case 2, modificam compozitia
            CompositeProduct aux = new CompositeProduct("");
            List<MenuItem> buff = res.getListaProduse();
            int c=0,ok=0;
            BaseProduct auxBase = new BaseProduct("",-1);
            for (MenuItem produs : buff) {
                if (produs.getName().equals(textProdusEdit.getText())) {
                    aux=(CompositeProduct)produs;
                    for(MenuItem base : buff){
                        if(base.getName().equals((textProdusEditComposite.getText()))){
                            c++;
                            auxBase=(BaseProduct)base;
                        }
                    }
                }
            }if(c!=0){
                ok=res.removeSpecififcProduct(aux);
                aux.removeProduct(auxBase);
                res.addProdus(aux);
                if(ok==1)
                    res.addMenu(aux);
            }
        }
    }

    public void view(){
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

    public void addBaseProduct(){
        BaseProduct b = new BaseProduct(textProdusNume.getText(),Integer.parseInt(textProdusPretBase.getText()));
        res.addProdus(b);
    }

    public void addCCompositeProduct() {
        int am = 0;
        CompositeProduct aux = new CompositeProduct("");
        for (MenuItem prod : res.getListaProduse()) {              //vad daca am compositeproduct deja
            if (prod.getName().equals(textProdusNume.getText())) {
                am++;
                for (MenuItem produsBase : res.getListaProduse())
                    if (produsBase.getName().equals(textProdusCompozitie.getText())) {
                        aux = (CompositeProduct) prod;
                        aux.addProduct(produsBase);
                    }
            }
        }
        if(am!=0) {
            res.removeProdus();
            res.addProdus(aux);
        }
        int ok=0;
        CompositeProduct c= new CompositeProduct("");
        if (am == 0) {
             c = new CompositeProduct(textProdusNume.getText());
            for (MenuItem produs : res.getListaProduse())
                if (produs.getName().equals(textProdusCompozitie.getText())) {
                    c.addProduct(produs);
                    ok++;
                }
        }
        if(ok!=0) {
            res.addProdus(c);
        }
    }
}


