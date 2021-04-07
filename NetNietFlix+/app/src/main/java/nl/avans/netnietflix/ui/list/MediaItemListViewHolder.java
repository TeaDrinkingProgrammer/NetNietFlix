package nl.avans.netnietflix.ui.list;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nl.avans.netnietflix.R;


public class MediaItemListViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = this.getClass().getSimpleName();
    public TextView name;
    public TextView description;
    public TextView numberOfItems;
    public MediaItemListViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG,"Constructor is called");
        name = itemView.findViewById(R.id.list_list_item_name);
        //description = itemView.findViewById(R.id.list_list_item_description);
        numberOfItems = itemView.findViewById(R.id.list_list_item_num);
    }
}
