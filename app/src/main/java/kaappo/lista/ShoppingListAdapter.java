package kaappo.lista;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.BufferUnderflowException;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListViewHolder> {
    List<ShoppingList> entries;

    ShoppingListAdapter (List<ShoppingList> entries) {
        this.entries = entries;
    }

    @Override
    public int getItemCount () {
        return this.entries.size();
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder (ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.main_item, viewGroup, false);
        return new ShoppingListViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ShoppingListViewHolder slvh, int position) {
        ShoppingList current = this.entries.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putInt("ID", current.getID());

        slvh.title.setText(current.getName());
        slvh.timeCreated.setText(String.valueOf(current.getTimeCreated()));
        slvh.wrapper.setTag(current.getID());
        slvh.edit.setTag(current.getID());
        slvh.delete.setTag(bundle);
        slvh.card.getLayoutParams().height = WRAP_CONTENT;
    }
}
