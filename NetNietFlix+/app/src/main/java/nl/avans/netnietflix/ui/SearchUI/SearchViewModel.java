package nl.avans.netnietflix.ui.SearchUI;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Locale;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;

public class SearchViewModel extends ViewModel implements MediaItemControllerListener {

    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<List<MediaItem>> searchMediaItems = null;
    private DataManager dataManager;

    public SearchViewModel() {
        dataManager = new DataManager(Locale.getDefault().getLanguage());
    }

    public LiveData<List<MediaItem>> getSearchMediaItems(){
        Log.d(TAG,"getMediaItems is executed");
        if(searchMediaItems == null){
            searchMediaItems = new MutableLiveData<>();
        }
        return searchMediaItems;
    }
    public void loadSearchMediaItems(String query){
        localLoadSearchMediaItems(this,query);
    }

    private void localLoadSearchMediaItems(MediaItemControllerListener listener, String query){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadSearchMediaItems");
        dataManager.getSearchResults(listener, query);
    }

    @Override
    public void onMediaItemsAvailable(List<MediaItem> mediaItems, int id) {
        this.searchMediaItems.setValue((List<MediaItem>) mediaItems);
    }
}