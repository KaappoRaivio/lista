package kaappo.lista;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ShoppingListViewHolder extends RecyclerView.ViewHolder {
    CardView card;
    RelativeLayout wrapper;
    TextView title;
    TextView timeCreated;
    ImageButton delete;
    ImageButton edit;

    ShoppingListViewHolder (View view) {
        super(view);

        this.card = view.findViewById(R.id.card);
        this.wrapper = view.findViewById(R.id.wrapper);
        this.title = view.findViewById(R.id.title);
        this.timeCreated = view.findViewById(R.id.time);
        this.delete = view.findViewById(R.id.ImageButton01);
        this.edit = view.findViewById(R.id.edit);

    }
}
