package kaappo.lista;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private static List<ShoppingList> lists = new ArrayList<>();


    private List<ShoppingListItem> items;
    private int ID;
    private String name;
    private long timeCreated;


    public ShoppingList(String name, List<ShoppingListItem> items) {
        if (items instanceof ArrayList) {
            this.items = items;
            this.ID = getFreeID();
            this.name = name;
            this.timeCreated = System.currentTimeMillis();
        }
    }

    public boolean add (ShoppingListItem item) {
        this.items.add(item);
        return true;
    }

    private static int getFreeID () {
        int freeID = 0;

        for (ShoppingList i : ShoppingList.lists) {
            if ( i.getID() > freeID ) {
                freeID = i.getID();
            }
        }

        return freeID + 1;
    }

    public int getID() {
        return ID;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public static ShoppingList getExampleList () {
        List<ShoppingListItem> items = new ArrayList<>();

        items.add(new ShoppingListItem("laktoositonta maitoa", "1 L", "maito"));
        items.add(new ShoppingListItem("sisäfilettä", "1 kg", "liha"));
        items.add(new ShoppingListItem("oltermannia", "1 pkt", "juusto"));
        items.add(new ShoppingListItem("edamia", "500 g", "juusto"));
        items.add(new ShoppingListItem("jauhelihaa", "400 g", "liha"));
        items.add(new ShoppingListItem("suklaata", "1 levy", "makeiset"));

        return new ShoppingList("Kauppalista", items);
    }

    public long getTimeCreated() {
        return timeCreated;
    }
}
