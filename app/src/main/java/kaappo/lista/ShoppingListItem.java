package kaappo.lista;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingListItem {
    public static ArrayList<String> CATEGORIES = new ArrayList<>();

    private static List<ShoppingListItem> items = new ArrayList<>();

    private String name;
    private String amount;
    private int ID;
    private boolean collected;
    private String group;
    private Integer categoryIndex;

    public ShoppingListItem(String nimi, String maara, String ryhma) throws Throwable{

        this.name = nimi;
        this.amount = maara;
        this.ID = getFreeID();
        this.collected = false;
        this.group = ryhma;
        this.categoryIndex = getCategoryIndex(ryhma);
        if (categoryIndex == null) {
            throw new Throwable("Unknown category!");
        }

        ShoppingListItem.items.add(this);
    }

    public ShoppingListItem (String nimi, String maara, Integer categoryIndex) throws Throwable {
        this.name = nimi;
        this.amount = maara;
        this.ID = getFreeID();
        this.collected = false;
        this.group = ryhma;
        this.categoryIndex = getCategoryIndex(ryhma);
        if (categoryIndex == null) {
            throw new Throwable("Unknown category!");
        }
        if (MainActivity.getProductCategories().get(categoryIndex))

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

    @Nullable
    public static Integer getCategoryIndex (String value) {
        for (int i = 0; i < MainActivity.getProductCategories().size(); i++) {
            if (MainActivity.getProductCategories().get(i).equals(value)) {
                return i;
            }
        }
        return null;
    }

}
