package nl.avans.netnietflix.repository;

import android.util.Log;

import java.util.Locale;

import nl.avans.netnietflix.repository.API.AddMediaItemToListController;
import nl.avans.netnietflix.repository.API.DetailMediaItemListController;
import nl.avans.netnietflix.repository.API.DetailedMediaItemController;
import nl.avans.netnietflix.repository.API.MediaItemControllerListener;
import nl.avans.netnietflix.repository.API.AllMediaItemListsController;
import nl.avans.netnietflix.repository.API.RatingController;
import nl.avans.netnietflix.repository.API.RemoveListController;
import nl.avans.netnietflix.repository.API.RemoveListItemController;
import nl.avans.netnietflix.repository.API.ReviewController;
import nl.avans.netnietflix.repository.API.SearchController;
import nl.avans.netnietflix.repository.API.TopRatedController;
import nl.avans.netnietflix.repository.API.TrendingController;

public class DataController{
    private static final String TAG = DataController.class.getSimpleName();
    public void loadTrendingMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems from API");
        TrendingController apiController = new TrendingController(listener);
        apiController.loadTrendingMoviesPerWeek(getLanguage());
    }
    public void loadTopRatedMediaItems(MediaItemControllerListener listener){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems from API");
        TopRatedController apiController = new TopRatedController(listener);
        apiController.loadTopRatedMoviesPerWeek(getLanguage());
    }
    public void getMediaItemDetails(DetailedMediaItemController.DetailedMediaItemListener listener, int mediaItemid){
        DetailedMediaItemController apiController = new DetailedMediaItemController(listener);
        apiController.getMediaItemDetails(mediaItemid,getLanguage());
    }

    public void getReviewForMovieId(ReviewController.ReviewListener reviewListener, int mediaItemId){
        ReviewController reviewController = new ReviewController(reviewListener);
        reviewController.getReviewDetails(mediaItemId);
    }
    public void getMediaItemLists(AllMediaItemListsController.MediaItemListListener mediaItemListListener){
        AllMediaItemListsController allMediaItemListsController = new AllMediaItemListsController(mediaItemListListener);
        allMediaItemListsController.getMediaItemLists();
    }

    public void getSearchResults(MediaItemControllerListener listener, String query){
        // Do an asynchronous operation to fetch movies
        Log.d(TAG, "loadMediaItems from API");
        SearchController apiController = new SearchController(listener);
        apiController.getSearchResults(query,getLanguage());
    }

    public void getDetailMediaItemList(DetailMediaItemListController.DetailMediaItemListListener mediaItemListListener, int listId){
        DetailMediaItemListController detailMediaItemListController = new DetailMediaItemListController(mediaItemListListener);
        detailMediaItemListController.getDetailMediaItemList(listId,getLanguage());
    }
    public void RemoveListController(RemoveListController.RemoveListListener listener, int listId){
        RemoveListController removeListController = new RemoveListController();
        removeListController.removeList(listener,listId);
    }

    public void addRatingToMovie(RatingController.RatingListener listener, int movieId, double rating) {
        RatingController ratingController = new RatingController(listener);
        ratingController.addRatingToMovie(movieId, rating);
    }

    public void removeItemFromList(RemoveListItemController.RemoveMediaItemFromListListener listener, int listId, int media_id) {
        RemoveListItemController removeListItemController = new RemoveListItemController(listener);
        removeListItemController.removeMediaItemFromList(listId, media_id);
    }
    public void addMediaItemToList(AddMediaItemToListController.AddMediaItemToListListener listener,int listId, int mediaItemId) {
        AddMediaItemToListController addMediaItemToListController = new AddMediaItemToListController(listener);
        addMediaItemToListController.addMediaItemToList(listId, mediaItemId);
    }

    private String getLanguage(){
        return Locale.getDefault().getLanguage();
    }


//    public boolean isConnected() {
//        try {
//            String command = "ping -c 1 api.themoviedb.org";
//            return Runtime.getRuntime().exec(command).waitFor() == 0;
//        } catch (InterruptedException ie) {
//            Log.d(TAG, "Error while checking connection: IO Exception:" + ie.getStackTrace());
//        } catch (IOException io) {
//            Log.d(TAG, "Error while checking connection: IO Exception:" + io.getStackTrace());
//        }
//        return false;
//    }

    }
