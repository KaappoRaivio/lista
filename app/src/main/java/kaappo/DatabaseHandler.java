package kaappo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import kaappo.lista.ShoppingList;

public class DatabaseHandler {
    private String name;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseHandler (String name, Context context) {
        this.name = name;
        this.context = context;
    }

    public boolean createDatabase () {
        db = getContext().openOrCreateDatabase(getName(), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS" + getName() + "(title TEXT, json TEXT, timeSaved TEXT, ID TEXT)");
        return true;
    }

    public boolean saveShoppingList (ShoppingList shoppingList) {
        
    }




    public String getName() {
        return name;
    }

    public Context getContext() {
        return context;
    }
}
