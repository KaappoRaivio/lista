package kaappo.lista;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListItem {
    private static List<ShoppingListItem> items = new ArrayList<>();

    private String name;
    private String amount;
    private int ID;
    private boolean collected;
    private String group;

    public ShoppingListItem(String nimi, String maara, String ryhma) {
        this.name = nimi;
        this.amount = maara;
        this.ID = getFreeID();
        this.collected = false;
        this.group = ryhma;

        ShoppingListItem.items.add(this);
    }

    private static int getFreeID () {
        int freeID = 0;

        for (ShoppingListItem i : ShoppingListItem.items) {
            if ( i.getID() > freeID ) {
                freeID = i.getID();
            }
        }

        return freeID + 1;
    }

    public int getID () {
        return ID;
    }

    public String getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public static List<ShoppingListItem> getItems() {
        return items;
    }

    public boolean isCollected() {
        return collected;
    }

    public String getGroup() {
        return group;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

}
