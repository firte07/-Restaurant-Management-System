Salut,
Cand rulezi programul in lista de produse vei gasi deja apa (pret 5), lamaie(pret 1), limonada(apa+lamaie, pret 6), iar in meniu gasesti apa si limonada.
Cand rulezi, poti alege fereastra in care vrei sa lucrezi.
Pentru Administrator:
    1. Add new menu - adauga un produs in meniu (produsul trebuie sa fie in lista de produse a restaurantului)
    2. View all menu - vizualizezi tot meniul
    3. Delete menu - stergi produs din meniu
    4. Edit menu - primul text field e pentru numele produsului actual
                 - al doilea text field e folosit pentru a schimba numele unui produs
                 - al treilea e folosit doar pentru composite product, pentru a sterge un base product 
                            Ex: in primul text field scrii "limonada", iar in al treilea "apa", dupa edit, limonada nu va mai contine apa
    5. Add new product - primul text field e folosit pentru nume
                       - al doilea e folosit doar daca e un produs de baza, iar aici se introduce pretul acestuia
                       - al treilea e folosit doar de composite product, si se scrie in el numele base productului pe care vrei sa-l contina
                            Ex: in primul text field scrii "limonada", iar in al treilea "apa", apesi "add new product", dupa scrii in al treilea "lamaie",
                                apesi pe "add new producr" si limonada va contine si apa si lamaie, ATENTIE! Produsul e adaugat in lista de produse, nu in meniu,
                                pentru adaugare in meniu scrii in text ul de sub butonul de add menu "limonada" si apesi pe buton

Pentru Waiter:
    1. Add new menu in order - primul text field e pentru numele produsului, al doilea pentru masa la care se face comanda (se pot adauga mai multe produse, iar dupa se apasa pe compute bill)
    2. Compute bill - genereaza nota in format .txt pentru numarul mesei curente
    3. View menu - vizualizare meniu
    4. View all orders - vizualizare comenzi

Butonul "SAVE" are rolul de a serializa toate noile modificari. Inainte sa apesi Close Button, apasa OK, daca doresti sa salvezi modificarile
                                