package nl.avans.netnietflix.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.avans.netnietflix.domain.MediaItem;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;
import nl.avans.netnietflix.repository.API.TopRatedMediaItemController;
import nl.avans.netnietflix.repository.API.TrendingMediaItemController;
import nl.avans.netnietflix.ui.RecyclerView.MediaItemAdapter;

public class HomeViewModel extends ViewModel implements MediaItemControllerListener {

    private MutableLiveData<List<MediaItem>> trendingMediaItems = null;
    private MutableLiveData<List<MediaItem>> topRatedMediaItems = null;
    private String TAG = this.getClass().getSimpleName();
    private Context context;
    private RecyclerView trendingRecyclerview;
    private RecyclerView topRatedRecyclerview;
    private MediaItemAdapter trendingMoviesAdapter;
    private MediaItemAdapter topRatedMoviesAdapter;

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
            loadTopRatedMediaItems( this);
        }
        return topRatedMediaItems;
    }


    private void loadTrendingMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        TrendingMediaItemController apiController = new TrendingMediaItemController(listener);
        apiController.loadTrendingMoviesPerWeek();
    }
    private void loadTopRatedMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems");
        TopRatedMediaItemController apiController = new TopRatedMediaItemController(listener);
        apiController.loadTopRatedMoviesPerWeek();
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
}