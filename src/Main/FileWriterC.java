package Main;

import java.io.File;
import java.io.IOException;

public class FileWriterC {
    private File myObjCreate =null;
    private java.io.FileWriter myObjWrite = null;

    public void write(int pretTotal, int tableNr, String rezultat, int nrBill){
        try {
            myObjCreate = new File("D:\\PT2020\\Assignment4_RestaurantManagementSystem\\Bill"+nrBill+".txt");
            myObjCreate.createNewFile();
            myObjWrite = new java.io.FileWriter("D:\\PT2020\\Assignment4_RestaurantManagementSystem\\Bill"+nrBill+".txt");
            myObjWrite.write("La masa cu numarul: "+tableNr + " au fost comandate produsele: " + rezultat +
                    "\r\nPretul total fiind de "+ pretTotal);
            myObjWrite.write("\r\n");
            myObjWrite.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nu pot creea la write");
        }
    }
}
