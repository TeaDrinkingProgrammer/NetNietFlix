package nl.avans.netnietflix.ui.list;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.repository.API.AllMediaItemListsController;
import nl.avans.netnietflix.repository.API.RemoveListController;
import nl.avans.netnietflix.ui.detailList.DetailListActivity;

public class MediaItemListAdapter extends RecyclerView.Adapter<MediaItemListViewHolder> implements Serializable, AllMediaItemListsController.MediaItemListListener,RemoveListController.RemoveListListener {
    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    private List<MediaItemList> MediaItemLists;
    private List<MediaItemList> MediaItemListsOriginal;
    private Context context;
    private DataManager dataManager;

    public MediaItemListAdapter(Context context) {
        //De lijst met MediaItemLists en de context (hier de Mainclass)
        this.context = context;
        MediaItemLists = new ArrayList<MediaItemList>();
        MediaItemListsOriginal = new ArrayList<MediaItemList>();
        dataManager = new DataManager();
        Log.d(TAG,"Constructor is called");
    }

    @NonNull
    @Override
    public MediaItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //De methode die aangeroepen wordt als de viewholder wordt gemaakt
        Log.d(TAG,"onCreateViewholder is called");
        Context context = parent.getContext();
        //Het id van het listitem wordt opgevraagd en "gemaakt" dus echt gecreate
        int layoutIdForListItem = R.layout.list_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent,shouldAttachToParentImmediately);
        return new MediaItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaItemListViewHolder holder, int position) {
                    //Hier wordt de data in de viewholder gezet
                    Log.d(TAG,"onBindViewHolder is called");
                    MediaItemList mediaItemListListItem = MediaItemLists.get(position);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Als er op de viewholder geklikt wordt, wordt de gebruiker doorgestuurd naar de detailpagina
                            //MediaItemList wordt hier gecast als Serializable om hem te kunnen passen met de intent.
                            Log.d(TAG,"onClick is called");
                            Intent intent = new Intent(context, DetailListActivity.class);
                            intent.putExtra("id",mediaItemListListItem.getId());
                            context.startActivity(intent);
            }
        });
        class MediaItemListAdapterOnClickListener implements View.OnClickListener{
            AllMediaItemListsController.MediaItemListListener listener;
            RemoveListController.RemoveListListener listener2;
            public MediaItemListAdapterOnClickListener(AllMediaItemListsController.MediaItemListListener listener,RemoveListController.RemoveListListener listener2){
                this.listener = listener;
                this.listener2 = listener2;
            }
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick is called on deleteButton");
                if(mediaItemListListItem.getId() == 7088970){
                    String toast_msg = context.getResources().getString(R.string.list_cannot_remove);
                    Toast.makeText(context, toast_msg, Toast.LENGTH_SHORT).show();
                } else{
                    dataManager.removeList(listener2,mediaItemListListItem.getId());
                    String toast_msg = String.format("%s %s",mediaItemListListItem.getName(),context.getResources().getString(R.string.list_removed));
                    Toast.makeText(context, toast_msg, Toast.LENGTH_SHORT).show();
                }
            }
        }

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick was called on shareButton");
                //Share and show toast

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String shareSubject = "Wassup, check my awesome list.";
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareSubject);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "NetNietFlix+");
                context.startActivity(shareIntent);
                Toast.makeText(context, "Sharing list", Toast.LENGTH_SHORT).show();
            }
        });
        holder.deleteButton.setOnClickListener(new MediaItemListAdapterOnClickListener(this,this));
        holder.name.setText(mediaItemListListItem.getName());
        holder.numberOfItems.setText("Items "+ String.valueOf(mediaItemListListItem.getItemCount()));
    }
    public void setMediaItemLists(List<MediaItemList> MediaItemLists){
        Log.d(TAG, "setMovieList");
        this.MediaItemLists.clear();
        this.MediaItemLists.addAll(MediaItemLists);
        this.MediaItemListsOriginal.addAll(MediaItemLists);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount is called");
        return MediaItemLists.size();
    }

    @Override
    public void onMediaItemListsAvailable(List<MediaItemList> mediaItemLists) {
        this.setMediaItemLists(mediaItemLists);
    }

    @Override
    public void onPost(Boolean isSuccessful) {
        dataManager.getMediaItemLists(this);
    }
}
