package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private int ID;
    private int nrTable;

    public Order(int ID, int nrTable) {
        this.ID = ID;
        this.nrTable = nrTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return ID == order.ID &&
                nrTable == order.nrTable;
    }

    public int hashCode(){
        return this.ID;
    }

    public int getID() {
        return ID;
    }

    public int getNrTable() {
        return nrTable;
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNrTable(int nrTable) {
        this.nrTable = nrTable;
    }
}
