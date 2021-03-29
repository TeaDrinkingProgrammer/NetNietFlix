package nl.avans.netnietflix.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nl.avans.netnietflix.R;


public class MediaItemViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = this.getClass().getSimpleName();
    public ImageView mediaListItemImage;
    public MediaItemViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG,"Constructor is called");
        mediaListItemImage = itemView.findViewById(R.id.media_list_item_image);
    }
}
