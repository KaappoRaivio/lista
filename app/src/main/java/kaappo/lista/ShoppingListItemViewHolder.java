package kaappo.lista;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ShoppingListItemViewHolder extends RecyclerView.ViewHolder {
    EditText name;
    Spinner group;
    EditText amount;
    CheckBox checked;
    ImageButton delete;

    public ShoppingListItemViewHolder (View v) {
        super(v);

        this.name = (EditText) v.findViewById(R.id.name);
        this.group = (Spinner) v.findViewById(R.id.group);
        this.amount = (EditText) v.findViewById(R.id.amount);
        this.checked = (CheckBox) v.findViewById(R.id.check);
        this.delete = (ImageButton) v.findViewById(R.id.delete);
    }
}
