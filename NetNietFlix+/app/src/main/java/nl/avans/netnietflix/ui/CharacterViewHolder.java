package nl.avans.netnietflix.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nl.avans.netnietflix.R;


public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = this.getClass().getSimpleName();
    public TextView characterListItemName;
    public ImageView characterListItemImage;
    public TextView characterListItemNickName;
    public TextView characterListItemStatus;
    public CharacterViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG,"Constructor is called");
        characterListItemImage = itemView.findViewById(R.id.character_list_item_image);
        characterListItemName = itemView.findViewById(R.id.character_list_item_name_tv);
        characterListItemNickName = itemView.findViewById(R.id.character_list_item_nickname_tv);
        characterListItemStatus = itemView.findViewById(R.id.character_list_item_status_tv);
    }

    }
