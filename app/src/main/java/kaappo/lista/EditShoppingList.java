package kaappo.lista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class EditShoppingList extends AppCompatActivity {

    ShoppingListItemAdapter adapter;
    ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        DatabaseHandler databaseHandler = DatabaseHandler.getInstances().get(0);
        int ShoppingListID = getIntent().getIntExtra("ID", -1);

        System.out.println("ID: " + ShoppingListID);


        shoppingList = databaseHandler.openShoppingListByID(ShoppingListID);
        RecyclerView rv = (RecyclerView) findViewById(R.id.items);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingListItemAdapter(shoppingList.getItems());
        rv.setAdapter(adapter);

        EditText title = (EditText) findViewById(R.id.title);
        title.setText(shoppingList.getName());
    }

    public void newItem (View v) {

        adapter.items.add(new ShoppingListItem("", "", ""));
        adapter.notifyDataSetChanged();

    }

    private ShoppingList getShoppingListInformationFromLayout () {
        List<ShoppingListItem> kamat = adapter.getItems();
        EditText editText = (EditText) findViewById(R.id.title);
        String title = editText.getText().toString();

        return new ShoppingList(title, kamat);
    }

    public void saveShoppingList (View v) {
        ShoppingList shoppingList = getShoppingListInformationFromLayout();
        DatabaseHandler.getInstances().get(0).saveShoppingList(shoppingList);

        DatabaseHandler.getInstances().get(0).deleteShoppingListByID(this.shoppingList.getID());

        startActivity(new Intent(this, MainActivity.class));

    }

}
