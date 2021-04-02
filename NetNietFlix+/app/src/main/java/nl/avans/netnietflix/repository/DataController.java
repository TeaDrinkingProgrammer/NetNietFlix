package nl.avans.netnietflix.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

import nl.avans.netnietflix.repository.API.DetailMediaItemController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;
import nl.avans.netnietflix.repository.API.TopRatedMediaItemController;
import nl.avans.netnietflix.repository.API.TrendingMediaItemController;

import static androidx.core.content.ContextCompat.getSystemService;

public class DataController{
    private static final String TAG = DataController.class.getSimpleName();
    public void loadTrendingMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems from API");
        TrendingMediaItemController apiController = new TrendingMediaItemController(listener);
        apiController.loadTrendingMoviesPerWeek();
    }
    public void loadTopRatedMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems from API");
        TopRatedMediaItemController apiController = new TopRatedMediaItemController(listener);
        apiController.loadTopRatedMoviesPerWeek();
    }
    public void getMediaItemDetails(DetailMediaItemController.DetailedMediaItemListener listener, int mediaItemid){
        DetailMediaItemController apiController = new DetailMediaItemController(listener);
        apiController.getMediaItemDetails(mediaItemid);
    }
    public boolean isConnected() {
        try {
            String command = "ping -c 1 api.themoviedb.org";
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (InterruptedException ie) {
            Log.d(TAG, "Error while checking connection: IO Exception:" + ie.getStackTrace());
        } catch (IOException io) {
            Log.d(TAG, "Error while checking connection: IO Exception:" + io.getStackTrace());
        }
        return false;
    }

    }
