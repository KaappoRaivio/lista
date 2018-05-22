package kaappo.lista;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler databaseHandler = new DatabaseHandler("shoppinglists", this);
        databaseHandler.createDatabase();

        adapter = new ShoppingListAdapter(databaseHandler.getAllShoppingLists());

        RecyclerView rv = findViewById(R.id.lists);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }

    public void createNew(View v) {
        Intent intent = new Intent(this, NewList.class);
        startActivity(intent);
    }

    public void onDeleteButtonClick (View v) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstances().get(0);

        Bundle bundle = (Bundle) v.getTag();

        int shoppingListID = bundle.getInt("ID");
        int pos = bundle.getInt("pos");

        databaseHandler.deleteShoppingListByID(shoppingListID);

        adapter.entries.remove(pos);
        adapter.notifyItemRemoved(pos);
        adapter.notifyItemRangeChanged(pos, adapter.entries.size());
    }

    public void toasti(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
