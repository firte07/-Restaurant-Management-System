package BusinessLayer;

import java.io.Serializable;
import java.util.*;

/**
 * Clasa in care se se pastreaza principaleleinformatii, cum ar fi lista de produse din restaurant, lista meniului,
 * precum si toate comenzile efectuate
 */
public class Restaurant extends Observable implements Serializable, iRestaurantProc {
    private List<MenuItem> listaProduse;
    private Map<Order, List<MenuItem>> orders;
    private List<MenuItem> menu;
    private int nrOrders;
    private int nrBill;
    private static final long serialVersionUID = 1L;

    /**
     * Constructorul clasei are rolul de a initializa listele si numarul de comenzi
     */
    public Restaurant(){
        listaProduse=new ArrayList<>();
        menu=new ArrayList<>();
        orders = new HashMap<>();
        nrOrders = 0;
    }

    /**
     * Metoda seteaza numarul comenzilor, pentru a nu se suprascrie dupa serializare
     */
    public void setNrOrders() {
        this.nrOrders = 0;
    }

    /**
     * Metoda care adauga un produs in meniu
     * @param m produs ce va fi adaugat in meniu
     */
    public void addMenu(MenuItem m){
        menu.add(m);
    }

    /**
     * Metoda ce sterge un produs din meniu
     * @param m produs ce va fi sters din meniu
     */
    public void removeMenu(MenuItem m){
        menu.remove(m);
    }

    /**
     * Metoda ce adauga un produs in lista de produse
     * @param m produs care va fi adaugat
     */
    public void addProdus(MenuItem m){
        listaProduse.add(m);
    }

    /**
     * Metoda care sterge ultimul produs din lista de produse
     * Folosit pentru a nu se face dubluri de composite product la adaugare
     */
    public void removeProdus(){
        listaProduse.remove(listaProduse.size()-1);
    }

    /**
     * Metoda care sterge un anumit produs din meniu
     * @param m produsul care va fi sters
     * @return returneaza un int care arata daca produsul s-a gasit sau nu in lista
     */
    public int removeSpecififcProduct(MenuItem m){
        listaProduse.remove(m);
        int ok=0;
        for(MenuItem produs : menu){
            if(produs.getName().equals(m.getName()))
                ok++;
        }
        if(ok!=0) {
            menu.remove(m);
        }
        return ok;
    }

    /**
     * Metoda care adauga o comanda
     * @param o cheia map-ului
     * @param m valorile din map
     */
    public void addOrder(Order o,List<MenuItem> m){
        orders.put(o,m);
        nrOrders++;
    }

    /**
     * Metoda care returneaza toate comenzile
     * @return map-ul in care sunt stocate informatiile despre comenzi
     */
    public Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    /**
     * Metoda care returneaza lista de produse
     * @return lista de produse
     */
    public List<MenuItem> getListaProduse() {
        return listaProduse;
    }

    /**
     * Metoda care returneaza lista meniului
     * @return lista meniului
     */
    public List<MenuItem> getMenu() {
        return menu;
    }

    /**
     * Metoda care afiseaza informatiile din restaurant
     * @return informatii despre restaurant
     */
    @Override
    public String toString() {
        return "Restaurant{" +
                "orders=" + orders +
                ", menu=" + menu +
                '}';
    }

    /**
     * Metoda care notifica bucatarul cand are de gatit
     */
    public void cook(){
        setChanged();
        notifyObservers(true);
    }

    /**
     * Preconditie, unde se asigura ca restaurantul are o baza de produse
     * @param produse lista de produse din restaurant
     */
    @Override
    public void notEmpty(List<MenuItem> produse) {
        assert produse.size()>0 : "Stoc insuficient";
    }

    /**
     * Postconditie, care asigura ca s-au facut comenzi in restaurant
     * @param nrOrders numarul de comenzi efectuate
     */
    @Override
    public void mustOrder(int nrOrders) {
        assert nrOrders!=0 : "Faliment";
    }

    /**
     * Invariant care asigura ca o comanda nu se poate face fara produse
     * @param comanda lista de produse comandate
     */
    @Override
    public void mustMenuItem(List<MenuItem> comanda) {
        assert comanda.size()>0 : "Comanda nula";
    }

    /**
     * Metoda care returneaza numarul de comenzi
     * @return numarul de comenzi
     */
    public int getNrOrders() {
        return nrOrders;
    }

    /**
     * Metoda care returneaza numarul facturii
     * @return numarul facturii
     */
    public int getNrBill() {
        return nrBill;
    }

    /**
     * Metoda care seteaza numarul facturii
     * @param nrBill numarul facturii
     */
    public void setNrBill(int nrBill) {
        this.nrBill = nrBill;
    }
}
