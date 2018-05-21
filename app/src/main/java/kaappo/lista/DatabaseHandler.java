package kaappo.lista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
        Gson gson = new Gson();
        String json = gson.toJson(shoppingList);


        db.execSQL("INSERT INTO " + getName() + " VALUES('"
                + shoppingList.getName() + "', '"
                + json + "', '"
                + System.currentTimeMillis() + "', '"
                + shoppingList.getID() + "');");

        return true;
    }

    public ShoppingList openShoppingListByID (int ID) {
        Cursor resultSet = db.rawQuery("SELECT * FROM " + getName() + "WHERE ID = '" + ID + "';", null);

        resultSet.moveToFirst();
        String json = resultSet.getString(resultSet.getColumnIndexOrThrow("json"));
        resultSet.close();

        Gson gson = new Gson();
        return  gson.fromJson(json, ShoppingList.class);
    }

    public List<ShoppingList> getAllShoppingLists () {
        List<ShoppingList> listat = new ArrayList<>();

        Cursor resultSet = db.rawQuery("SELECT * FROM'" + getName() + "';", null);
        resultSet.moveToFirst();

        Gson gson = new Gson();

        while (resultSet.moveToNext()) {
            String json = resultSet.getString(resultSet.getColumnIndexOrThrow("json"));
            listat.add(gson.fromJson(json, ShoppingList.class));

        }

        resultSet.close();

        return listat;
    }




    public String getName() {
        return name;
    }

    public Context getContext() {
        return context;
    }
}
