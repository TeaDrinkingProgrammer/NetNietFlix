package nl.avans.netnietflix.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Locale;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.DetailMediaItemList;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.repository.API.DetailMediaItemListController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;

public class HomeViewModel extends ViewModel implements MediaItemControllerListener, DetailMediaItemListController.DetailMediaItemListListener {

    private MutableLiveData<List<MediaItem>> trendingMediaItems = null;
    private MutableLiveData<List<MediaItem>> topRatedMediaItems = null;
    private MutableLiveData<List<MediaItem>> watchLaterMediaItems = null;
    private String TAG = this.getClass().getSimpleName();
    private DataManager dataManager;

    public HomeViewModel(){
         dataManager = new DataManager();
    }

    public LiveData<List<MediaItem>> getWatchLaterMediaItems(){
        Log.d(TAG,"getMediaItems is executed");
        if(watchLaterMediaItems == null){
            watchLaterMediaItems = new MutableLiveData<>();
            loadWatchLaterMediaItems(this);
        }
        return watchLaterMediaItems;
    }
    public LiveData<List<MediaItem>> getTrendingMediaItems(){
        Log.d(TAG,"getMediaItems is executed");
        if(trendingMediaItems == null){
            trendingMediaItems = new MutableLiveData<>();
            loadTrendingMediaItems(this);
        }
        return trendingMediaItems;
    }
    public LiveData<List<MediaItem>> getTopRatedMediaItems(){
        Log.d(TAG,"getMediaItems is executed");
        if(topRatedMediaItems == null){
            topRatedMediaItems = new MutableLiveData<>();
            loadTopRatedMediaItems( (MediaItemControllerListener) this);
        }
        return topRatedMediaItems;
    }


    private void loadTrendingMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        dataManager.loadTrendingMediaItems(listener);
    }
    private void loadTopRatedMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        dataManager.loadTopRatedMediaItems(listener);
    }
    private void loadWatchLaterMediaItems(DetailMediaItemListController.DetailMediaItemListListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        dataManager.getDetailItemList(listener,7088970);
    }

    @Override
    public void onMediaItemsAvailable(List<MediaItem> mediaItems, int id) {
        Log.d(TAG,"onMediaItemsAvailable is executed");
        if(id == 0){
            this.trendingMediaItems.setValue((List<MediaItem>) mediaItems);
        } else{
            this.topRatedMediaItems.setValue((List<MediaItem>) mediaItems);
        }
    }

    @Override
    public void onDetailMediaItemListAvailable(DetailMediaItemList mediaItemList) {
        this.watchLaterMediaItems.setValue(mediaItemList.getMediaItemList());
    }
}