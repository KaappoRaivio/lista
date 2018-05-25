package kaappo.lista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemViewHolder> {
    List<ShoppingListItem> items;

    ShoppingListItemAdapter (List<ShoppingListItem> items) {
        this.items = items;
    }

    @Override
    public ShoppingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.item, viewGroup, false);

        return new ShoppingListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder (ShoppingListItemViewHolder vh, int position) {
        ShoppingListItem current = this.getItems().get(position);

        vh.amount.setText(current.getAmount());
        vh.name.setText(current.getName());
        vh.checked.setChecked(current.isCollected());
        vh.delete.setTag(position);
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemFromShoppingList(v);
            }
        });

    }

    private void deleteItemFromShoppingList (View v) {
        int position = (Integer) v.getTag();
        this.items.remove(position);

        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, this.items.size());

    }

    @Override
    public int getItemCount() {
        return this.getItems().size();
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }
}
