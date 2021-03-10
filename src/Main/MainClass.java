package Main;

import BusinessLayer.*;
import PresentationLayer.MainGUI;
import javax.swing.*;
import java.io.*;
   public class MainClass implements  Serializable{
    private static final long serialVersionUID = 1L;
    private static String numeFisier;

    public static void setChange(Restaurant res,int nrOrders) throws IOException {
     res.setNrBill(nrOrders+res.getNrBill());
     res.mustOrder(nrOrders);
     FileOutputStream fileout = new FileOutputStream (numeFisier);
     ObjectOutputStream out = new ObjectOutputStream(fileout);
     out.writeObject(res); // Method for serialization of object
     out.close();
     fileout.close();
    }
//"D:\\PT2020\\Assignment4_RestaurantManagementSystem\\restaurant.ser"
    public static void main(String args[]) throws IOException, ClassNotFoundException {
     Restaurant res = new Restaurant();
     numeFisier=args[0];
     FileInputStream file = new FileInputStream(numeFisier);
     ObjectInputStream in = new ObjectInputStream(file);
     res = (Restaurant) in.readObject(); // Method for deserialization of object
     in.close();
     file.close();
     res.setNrOrders();
     res.notEmpty(res.getListaProduse());
     JFrame frame = new MainGUI(res);
     frame.setTitle("Main");
     frame.setSize(250,150);
     frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     frame.setVisible(true);
    }
}
