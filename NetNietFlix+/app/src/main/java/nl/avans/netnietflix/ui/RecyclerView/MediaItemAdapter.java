package nl.avans.netnietflix.ui.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.ui.DetailActivity;
import nl.avans.netnietflix.R;
import nl.avans.netnietflix.domain.MediaItem;

public class MediaItemAdapter extends RecyclerView.Adapter<MediaItemViewHolder> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    private List<MediaItem> mediaItems;
    private List<MediaItem> mediaItemsOriginal;
    private Context context;

    public MediaItemAdapter(Context context) {
        //De lijst met mediaItems en de context (hier de Mainclass)
        this.context = context;
        mediaItems = new ArrayList<MediaItem>();
        mediaItemsOriginal = new ArrayList<MediaItem>();
        Log.d(TAG,"Constructor is called");
    }

    @NonNull
    @Override
    public MediaItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //De methode die aangeroepen wordt als de viewholder wordt gemaakt
        Log.d(TAG,"onCreateViewholder is called");
        Context context = parent.getContext();
        //Het id van het listitem wordt opgevraagd en "gemaakt" dus echt gecreate
        int layoutIdForListItem = R.layout.mediaitem_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent,shouldAttachToParentImmediately);
        return new MediaItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaItemViewHolder holder, int position) {
        //Hier wordt de data in de viewholder gezet
        Log.d(TAG,"onBindViewHolder is called");
        MediaItem mediaItem = mediaItems.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Als er op de viewholder geklikt wordt, wordt de gebruiker doorgestuurd naar de detailpagina
                //MediaItem wordt hier gecast als Serializable om hem te kunnen passen met de intent.
                Log.d(TAG,"onClick is called");
                DetailActivity detailActivity = new DetailActivity();
                DataManager dataManager = new DataManager();
                dataManager.getMediaItemDetails(detailActivity,mediaItem.getId());
                dataManager.getReviewForMovieId(detailActivity,mediaItem.getId());
                Intent intent = new Intent(context,detailActivity.getClass());
                intent.putExtra(INTENT_EXTRA_MEDIA_ITEM, (Serializable) mediaItems.get(position));
                context.startActivity(intent);
            }
        });
        if(mediaItem.getImgLink() != null){
//            holder.mediaListItemImage.setImageResource(R.drawable.not_found);
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + mediaItem.getImgLink()).resize(350,500).into(holder.mediaListItemImage);
        } else{
            holder.mediaListItemNoImageText.setText(mediaItem.getTitle());
        }
    }
    public void setMediaItems(List<MediaItem> mediaItems){
        Log.d(TAG, "setMovieList");
        this.mediaItems.clear();
        this.mediaItems.addAll(mediaItems);
        this.mediaItemsOriginal.addAll(mediaItems);
        this.notifyDataSetChanged();
    }

    //Hier de filters
//    public void filterOnStatus(String query) {
//        Log.d(TAG,"filterOnStatus has been called");
//        if (!query.isEmpty()) {
//            if(!mediaItems.equals(mediaItemsOriginal)){
//                mediaItems.clear();
//                mediaItems.addAll(mediaItemsOriginal);
//            }
//            ArrayList<MediaItem> filteredList = new ArrayList<>();
//            query = query.toLowerCase();
//            for (MediaItem character : mediaItems) {
//                if (character.getStatus().toLowerCase().contains(query)) {
//                    filteredList.add(character);
//                }
//            }
//            mediaItems.clear();
//            mediaItems.addAll(filteredList);
//            this.notifyDataSetChanged();
//        }
//    }
//    public void resetmediaItemsInRecyclerView(){
//        Log.d(TAG,"resetmediaItemsInRecyclerView has been called");
//        if(!mediaItems.equals(mediaItemsOriginal)){
//            mediaItems.clear();
//            mediaItems.addAll(mediaItemsOriginal);
//            this.notifyDataSetChanged();
//        }
//    }


    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount is called");
        return mediaItems.size();
    }
}
