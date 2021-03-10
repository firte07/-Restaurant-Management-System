package BusinessLayer;

import java.io.IOException;

public interface Waiter {
    public void add(String a); //adauga elemente
    public void vizualizare() throws IOException; //viz meniul
    public void bill(String c); //compute price + generarea facturii
    public void orders(); //aici vad toate comenzile
}
