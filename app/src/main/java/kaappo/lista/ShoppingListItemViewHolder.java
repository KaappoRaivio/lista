package kaappo.lista;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

public class ShoppingListItemViewHolder extends RecyclerView.ViewHolder {
    EditText name;
    EditText group;
    EditText amount;
    CheckBox checked;

    public ShoppingListItemViewHolder (View v) {
        super(v);

        this.name = (EditText) v.findViewById(R.id.name);
        this.group = (EditText) v.findViewById(R.id.group);
        this.amount = (EditText) v.findViewById(R.id.amount);
        this.checked = (CheckBox) v.findViewById(R.id.check);
    }
}
