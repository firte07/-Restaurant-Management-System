package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable {
    private List<MenuItem> produse = new ArrayList<MenuItem>();
    private String denumire;
    private int pret;

    public CompositeProduct(String nume){
        denumire=nume;
    }

    public void addProduct(MenuItem prod){
            produse.add(prod);
    }

    public void removeProduct(MenuItem prod){
        produse.remove(prod);
    }

    public int computePrice() {
        int totalPrice=0;
        for(MenuItem b: produse){
            totalPrice+=b.computePrice();   //computePrice
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "denumire='" + denumire + '\'' +
                ", pret=" +  computePrice() +
                '}';
    }

    @Override
    public String getName(){
        return denumire;
    }

    @Override
    public void setPret(int p) {
        pret=p;
    }

    @Override
    public void setDenumire(String denu) {
        this.denumire=denu;
    }

    public int getPret(){return pret;}

}
