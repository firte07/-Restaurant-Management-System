package BusinessLayer;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {
    private String nume;
    private int pret;

    public BaseProduct(String name, int pret) {
        this.nume = name;
        this.pret = pret;
    }

    public String getName() {
        return nume;
    }

    public int getPret() {
        return pret;
    }

    public void setNume(String name) {
        this.nume = name;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public void setDenumire(String d) {
        this.nume=d;
    }

    public int computePrice() {
        return pret;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}
