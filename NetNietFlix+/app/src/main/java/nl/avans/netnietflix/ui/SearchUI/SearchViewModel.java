package nl.avans.netnietflix.ui.SearchUI;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nl.avans.netnietflix.applogic.DataManager;
import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;

public class SearchViewModel extends ViewModel implements MediaItemControllerListener {

    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<List<MediaItem>> searchMediaItems = null;
    private DataManager dataManager;
    List<MediaItem> mediaItemsNormal = new ArrayList<>();
    List<MediaItem> mediaItemsNormalResult = new ArrayList<>();


    public SearchViewModel() {
        dataManager = new DataManager();
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

    public MediaItem sortRatings(List<MediaItem> mediaItems){
        MediaItem bestMediaItem = mediaItems.get(0);
        for(int i = 1; i < mediaItems.size(); i++) {
            if(bestMediaItem.getVoteAverage() < mediaItems.get(i).getVoteAverage()){
                bestMediaItem = mediaItems.get(i);
            }
        }
        mediaItemsNormal.remove(bestMediaItem);
        return bestMediaItem;
    }

    public List<MediaItem> filterItems(String filterOption, List<MediaItem> mediaItems){
        mediaItemsNormal.clear();
        mediaItemsNormalResult.clear();
        if(filterOption.equals("rating")){
            mediaItemsNormal.addAll(mediaItems);
            for(MediaItem mediaItem : mediaItems) {
                MediaItem answer = sortRatings(mediaItemsNormal);
                mediaItemsNormalResult.add(answer);
            }
            Log.d(TAG,mediaItemsNormalResult.get(0).getVoteAverage().toString());
            Log.d(TAG,mediaItemsNormalResult.get(1).getVoteAverage().toString());
            Log.d(TAG,mediaItemsNormalResult.get(2).getVoteAverage().toString());
        }
        return mediaItemsNormalResult;
    }


}