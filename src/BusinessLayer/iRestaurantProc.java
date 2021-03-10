package BusinessLayer;

import java.util.List;

public interface iRestaurantProc {
    /** trebuie sa existe produse in restaurant
     * @pre !produse.isEmpty()
     */
    public void notEmpty(List<MenuItem> produse);

    /** trebuie sa existe comenzi
     * @post nrOrders!=0
     */
    public void mustOrder(int nrOrders);

    /** trebuie sa existe produse in order
     * @invariant !comanda.isEmpty()
     */
    public void mustMenuItem(List<MenuItem> comanda);
}
