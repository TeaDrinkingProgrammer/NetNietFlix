package nl.avans.netnietflix.ui.list;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.avans.netnietflix.R;
import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.ui.detailList.DetailListViewModel;

public class MediaItemListAdapter extends RecyclerView.Adapter<MediaItemListViewHolder> implements Serializable {
    private final String TAG = getClass().getSimpleName();
    final static String INTENT_EXTRA_MEDIA_ITEM = "media_item";
    private List<MediaItemList> MediaItemLists;
    private List<MediaItemList> MediaItemListsOriginal;
    private Context context;

    public MediaItemListAdapter(Context context) {
        //De lijst met MediaItemLists en de context (hier de Mainclass)
        this.context = context;
        MediaItemLists = new ArrayList<MediaItemList>();
        MediaItemListsOriginal = new ArrayList<MediaItemList>();
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
        MediaItemList MediaItemList = MediaItemLists.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Als er op de viewholder geklikt wordt, wordt de gebruiker doorgestuurd naar de detailpagina
                //MediaItemList wordt hier gecast als Serializable om hem te kunnen passen met de intent.
                Log.d(TAG,"onClick is called");
                DetailListViewModel detailActivity = new DetailListViewModel();
                DataManager dataManager = new DataManager();
                dataManager.getMediaItemLists(detailActivity);
                Intent intent = new Intent(context,detailActivity.getClass());
                intent.putExtra(INTENT_EXTRA_MEDIA_ITEM, (Serializable) MediaItemLists.get(position));
                context.startActivity(intent);
            }
        });
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
}
