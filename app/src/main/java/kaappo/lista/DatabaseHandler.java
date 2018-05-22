package kaappo.lista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kaappo.lista.ShoppingList;

public class DatabaseHandler {
    private String name;
    private Context context;
    private SQLiteDatabase db;
    private int ID;

    private static List<DatabaseHandler> instances = new ArrayList<>();

    public DatabaseHandler (String name, Context context) {
        this.name = name;
        this.context = context;

        this.ID = getFreeID();

        instances.add(this);

    }

    public boolean createDatabase () {
        db = getContext().openOrCreateDatabase(getName(), Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + getName() + "(title TEXT, json TEXT, timeSaved TEXT, ID TEXT)");
        return true;
    }

    public boolean saveShoppingList (ShoppingList shoppingList) {
        Gson gson = new Gson();
        String json = gson.toJson(shoppingList);

        deleteShoppingListByID(shoppingList.getID());


        db.execSQL("INSERT INTO " + getName() + " VALUES('"
                + shoppingList.getName() + "', '"
                + json + "', '"
                + System.currentTimeMillis() + "', '"
                + shoppingList.getID() + "');");

        Toast.makeText(context, this.getAllShoppingLists().toString(), Toast.LENGTH_SHORT).show();
        return true;
    }

    public ShoppingList openShoppingListByID (int ID) {
        Cursor resultSet = db.rawQuery("SELECT * FROM " + getName() + " WHERE ID = '" + ID + "';", null);

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
            ShoppingList muppet = gson.fromJson(json, ShoppingList.class);
            //ID
            //ShoppingList temp = new ShoppingList(muppet.getName(), muppet.getItems());
            listat.add(muppet);

        }

        resultSet.close();

        return listat;
    }

    public void deleteShoppingListByID (int ID) {
        db.execSQL("DELETE FROM " + getName() + " WHERE ID = '" + ID + "';");
    }


    public String getName() {
        return name;
    }

    private Context getContext() {
        return context;
    }

    private static int getFreeID () {
        int biggest = 0;

        for (DatabaseHandler databaseHandler : instances) {
            if (databaseHandler.getID() > biggest) {
                biggest = databaseHandler.getID();
            }
        }

        return biggest + 1;
    }

    @Nullable
    public static DatabaseHandler findDatabaseHandlerByID (int ID) {
        int index = -1;

        for (int i = 0; i < instances.size(); i++) {
            if (instances.get(i).getID() == ID) {
                index = i;
                break;

            }
        }

        if (index != -1) {
            System.out.println("instance: " + instances.get(index).getName());
            return instances.get(index);
            //return instances.get(2);
        }
        else {
            System.out.println("findDatabaseHandlerByID: Invalid index " + ID + "!");
            return null;
        }

    }

    public int getID() {
        return ID;
    }

    public static List<DatabaseHandler> getInstances() {
        return instances;
    }
}
