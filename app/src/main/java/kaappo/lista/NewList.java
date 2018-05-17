package kaappo.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class NewList extends AppCompatActivity {

    private ShoppingListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        EditText et = findViewById(R.id.title);
        et.setText(ShoppingList.getExampleList().getName());


        adapter = new ShoppingListItemAdapter(ShoppingList.getExampleList().getItems());
        RecyclerView rv = findViewById(R.id.items);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    public void newItem (View v) {
        adapter.items.add(new ShoppingListItem("", "", ""));
        adapter.notifyDataSetChanged();
    }
}