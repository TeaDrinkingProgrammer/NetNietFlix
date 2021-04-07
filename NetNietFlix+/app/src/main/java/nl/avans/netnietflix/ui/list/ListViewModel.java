package nl.avans.netnietflix.ui.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Locale;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.domain.MediaItemList;
import nl.avans.netnietflix.repository.API.AllMediaItemListsController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;

public class ListViewModel extends ViewModel implements AllMediaItemListsController.MediaItemListListener {

    private MutableLiveData<List<MediaItemList>> mediaItemLists;
    private final String TAG = this.getClass().getSimpleName();
    private DataManager dataManager;


    public ListViewModel() {
        dataManager = new DataManager();
    }
    public LiveData<List<MediaItemList>> getMediaItemLists(){
        Log.d(TAG,"getMediaItems is executed");
        if(mediaItemLists == null){
            mediaItemLists = new MutableLiveData<>();
            loadMediaItemLists(this);
        }
        return mediaItemLists;
    }
    private void loadMediaItemLists(AllMediaItemListsController.MediaItemListListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        dataManager.getMediaItemLists(listener);
    }

    @Override
    public void onMediaItemListsAvailable(List<MediaItemList> mediaItemLists) {
        this.mediaItemLists.setValue((List<MediaItemList>) mediaItemLists);
    }
}